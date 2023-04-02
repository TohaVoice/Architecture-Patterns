package ru.otus.shatokhin.domain;

import ru.otus.shatokhin.domain.action.FuelBurnable;
import ru.otus.shatokhin.domain.action.FuelStorable;

/**
 * Provides a contract for objects have ability store and burn the fuel
 */
public interface FuelFiredObject extends FuelStorable, FuelBurnable {
}
