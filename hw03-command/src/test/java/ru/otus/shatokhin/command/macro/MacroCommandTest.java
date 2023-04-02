package ru.otus.shatokhin.command.macro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.shatokhin.command.Command;
import ru.otus.shatokhin.exception.CommandException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@DisplayName("Macro command test")
class MacroCommandTest {

    private MacroCommand macroCommand;

    private class TestCommand implements Command {

        private List<Integer> executedCommands;
        private int order;

        public TestCommand(int order, List<Integer> executedCommands) {
            this.order = order;
            this.executedCommands = executedCommands;
        }

        @Override
        public void execute() {
            executedCommands.add(order);
        }
    }

    @Test
    @DisplayName("Checking the order in which commands are executed")
    void checkOrderCommands() {
        Queue<Command> commands = new LinkedList<>();
        List<Integer> executedCommands = new LinkedList<>();
        commands.add(new TestCommand(1, executedCommands));
        commands.add(new TestCommand(2, executedCommands));
        commands.add(new TestCommand(3, executedCommands));
        macroCommand = new MacroCommand(commands);

        macroCommand.execute();

        assertEquals(1, executedCommands.get(0));
        assertEquals(2, executedCommands.get(1));
        assertEquals(3, executedCommands.get(2));
    }

    @Test
    @DisplayName("If the command throws an exception, stop executing the macro command")
    void stopExecuteWhenThereIsException() {
        Queue<Command> commands = new LinkedList<>();
        List<Integer> executedCommands = new LinkedList<>();
        TestCommand testCommandMock= mock(TestCommand.class);
        commands.add(new TestCommand(1, executedCommands));
        commands.add(testCommandMock);
        commands.add(new TestCommand(3, executedCommands));
        macroCommand = new MacroCommand(commands);
        RuntimeException exception = new RuntimeException("test");
        doThrow(exception).when(testCommandMock).execute();

        assertThatThrownBy(() -> macroCommand.execute())
                .isInstanceOf(CommandException.class)
                .hasMessageContaining(exception.getMessage())
                .hasCause(exception);

        assertEquals(1, executedCommands.size());
    }
}
