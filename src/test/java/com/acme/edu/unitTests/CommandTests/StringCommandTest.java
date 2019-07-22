package com.acme.edu.unitTests.CommandTests;

import com.acme.edu.command.Command;
import com.acme.edu.command.IntCommand;
import com.acme.edu.command.StringCommand;
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
        Command stub = new StringCommand(messageStub, saverStub);

        sut.accumulate(stub);
        String result = sut.getDecoratedString();

        assertThat(result).contains("string: ");
        assertThat(result).contains(" (x2)");
    }

    @Test
    public void shouldReturnTrueWhenEqualsCalledWithStringCommandWithSameMessage() {
        String messageStub = "test string";
        Command stub = new StringCommand(messageStub, saverStub);

        boolean result = sut.equals(stub);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnTrueWhenEqualsCalledWithStringCommandWithAnotherMessage() {
        String messageStub = "test another string";
        Command stub = new StringCommand(messageStub, saverStub);

        boolean result = sut.equals(stub);

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldReturnFalseWhenEqualsCalledWithNotStringCommand() {
        Command stub = mock(IntCommand.class);

        boolean result = sut.equals(stub);

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldCallSaverSaveWhenSave() {
        Command commandStub = mock(IntCommand.class);

        sut.save(commandStub);

        verify(saverStub).save(sut.getDecoratedString());
    }

    @Test
    public void shouldReturnInputCommandWhenSave() {
        Command commandStub = mock(IntCommand.class);

        Command result = sut.save(commandStub);

        assertThat(result).isEqualTo(commandStub);
    }

    @Test
    // TODO: check call accumulate
    public void shouldReturnItselfWhenSaveWithStringCommandWithSameMessage() {
        String messageStub = "test string";
        Command stub = new StringCommand(messageStub, saverStub);

        Command result = sut.save(stub);

        assertThat(result).isEqualTo(sut);
    }

    @Test
    public void shouldCallSaverSaveWhenFlush() {
        sut.flush();

        verify(saverStub).save(sut.getDecoratedString());
    }

    @Test
    public void shouldIncrementRepetitionCounterWhenAccumulate() {
        String messageStub = "test string";
        Command stub = new StringCommand(messageStub, saverStub);

        sut.accumulate(stub);

        assertThat(sut.getRepetitionMessageCounter()).isEqualTo(2);
    }
}
