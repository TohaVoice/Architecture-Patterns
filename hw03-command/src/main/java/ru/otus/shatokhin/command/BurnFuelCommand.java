package ru.otus.shatokhin.command;

import org.jetbrains.annotations.NotNull;
import ru.otus.shatokhin.command.macro.MacroCommand;
import ru.otus.shatokhin.domain.FuelFiredObject;

/**
 * Command for burning the fuel
 */
public class BurnFuelCommand implements Command {

    private FuelFiredObject fuelFiredObject;

    private MacroCommand macroCommand;

    public BurnFuelCommand(@NotNull FuelFiredObject fuelFiredObject, MacroCommand macroCommandValidator) {
        this.fuelFiredObject = fuelFiredObject;
        macroCommand = macroCommandValidator;
    }

    @Override
    public void execute() {
        macroCommand.execute();
        fuelFiredObject.decreaseFuelLevel(fuelFiredObject.getFuelLevel() - fuelFiredObject.getFuelBurnVelocity());
    }
}
