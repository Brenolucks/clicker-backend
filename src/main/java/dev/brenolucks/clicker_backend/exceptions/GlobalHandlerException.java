package dev.brenolucks.clicker_backend.exceptions;

import dev.brenolucks.clicker_backend.domain.dto.exceptions.ErrorResponseDTO;
import dev.brenolucks.clicker_backend.exceptions.clicker.ClickOverException;
import dev.brenolucks.clicker_backend.exceptions.user.UserExistException;
import dev.brenolucks.clicker_backend.exceptions.user.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserExist(UserExistException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                "email",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserExist(UserNotExistException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                "username",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ClickOverException.class)
    public ResponseEntity<ErrorResponseDTO> handleClickItsOver(ClickOverException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                "avaliableClick",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
