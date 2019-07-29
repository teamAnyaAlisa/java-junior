package com.acme.edu.command;

import com.acme.edu.command.Command;
import com.acme.edu.command.IntCommand;
import com.acme.edu.command.StringCommand;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogSaver;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StringCommandTest {
    private StringCommand sut;

    @Before
    public void setUp() {
        String messageStub = "test string";
        sut = new StringCommand(messageStub);
    }

    @Test
    public void shouldAddPrimitivePrefixWhenGetDecoratedString() {
        String result = sut.getDecoratedString();

        assertThat(result).contains("string: ");
    }

    @Test
    public void shouldAddPrimitivePrefixAndCounterPostfixWhenGetDecoratedString() {
        String messageStub = "test string";
        Command commandStub = new StringCommand(messageStub);

        sut.accumulate(commandStub);
        String result = sut.getDecoratedString();

        assertThat(result).contains("string: ");
        assertThat(result).contains(" (x2)");
    }

    @Test
    public void shouldReturnTrueWhenEqualsCalledWithStringCommandWithSameMessage() {
        String messageStub = "test string";
        Command commandStub = new StringCommand(messageStub);

        boolean result = sut.equals(commandStub);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnTrueWhenEqualsCalledWithStringCommandWithAnotherMessage() {
        String messageStub = "test another string";
        Command commandStub = new StringCommand(messageStub);

        boolean result = sut.equals(commandStub);

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldReturnFalseWhenEqualsCalledWithNotStringCommand() {
        Command commandStub = mock(IntCommand.class);

        boolean result = sut.equals(commandStub);

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldIncrementRepetitionCounterWhenAccumulate() {
        String messageStub = "test string";
        Command commandStub = new StringCommand(messageStub);

        sut.accumulate(commandStub);

        assertThat(sut.getRepetitionMessageCounter()).isEqualTo(2);
    }
}
