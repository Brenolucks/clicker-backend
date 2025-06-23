package dev.brenolucks.clicker_backend.utils;

import dev.brenolucks.clicker_backend.domain.model.Picsum;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@AllArgsConstructor
public class PicsumUtils {
    private final WebClient webClient;

    public Mono<ResponseEntity<Resource>> findPicsumById() {
        return webClient
                .get()
                .uri("/200/300")
                .accept(MediaType.IMAGE_JPEG)
                .retrieve()
                .toEntity(Resource.class);
    }
}
