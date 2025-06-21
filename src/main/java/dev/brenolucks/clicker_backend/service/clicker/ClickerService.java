package dev.brenolucks.clicker_backend.service.clicker;

import dev.brenolucks.clicker_backend.domain.dto.user.UserRequestDTO;

public interface ClickerService {
    int checkNumberIsEqualDatabase(String username);
}
