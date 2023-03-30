package ru.otus.shatokhin.command;

import ru.otus.shatokhin.domain.action.Movable;
import ru.otus.shatokhin.domain.action.Rotatable;

/**
 * Command for turning the object
 * @param <T>
 */
public class TurnCommand<T extends Movable & Rotatable> implements Command {

    private MoveCommand moveCommand;

    public TurnCommand(T uObject) {
        moveCommand = new MoveCommand(uObject, new ChangeVelocityCommand(
                uObject, new RotateCommand(uObject)
        ));
    }

    @Override
    public void execute() {
        moveCommand.execute();
    }
}
