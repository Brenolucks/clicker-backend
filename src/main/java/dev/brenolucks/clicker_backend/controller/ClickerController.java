package dev.brenolucks.clicker_backend.controller;

import dev.brenolucks.clicker_backend.domain.dto.user.UserRequestDTO;
import dev.brenolucks.clicker_backend.service.clicker.ClickerServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClickerController {
    private final ClickerServiceImpl clickerService;

    public ClickerController(ClickerServiceImpl clickerService) {
        this.clickerService = clickerService;
    }

    @PostMapping("/clicker")
    public ResponseEntity<String> clicked(@RequestBody UserRequestDTO userRequestDTO) throws MessagingException {
        clickerService.coreClicker(userRequestDTO.username());
        return ResponseEntity.status(HttpStatus.OK).body("ClickerResponseDTO");
    }
}
