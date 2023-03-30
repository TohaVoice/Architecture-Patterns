package ru.otus.shatokhin.command;

/**
 * Basic command interface
 */
@FunctionalInterface
public interface Command {
    void execute();
}
