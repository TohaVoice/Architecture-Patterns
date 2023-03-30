package ru.otus.shatokhin.command.macro;

import org.junit.jupiter.api.*;
import ru.otus.shatokhin.command.BurnFuelCommand;
import ru.otus.shatokhin.command.CheckFuelCommand;
import ru.otus.shatokhin.command.Command;
import ru.otus.shatokhin.command.MoveCommand;
import ru.otus.shatokhin.domain.FuelFiredObject;
import ru.otus.shatokhin.domain.action.Movable;
import ru.otus.shatokhin.model.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Straight line movement command test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StraightLineMovementCommandTest {

    private StraightLineMovementCommand straightLineMovementCommand;

    private ITestObject iTestObjectMock;

    private interface ITestObject extends FuelFiredObject, Movable {

    }

    @BeforeEach
    void setUp() {
        iTestObjectMock = mock(ITestObject.class);
        straightLineMovementCommand = new StraightLineMovementCommand(iTestObjectMock);
    }

    @DisplayName("Check whether there is enough fuel")
    @Test
    @Order(1)
    public void checkThereIsEnoughFuel() {
        when(iTestObjectMock.getFuelLevel()).thenReturn(10);
        when(iTestObjectMock.getFuelBurnVelocity()).thenReturn(1);
        when(iTestObjectMock.getPosition()).thenReturn(new Vector(12, 5));
        when(iTestObjectMock.getVelocity()).thenReturn(new Vector(-7, 3));

        straightLineMovementCommand.execute();

        verify(iTestObjectMock, times(3)).getFuelLevel();
        verify(iTestObjectMock, times(3)).getFuelBurnVelocity();
    }

    @DisplayName("Change position")
    @Test
    @Order(2)
    public void changePosition() {
        when(iTestObjectMock.getFuelLevel()).thenReturn(10);
        when(iTestObjectMock.getFuelBurnVelocity()).thenReturn(1);
        when(iTestObjectMock.getPosition()).thenReturn(new Vector(12, 5));
        when(iTestObjectMock.getVelocity()).thenReturn(new Vector(-7, 3));

        straightLineMovementCommand.execute();

        verify(iTestObjectMock).setPosition(argThat(vector -> vector.x() == 5 && vector.y() == 8));
    }

    @DisplayName("Burn fuel")
    @Test
    @Order(3)
    public void burnFuel() {
        when(iTestObjectMock.getFuelLevel()).thenReturn(10);
        when(iTestObjectMock.getFuelBurnVelocity()).thenReturn(1);
        when(iTestObjectMock.getPosition()).thenReturn(new Vector(12, 5));
        when(iTestObjectMock.getVelocity()).thenReturn(new Vector(-7, 3));

        straightLineMovementCommand.execute();

        verify(iTestObjectMock, times(1)).decreaseFuelLevel(eq(9));
    }

    @DisplayName("Execute commands in order")
    @Test
    @Order(4)
    public void executeCommandsInOrder() {
        when(iTestObjectMock.getFuelLevel()).thenReturn(10);
        when(iTestObjectMock.getFuelBurnVelocity()).thenReturn(1);
        when(iTestObjectMock.getPosition()).thenReturn(new Vector(12, 5));
        when(iTestObjectMock.getVelocity()).thenReturn(new Vector(-7, 3));

        straightLineMovementCommand.execute();

        List<Command> queue = (LinkedList) straightLineMovementCommand.getQueue();
        assertEquals(CheckFuelCommand.class, queue.get(0).getClass());
        assertEquals(MoveCommand.class, queue.get(1).getClass());
        assertEquals(BurnFuelCommand.class, queue.get(2).getClass());
    }

}
