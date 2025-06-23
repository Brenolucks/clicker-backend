package dev.brenolucks.clicker_backend.service.clicker;

import dev.brenolucks.clicker_backend.domain.dto.user.UserRequestDTO;
import jakarta.mail.MessagingException;

public interface ClickerService {
    void coreClicker(String username) throws MessagingException;
}
