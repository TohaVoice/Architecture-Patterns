package ru.otus.shatokhin.command;

import ru.otus.shatokhin.domain.action.Movable;
import ru.otus.shatokhin.exception.CommandException;
import ru.otus.shatokhin.model.Vector;

/**
 * Command for moving the object
 */
public class MoveCommand implements Command {
    private Movable movable;

    public MoveCommand(Movable movable) {
        this.movable = movable;
    }

    @Override
    public void execute() {
        if (movable.getPosition() == null)
            throw new CommandException("Cannot get position");

        if (movable.getVelocity() == null)
            throw new CommandException("Cannot get velocity");

        movable.setPosition(Vector.plus(movable.getPosition(), movable.getVelocity()));
    }
}
