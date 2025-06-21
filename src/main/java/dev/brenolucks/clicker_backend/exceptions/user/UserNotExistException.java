package dev.brenolucks.clicker_backend.exceptions.user;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String message) {
        super(message);
    }
}
