package ru.otus.shatokhin.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.otus.shatokhin.exception.ActionStateException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@DisplayName("The rotation to do")
public class RotationTest {

    private Rotation rotation;
    private Rotatable rotatable;

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
        rotatable = mock(Rotatable.class);
        rotation = new Rotation(rotatable);
    }

    @Test
    @DisplayName("To rotate at an angle of 130 at an initial position of 90 and an angular velocity of 40")
    void objectRotate() {
        when(rotatable.getDirectionsNum()).thenReturn(360);
        when(rotatable.getDirection()).thenReturn(90);
        when(rotatable.getAngularVelocity()).thenReturn(40);

        rotation.execute();

        verify(rotatable).setDirection(eq(130));
    }

    @ParameterizedTest
    @ArgumentsSource(RotationParametersProvider.class)
    @DisplayName("Attempting to rotate an object whose any parameter is equal to zero, throws to an error")
    void objectWhereDirectionsNumIsEqualToZeroThrowsError(int[] rotationParameters) {
        when(rotatable.getDirectionsNum()).thenReturn(rotationParameters[0]);
        when(rotatable.getDirection()).thenReturn(rotationParameters[1]);
        when(rotatable.getAngularVelocity()).thenReturn(rotationParameters[2]);

        assertThatThrownBy(() -> rotation.execute())
                .isInstanceOf(ActionStateException.class).hasMessageContaining("Rotation parameter cannot be zero");
    }

}
