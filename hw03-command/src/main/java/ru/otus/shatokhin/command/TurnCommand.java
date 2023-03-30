package ru.otus.shatokhin.command;

import ru.otus.shatokhin.command.macro.MacroCommand;
import ru.otus.shatokhin.domain.action.Movable;
import ru.otus.shatokhin.domain.action.Rotatable;

/**
 * Command for turning the object
 * @param <T>
 */
public class TurnCommand<T extends Movable & Rotatable> extends MacroCommand {

    public TurnCommand(T uObject) {
        super();
        queue.add(new RotateCommand(uObject));
        queue.add(new ChangeVelocityCommand(uObject));
        queue.add(new MoveCommand(uObject));
    }
}
