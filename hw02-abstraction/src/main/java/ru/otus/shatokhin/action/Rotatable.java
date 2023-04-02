package ru.otus.shatokhin.action;

/**
 * Provides a contract to rotate objects around its axis
 */
public interface Rotatable {

    int getDirection();

    void setDirection(int direction);

    int getAngularVelocity();

    int getDirectionsNum();
}
