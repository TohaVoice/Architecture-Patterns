package ru.otus.shatokhin.domain.action;

import ru.otus.shatokhin.model.Vector;

/**
 * Provides a contract to move objects rectilinear uniformly without deformation
 */
public interface Movable {

    Vector getPosition();

    Vector getVelocity();

    void setVelocity(Vector velocity);

    void setPosition(Vector position);
}
