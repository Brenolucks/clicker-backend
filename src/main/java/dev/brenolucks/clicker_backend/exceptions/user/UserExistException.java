package dev.brenolucks.clicker_backend.exceptions.user;

public class UserExistException extends RuntimeException {
    public UserExistException(String message) {
        super(message);
    }
}
