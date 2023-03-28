package ru.otus.shatokhin.action;

import ru.otus.shatokhin.exception.ActionStateException;

public class Rotation {

    Rotatable rotatable;

    public Rotation(Rotatable rotatable) {
        this.rotatable = rotatable;
    }

    public void execute() {
        if (rotatable.getDirectionsNum() == 0 || rotatable.getDirection() == 0 || rotatable.getAngularVelocity() == 0)
            throw new ActionStateException("Rotation parameter cannot be zero");

        rotatable.setDirection(rotatable.getDirection() + rotatable.getAngularVelocity() % rotatable.getDirectionsNum());
    }
}
