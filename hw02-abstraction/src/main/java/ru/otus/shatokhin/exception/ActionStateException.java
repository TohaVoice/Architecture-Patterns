package ru.otus.shatokhin.exception;

public class ActionStateException extends RuntimeException {

    public ActionStateException(Throwable cause) {
        super(cause);
    }

    public ActionStateException(String message) {
        super(message);
    }
}
