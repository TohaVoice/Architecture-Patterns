package ru.otus.shatokhin.domain.action;

/**
 * Provides a contract to store and change level of the fuel
 */
public interface FuelStorable {

    int getFuelLevel();

    void decreaseFuelLevel(int level);

    void increaseFuelLevel(int level);

    int getFuelLimit();
}
