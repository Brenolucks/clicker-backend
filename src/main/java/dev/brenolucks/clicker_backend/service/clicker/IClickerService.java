package dev.brenolucks.clicker_backend.service.clicker;

import dev.brenolucks.clicker_backend.domain.dto.clicker.ClickerRequestDTO;
import dev.brenolucks.clicker_backend.domain.dto.clicker.ClickerResponseDTO;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface IClickerService {
    ClickerResponseDTO coreClicker(ClickerRequestDTO clickerRequestDTO) throws MessagingException, IOException;
}
