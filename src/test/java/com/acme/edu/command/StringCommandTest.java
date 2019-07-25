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
    private LogSaver saverStub = mock(LogSaver.class);

    @Before
    public void setUp() {
        String messageStub = "test string";
        sut = new StringCommand(messageStub, saverStub);
    }

    @Test
    public void shouldAddPrimitivePrefixWhenGetDecoratedString() {
        String result = sut.getDecoratedString();

        assertThat(result).contains("string: ");
    }

    @Test
    public void shouldAddPrimitivePrefixAndCounterPostfixWhenGetDecoratedString() {
        String messageStub = "test string";
        Command commandStub = new StringCommand(messageStub, saverStub);

        sut.accumulate(commandStub);
        String result = sut.getDecoratedString();

        assertThat(result).contains("string: ");
        assertThat(result).contains(" (x2)");
    }

    @Test
    public void shouldReturnTrueWhenEqualsCalledWithStringCommandWithSameMessage() {
        String messageStub = "test string";
        Command commandStub = new StringCommand(messageStub, saverStub);

        boolean result = sut.equals(commandStub);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnTrueWhenEqualsCalledWithStringCommandWithAnotherMessage() {
        String messageStub = "test another string";
        Command commandStub = new StringCommand(messageStub, saverStub);

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
    public void shouldCallSaverSaveAndReturnInputCommandWhenSaveWithAnotherCommand() throws LogSaverException {
        Command commandStub = mock(IntCommand.class);

        Command result = sut.save(commandStub);

        verify(saverStub).save(sut.getDecoratedString());
        assertThat(result).isEqualTo(commandStub);
    }

    @Test
    public void shouldReturnItselfAndCallAccumulateWhenSaveWithStringCommandWithSameMessage() throws LogSaverException {
        String messageStub = "test string";
        Command commandStub = new StringCommand(messageStub, saverStub);

        Command result = sut.save(commandStub);

        assertThat(result).isEqualTo(sut);
        assertThat(sut.getRepetitionMessageCounter()).isEqualTo(2);
    }

    @Test
    public void shouldCallSaverSaveWhenFlush() throws LogSaverException {
        sut.flush();

        verify(saverStub).save(sut.getDecoratedString());
    }

    @Test
    public void shouldIncrementRepetitionCounterWhenAccumulate() {
        String messageStub = "test string";
        Command commandStub = new StringCommand(messageStub, saverStub);

        sut.accumulate(commandStub);

        assertThat(sut.getRepetitionMessageCounter()).isEqualTo(2);
    }
}
