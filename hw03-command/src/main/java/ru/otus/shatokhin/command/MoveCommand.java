package ru.otus.shatokhin.command;

import ru.otus.shatokhin.domain.action.Movable;
import ru.otus.shatokhin.exception.CommandException;
import ru.otus.shatokhin.model.Vector;

/**
 * Command for moving the object
 */
public class MoveCommand implements Command {
    private Movable movable;

    private ChangeVelocityCommand changeVelocityCommand;

    public MoveCommand(Movable movable) {
        this.movable = movable;
    }

    public MoveCommand(Movable movable, ChangeVelocityCommand changeVelocityCommand) {
        this.movable = movable;
        this.changeVelocityCommand = changeVelocityCommand;
    }

    @Override
    public void execute() {
        if (movable.getPosition() == null)
            throw new CommandException("Cannot get position");

        if (movable.getVelocity() == null)
            throw new CommandException("Cannot get velocity");

        if (changeVelocityCommand != null)
            changeVelocityCommand.execute();

        movable.setPosition(Vector.plus(movable.getPosition(), movable.getVelocity()));
    }
}
