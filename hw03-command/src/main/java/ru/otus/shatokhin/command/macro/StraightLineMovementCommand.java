package ru.otus.shatokhin.command.macro;

import ru.otus.shatokhin.command.BurnFuelCommand;
import ru.otus.shatokhin.command.CheckFuelCommand;
import ru.otus.shatokhin.command.MoveCommand;
import ru.otus.shatokhin.domain.FuelFiredObject;
import ru.otus.shatokhin.domain.action.Movable;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Command for moving in a straight line with checking the fuel level and burning it
 */
public class StraightLineMovementCommand extends MacroCommand {

    public <T extends FuelFiredObject & Movable> StraightLineMovementCommand(T uObject) {
        super();
        CheckFuelCommand checkFuelCommand = new CheckFuelCommand(uObject);
        queue.add(checkFuelCommand);
        queue.add(new MoveCommand(uObject));

        Queue validateCommands = new LinkedList<>();
        validateCommands.add(checkFuelCommand);
        queue.add(new BurnFuelCommand(uObject, new MacroCommand(validateCommands)));
    }
}
