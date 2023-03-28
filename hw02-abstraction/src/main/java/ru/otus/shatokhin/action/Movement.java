package ru.otus.shatokhin.action;

import ru.otus.shatokhin.exception.ActionStateException;
import ru.otus.shatokhin.model.Vector;

public class Movement {

    private Movable movable;

    public Movement(Movable movable) {
        this.movable = movable;
    }

    public void execute() {
        if (movable.getPosition() == null)
            throw new ActionStateException("Cannot get position");

        if (movable.getVelocity() == null)
            throw new ActionStateException("Cannot get velocity");

        movable.setPosition(Vector.plus(movable.getPosition(), movable.getVelocity()));
    }
}
