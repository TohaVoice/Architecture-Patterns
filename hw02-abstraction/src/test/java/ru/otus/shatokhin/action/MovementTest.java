package ru.otus.shatokhin.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.shatokhin.exception.ActionStateException;
import ru.otus.shatokhin.model.Vector;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@DisplayName("The movement to do")
class MovementTest {

    private Movement movement;

    private Movable movable;

    @BeforeEach
    void setUp() {
        movable = mock(Movable.class);
        movement = new Movement(movable);
    }

    @Test
    @DisplayName("For an object at (12, 5) and moving at (-7, 3), the movement changes the object's position to (5, 8)")
    void objectChangePosition() {
        when(movable.getPosition()).thenReturn(new Vector(12, 5));
        when(movable.getVelocity()).thenReturn(new Vector(-7, 3));

        movement.execute();

        verify(movable).setPosition(argThat(vector -> vector.x() == 5 && vector.y() == 8));
    }

    @Test
    @DisplayName("Attempting to move an object whose position in space cannot be read, throws to an error")
    void cannotGetObjectPositionThrowsError() {
        when(movable.getPosition()).thenReturn(null);

        assertThatThrownBy(() -> movement.execute())
                .isInstanceOf(ActionStateException.class).hasMessageContaining("Cannot get position");
    }

    @Test
    @DisplayName("Attempting to move an object whose instantaneous velocity cannot be read, throws to an error")
    void cannotGetObjectVelocityThrowsError() {
        when(movable.getPosition()).thenReturn(new Vector(10, 4));
        when(movable.getVelocity()).thenReturn(null);

        assertThatThrownBy(() -> movement.execute())
                .isInstanceOf(ActionStateException.class).hasMessageContaining("Cannot get velocity");
    }

    @Test
    @DisplayName("Attempting to move an object whose position in space cannot be changed, throws to an error")
    void cannotChangeObjectPositionThrowsError() {
        when(movable.getPosition()).thenReturn(new Vector(10, 4));
        when(movable.getVelocity()).thenReturn(new Vector(-3, 5));
        doThrow(new ActionStateException("Cannot set position")).when(movable).setPosition(any());

        assertThatThrownBy(() -> movement.execute())
                .isInstanceOf(ActionStateException.class).hasMessageContaining("Cannot set position");
    }

}
