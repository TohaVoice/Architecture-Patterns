package ru.otus.shatokhin.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.otus.shatokhin.domain.action.Rotatable;
import ru.otus.shatokhin.exception.CommandException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@DisplayName("The rotation to do")
class RotateCommandTest {

    private RotateCommand rotateCommand;
    private Rotatable rotatableMock;

    private static class RotationParametersProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                            new int[]{0, 90, 40},
                            new int[]{360, 0, 40},
                            new int[]{360, 90, 0})
                    .map(Arguments::of);
        }
    }

    @BeforeEach
    void setUp() {
        rotatableMock = mock(Rotatable.class);
        rotateCommand = new RotateCommand(rotatableMock);
    }

    @Test
    @DisplayName("To rotate at an angle of 130 at an initial position of 90 and an angular velocity of 40")
    void objectRotate() {
        when(rotatableMock.getDirectionsNum()).thenReturn(360);
        when(rotatableMock.getDirection()).thenReturn(90);
        when(rotatableMock.getAngularVelocity()).thenReturn(40);

        rotateCommand.execute();

        verify(rotatableMock).setDirection(eq(130));
    }

    @ParameterizedTest
    @ArgumentsSource(RotationParametersProvider.class)
    @DisplayName("Attempting to rotate an object whose any parameter is equal to zero, throws to an error")
    void objectWhereDirectionsNumIsEqualToZeroThrowsError(int[] rotationParameters) {
        when(rotatableMock.getDirectionsNum()).thenReturn(rotationParameters[0]);
        when(rotatableMock.getDirection()).thenReturn(rotationParameters[1]);
        when(rotatableMock.getAngularVelocity()).thenReturn(rotationParameters[2]);

        assertThatThrownBy(() -> rotateCommand.execute())
                .isInstanceOf(CommandException.class).hasMessageContaining("Rotation parameter cannot be zero");
    }

}
