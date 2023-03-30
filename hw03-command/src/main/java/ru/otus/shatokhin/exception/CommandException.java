package ru.otus.shatokhin.exception;

/**
 * Basic exception for commands
 */
public class CommandException extends RuntimeException {

    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message) {
        super(message);
    }
}
