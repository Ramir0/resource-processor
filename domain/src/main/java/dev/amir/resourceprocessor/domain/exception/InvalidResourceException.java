package dev.amir.resourceprocessor.domain.exception;

public class InvalidResourceException extends RuntimeException {
    public InvalidResourceException(Long resourceId, Throwable throwable) {
        super(String.format("Resource with Id: %d does not contain required Metadata", resourceId), throwable);
    }

    public InvalidResourceException(Long resourceId) {
        this(resourceId, null);
    }
}
