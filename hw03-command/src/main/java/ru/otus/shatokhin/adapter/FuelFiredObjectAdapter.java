package ru.otus.shatokhin.adapter;

import ru.otus.shatokhin.domain.FuelFiredObject;

/**
 * Just a draft adapter for the future
 * @param <T>
 */
public class FuelFiredObjectAdapter<T extends FuelFiredObject> implements FuelFiredObject {

    private T uObject;

    public FuelFiredObjectAdapter(T uObject) {
        this.uObject = uObject;
    }

    @Override
    public int getFuelBurnVelocity() {
        return uObject.getFuelBurnVelocity();
    }

    @Override
    public int getFuelLevel() {
        return uObject.getFuelLevel();
    }

    @Override
    public void decreaseFuelLevel(int level) {
        uObject.decreaseFuelLevel(level);
    }

    @Override
    public void increaseFuelLevel(int level) {
        uObject.increaseFuelLevel(level);
    }

    @Override
    public int getFuelLimit() {
        return uObject.getFuelLimit();
    }
}
