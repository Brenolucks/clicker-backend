package dev.brenolucks.clicker_backend.domain.dto;

public record UserLoginResponseDTO(String username, String email, int randomNumber, int avaliableClick, String token) {
}
