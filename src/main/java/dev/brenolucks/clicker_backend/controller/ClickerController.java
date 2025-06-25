package dev.brenolucks.clicker_backend.controller;

import dev.brenolucks.clicker_backend.domain.dto.clicker.ClickerRequestDTO;
import dev.brenolucks.clicker_backend.domain.dto.clicker.ClickerResponseDTO;
import dev.brenolucks.clicker_backend.domain.dto.exceptions.ErrorResponseDTO;
import dev.brenolucks.clicker_backend.service.clicker.ClickerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@Tag(name = "Clicker", description = "Endpoints for clicker")
public class ClickerController {
    private final ClickerService clickerService;

    public ClickerController(ClickerService clickerService) {
        this.clickerService = clickerService;
    }

    @PostMapping("/clicker")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClickerResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "User suplied not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "403", description = "Unathorized")})
    @Operation(summary = "Principal endpoint for clicker", security = @SecurityRequirement( name = "bearerAuth"),
            description = "Here is the principal endpoint where should be return if you won or you lose.")
    public ResponseEntity<ClickerResponseDTO> clicked(@RequestBody ClickerRequestDTO clickerRequestDTO)
            throws MessagingException, IOException {
        var response = clickerService.coreClicker(clickerRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
