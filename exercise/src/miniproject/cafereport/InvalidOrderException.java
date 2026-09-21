package miniproject.cafereport;

import p260917.ex6.InvalidQuantityException;

public class InvalidOrderException extends RuntimeException {
    public InvalidOrderException(String message) {
        super(message);
    }
}
