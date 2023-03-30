package ru.otus.shatokhin.command;

import ru.otus.shatokhin.domain.action.Movable;
import ru.otus.shatokhin.domain.action.Rotatable;
import ru.otus.shatokhin.model.Vector;

/**
 * Command for changing velocity. Use it for @link {@link TurnCommand}
 */
public class ChangeVelocityCommand<T extends Movable & Rotatable> implements Command {

    private T uObject;

    private RotateCommand rotateCommand;

    public ChangeVelocityCommand(T uObject) {
        this.uObject = uObject;
    }

    @Override
    public void execute() {
        int direction = uObject.getDirection();
        int directionsNum = uObject.getDirectionsNum();
        int angularVelocity = uObject.getAngularVelocity();
        uObject.setVelocity(new Vector(
                (int) (angularVelocity * Math.cos((double) direction / 360 * directionsNum)),
                (int) (angularVelocity * Math.sin((double) direction / 360 * directionsNum))
        ));

    }
}
