package dev.brenolucks.clicker_backend.domain.dto.user;

public record UserLoginResponseDTO(String username, String email, int randomNumber, int avaliableClick, String token) {
}
