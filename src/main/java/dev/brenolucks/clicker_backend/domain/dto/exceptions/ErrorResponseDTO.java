package dev.brenolucks.clicker_backend.domain.dto.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ErrorResponseDTO(int status, String message, String field,
                               @JsonFormat(pattern="yyyy-MM-dd") LocalDateTime time) {
}
