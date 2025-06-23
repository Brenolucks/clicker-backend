package dev.brenolucks.clicker_backend.controller;

import dev.brenolucks.clicker_backend.domain.model.Picsum;
import dev.brenolucks.clicker_backend.service.picsum.PicsumService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PicsumController {
    private final PicsumService service;

    public PicsumController(PicsumService picsumService) {
        this.service = picsumService;
    }

    @GetMapping("/pic")
    public Mono<ResponseEntity<Resource>> consultarPersonagemPorId() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG)
                .body(service.consultarPersonagemPorId()).getBody();
    }

}
