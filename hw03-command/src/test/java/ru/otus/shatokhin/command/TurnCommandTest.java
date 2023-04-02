package ru.otus.shatokhin.command;

import org.junit.jupiter.api.*;
import ru.otus.shatokhin.command.macro.TurnCommand;
import ru.otus.shatokhin.domain.action.Movable;
import ru.otus.shatokhin.domain.action.Rotatable;
import ru.otus.shatokhin.model.Vector;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@DisplayName("Turn command test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnCommandTest {

    private TurnCommand turnCommand;

    private ITestObject iTestObjectMock;

    private interface ITestObject extends Rotatable, Movable {

    }

    @BeforeEach
    void setUp() {
        iTestObjectMock = mock(ITestObject.class);
        turnCommand = new TurnCommand<>(iTestObjectMock);
    }

    @Test
    @Order(1)
    @DisplayName("Change velocity")
    void changeVelocity() {
        when(iTestObjectMock.getPosition()).thenReturn(new Vector(12, 5));
        when(iTestObjectMock.getVelocity()).thenReturn(new Vector(-7, 3));
        when(iTestObjectMock.getDirectionsNum()).thenReturn(360);
        when(iTestObjectMock.getDirection()).thenReturn(90);
        when(iTestObjectMock.getAngularVelocity()).thenReturn(40);

        turnCommand.execute();

        verify(iTestObjectMock, times(1)).setVelocity(argThat(vector -> vector.x() == -17 && vector.y() == 35));
    }

    @Test
    @Order(2)
    @DisplayName("For an object at (12, 5) and moving at (-17, 35), the movement changes the object's position to (5, 8)")
    void objectChangePosition() {
        when(iTestObjectMock.getPosition()).thenReturn(new Vector(12, 5));
        when(iTestObjectMock.getVelocity()).thenReturn(new Vector(-17, 35));
        when(iTestObjectMock.getDirectionsNum()).thenReturn(360);
        when(iTestObjectMock.getDirection()).thenReturn(90);
        when(iTestObjectMock.getAngularVelocity()).thenReturn(40);

        turnCommand.execute();

        verify(iTestObjectMock).setPosition(argThat(vector -> vector.x() == -5 && vector.y() == 40));
    }

    @Test
    @Order(3)
    @DisplayName("To rotate at an angle of 130 at an initial position of 90 and an angular velocity of 40")
    void objectRotate() {
        when(iTestObjectMock.getPosition()).thenReturn(new Vector(-5, 40));
        when(iTestObjectMock.getVelocity()).thenReturn(new Vector(-17, 35));
        when(iTestObjectMock.getDirectionsNum()).thenReturn(360);
        when(iTestObjectMock.getDirection()).thenReturn(90);
        when(iTestObjectMock.getAngularVelocity()).thenReturn(40);

        turnCommand.execute();

        verify(iTestObjectMock).setDirection(eq(130));
    }
}