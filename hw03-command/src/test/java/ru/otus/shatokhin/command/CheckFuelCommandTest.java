package ru.otus.shatokhin.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.shatokhin.domain.FuelFiredObject;
import ru.otus.shatokhin.exception.CommandException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@DisplayName("Check fuel command test")
class CheckFuelCommandTest {

    private CheckFuelCommand checkFuelCommand;

    private FuelFiredObject fuelFiredObjectMock;

    @BeforeEach
    void setUp() {
        fuelFiredObjectMock = mock(FuelFiredObject.class);
        checkFuelCommand = new CheckFuelCommand(fuelFiredObjectMock);
    }

    @Test
    @DisplayName("Do nothing when there is enough fuel")
    void doNothingWhenEnoughFuel() {
        when(fuelFiredObjectMock.getFuelLevel()).thenReturn(10);
        when(fuelFiredObjectMock.getFuelBurnVelocity()).thenReturn(1);

        checkFuelCommand.execute();

        verify(fuelFiredObjectMock, times(1)).getFuelLevel();
        verify(fuelFiredObjectMock, times(1)).getFuelBurnVelocity();
    }

    @Test
    @DisplayName("Throw exception when there isn't enough fuel")
    void throwExceptionWhenNotEnoughFuel() {
        when(fuelFiredObjectMock.getFuelLevel()).thenReturn(1);
        when(fuelFiredObjectMock.getFuelBurnVelocity()).thenReturn(2);

        assertThatThrownBy(() -> checkFuelCommand.execute())
                .isInstanceOf(CommandException.class).hasMessageContaining("Not enough fuel");
    }
}
