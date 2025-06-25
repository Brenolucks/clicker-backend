package dev.brenolucks.clicker_backend.domain.dto.user;

public record LeadboardResponse(String username, int randomNumber, int attempts, boolean winner) {
}
