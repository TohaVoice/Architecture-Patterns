package ru.otus.shatokhin.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.shatokhin.domain.action.Movable;
import ru.otus.shatokhin.domain.action.Rotatable;
import ru.otus.shatokhin.model.Vector;

import static org.mockito.Mockito.*;

@DisplayName("Change velocity command test")
class ChangeVelocityCommandTest {

    private ChangeVelocityCommand changeVelocityCommand;

    private RotateCommand rotateCommandMock;

    private ITestObject iTestObjectMock;

    private interface ITestObject extends Rotatable, Movable {

    }

    @BeforeEach
    void setUp() {
        iTestObjectMock = mock(ITestObject.class);
        rotateCommandMock = mock(RotateCommand.class);
        changeVelocityCommand = new ChangeVelocityCommand(iTestObjectMock, rotateCommandMock);
    }

    @Test
    @DisplayName("Change velocity")
    void changeVelocity() {
        when(iTestObjectMock.getDirectionsNum()).thenReturn(360);
        when(iTestObjectMock.getDirection()).thenReturn(90);
        when(iTestObjectMock.getAngularVelocity()).thenReturn(20);

        changeVelocityCommand.execute();

        verify(iTestObjectMock, times(1)).setVelocity(argThat(vector -> vector.x() == -8 && vector.y() == 17));
    }
}