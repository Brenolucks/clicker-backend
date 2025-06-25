package dev.brenolucks.clicker_backend.controller;

import dev.brenolucks.clicker_backend.domain.dto.clicker.ClickerRequestDTO;
import dev.brenolucks.clicker_backend.domain.dto.clicker.ClickerResponseDTO;
import dev.brenolucks.clicker_backend.service.clicker.ClickerService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ClickerController {
    private final ClickerService clickerService;

    public ClickerController(ClickerService clickerService) {
        this.clickerService = clickerService;
    }

    @PostMapping("/clicker")
    public ResponseEntity<ClickerResponseDTO> clicked(@RequestBody ClickerRequestDTO clickerRequestDTO)
            throws MessagingException, IOException {
        var response = clickerService.coreClicker(clickerRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
