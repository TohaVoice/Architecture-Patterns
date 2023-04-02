package ru.otus.shatokhin.command;

import ru.otus.shatokhin.domain.action.Rotatable;
import ru.otus.shatokhin.exception.CommandException;

/**
 * Command for rotating the object
 */
public class RotateCommand implements Command {

    private Rotatable rotatable;

    public RotateCommand(Rotatable rotatable) {
        this.rotatable = rotatable;
    }

    public void execute() {
        if (rotatable.getDirectionsNum() == 0 || rotatable.getDirection() == 0 || rotatable.getAngularVelocity() == 0)
            throw new CommandException("Rotation parameter cannot be zero");

        rotatable.setDirection(rotatable.getDirection() + rotatable.getAngularVelocity() % rotatable.getDirectionsNum());
    }
}
