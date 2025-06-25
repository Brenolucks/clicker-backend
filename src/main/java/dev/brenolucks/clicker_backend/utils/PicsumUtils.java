package dev.brenolucks.clicker_backend.utils;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class PicsumUtils {
    private final WebClient webClient;

    public PicsumUtils(WebClient webClient) {
        this.webClient = webClient;
    }
    public File downloadSingleImageFromPicsum() throws IOException {
        String finalUrl = webClient
                .get()
                .uri("/200/300")
                .exchangeToMono(response -> {
                    if (response.statusCode().is3xxRedirection()) {
                        return Mono.just(response.headers().asHttpHeaders().getLocation().toString());
                    } else {
                        return Mono.error(new IOException("Waiting for redirect, but did not occur!"));
                    }
                })
                .block();

        if(finalUrl == null) throw new RuntimeException("Image not found!");

        byte[] image = webClient
                .get()
                .uri(finalUrl)
                .accept(MediaType.IMAGE_JPEG)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();

        if(image == null || image.length == 0) {
            throw new RuntimeException("Image not found");
        }

        File file = File.createTempFile("picsum_", ".jpg");
        Files.write(file.toPath(), image);
        return file;
    }
}
