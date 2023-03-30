package ru.otus.shatokhin.command.macro;

import ru.otus.shatokhin.command.Command;
import ru.otus.shatokhin.exception.CommandException;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Basic macro command, that can execute commands in order
 */
public class MacroCommand implements Command {

    protected Queue<Command> queue;

    public MacroCommand() {
        queue = new LinkedList<>();
    }

    public MacroCommand(Queue<Command> commands) {
        this.queue = commands;
    }

    @Override
    public void execute() {
        try {
            getQueue().forEach(Command::execute);
        } catch (Exception ex) {
            throw new CommandException(ex);
        }
    }

    public Queue<Command> getQueue() {
        return queue;
    }
}
