package ru.otus.shatokhin.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.shatokhin.domain.action.Movable;
import ru.otus.shatokhin.exception.CommandException;
import ru.otus.shatokhin.model.Vector;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@DisplayName("The movement to do")
class MoveCommandTest {

    private MoveCommand moveCommand;

    private Movable movableMock;

    @BeforeEach
    void setUp() {
        movableMock = mock(Movable.class);
        moveCommand = new MoveCommand(movableMock);
    }

    @Test
    @DisplayName("For an object at (12, 5) and moving at (-7, 3), the movement changes the object's position to (5, 8)")
    void objectChangePosition() {
        when(movableMock.getPosition()).thenReturn(new Vector(12, 5));
        when(movableMock.getVelocity()).thenReturn(new Vector(-7, 3));

        moveCommand.execute();

        verify(movableMock).setPosition(argThat(vector -> vector.x() == 5 && vector.y() == 8));
    }

    @Test
    @DisplayName("Attempting to move an object whose position in space cannot be read, throws to an error")
    void cannotGetObjectPositionThrowsError() {
        when(movableMock.getPosition()).thenReturn(null);

        assertThatThrownBy(() -> moveCommand.execute())
                .isInstanceOf(CommandException.class).hasMessageContaining("Cannot get position");
    }

    @Test
    @DisplayName("Attempting to move an object whose instantaneous velocity cannot be read, throws to an error")
    void cannotGetObjectVelocityThrowsError() {
        when(movableMock.getPosition()).thenReturn(new Vector(10, 4));
        when(movableMock.getVelocity()).thenReturn(null);

        assertThatThrownBy(() -> moveCommand.execute())
                .isInstanceOf(CommandException.class).hasMessageContaining("Cannot get velocity");
    }

    @Test
    @DisplayName("Attempting to move an object whose position in space cannot be changed, throws to an error")
    void cannotChangeObjectPositionThrowsError() {
        when(movableMock.getPosition()).thenReturn(new Vector(10, 4));
        when(movableMock.getVelocity()).thenReturn(new Vector(-3, 5));
        doThrow(new CommandException("Cannot set position")).when(movableMock).setPosition(any());

        assertThatThrownBy(() -> moveCommand.execute())
                .isInstanceOf(CommandException.class).hasMessageContaining("Cannot set position");
    }

}
