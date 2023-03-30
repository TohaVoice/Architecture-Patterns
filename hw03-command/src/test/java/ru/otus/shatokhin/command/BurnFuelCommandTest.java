package ru.otus.shatokhin.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.shatokhin.command.macro.MacroCommand;
import ru.otus.shatokhin.domain.FuelFiredObject;

import static org.mockito.Mockito.*;

@DisplayName("Burn fuel command test")
class BurnFuelCommandTest {

    private BurnFuelCommand burnFuelCommand;

    private FuelFiredObject fuelFiredObjectMock;

    private MacroCommand macroCommandMock;

    @BeforeEach
    void setUp() {
        fuelFiredObjectMock = mock(FuelFiredObject.class);
        macroCommandMock = mock(MacroCommand.class);
        burnFuelCommand = new BurnFuelCommand(fuelFiredObjectMock, macroCommandMock);
    }

    @Test
    @DisplayName("Burn fuel changing level according to burn velocity")
    void burnFuelIsCorrect() {
        when(fuelFiredObjectMock.getFuelLevel()).thenReturn(10);
        when(fuelFiredObjectMock.getFuelBurnVelocity()).thenReturn(1);

        burnFuelCommand.execute();

        verify(fuelFiredObjectMock, times(1)).decreaseFuelLevel(eq(9));
    }

}
