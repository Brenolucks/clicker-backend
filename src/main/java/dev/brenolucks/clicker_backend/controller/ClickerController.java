package dev.brenolucks.clicker_backend.controller;

import dev.brenolucks.clicker_backend.domain.dto.clicker.ClickerRequestDTO;
import dev.brenolucks.clicker_backend.domain.dto.clicker.ClickerResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.user.UserRequestDTO;
import dev.brenolucks.clicker_backend.service.clicker.ClickerServiceImpl;
import dev.brenolucks.clicker_backend.utils.ClickerUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClickerController {
    private final ClickerServiceImpl clickerService;
    private final ClickerUtils clickerUtils;

    public ClickerController(ClickerServiceImpl clickerService, ClickerUtils clickerUtils) {
        this.clickerService = clickerService;
        this.clickerUtils = clickerUtils;
    }

    @PostMapping("/clicker")
    public ResponseEntity<String> clicked(@RequestBody UserRequestDTO userRequestDTO) {
        var newRandomNumber = clickerService.checkNumberIsEqualDatabase(userRequestDTO.username());
        return ResponseEntity.status(HttpStatus.OK).body("ClickerResponseDTO");
    }
}
