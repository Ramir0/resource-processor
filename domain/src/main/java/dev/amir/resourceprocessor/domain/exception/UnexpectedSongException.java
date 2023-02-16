package dev.amir.resourceprocessor.domain.exception;

public class UnexpectedSongException extends RuntimeException {
    public UnexpectedSongException(String message) {
        super(message);
    }
}
