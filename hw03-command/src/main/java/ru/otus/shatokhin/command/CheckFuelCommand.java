package ru.otus.shatokhin.command;

import org.jetbrains.annotations.NotNull;
import ru.otus.shatokhin.domain.FuelFiredObject;
import ru.otus.shatokhin.exception.CommandException;

/**
 * Command for checking if there is the fuel enough for action
 */
public class CheckFuelCommand implements Command {

    private FuelFiredObject fuelFiredObject;

    public CheckFuelCommand(@NotNull FuelFiredObject fuelFiredObject) {
        this.fuelFiredObject = fuelFiredObject;
    }
    @Override
    public void execute() {
         if (fuelFiredObject.getFuelLevel() < fuelFiredObject.getFuelBurnVelocity()) {
            throw new CommandException("Not enough fuel");
        }
    }
}
