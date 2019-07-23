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

public class IntCommandTest {
    private IntCommand sut;
    private LogSaver saverStub = mock(LogSaver.class);

    @Before
    public void setUp() {
        int messageStub = 1;
        sut = new IntCommand(messageStub, saverStub);
    }

    @Test
    public void shouldAddPrimitivePrefixWhenGetDecoratedString() {
        String result = sut.getDecoratedString();

        assertThat(result).contains("primitive: ");
    }

    @Test
    public void shouldReturnTrueWhenEqualsCalledWithIntCommand() {
        Command commandStub = mock(IntCommand.class);

        boolean result = sut.equals(commandStub);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseWhenEqualsCalledWithNotIntCommand() {
        Command commandStub = mock(StringCommand.class);

        boolean result = sut.equals(commandStub);

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldCallSaverSaveWhenSave() {
        Command commandStub = mock(StringCommand.class);

        sut.save(commandStub);

        verify(saverStub).save(sut.getDecoratedString());
    }

    @Test
    // TODO: check call accumulate
    public void shouldReturnItselfWhenSaveWithIntCommand() {
        int messageStub = 1;
        Command commandStub = new IntCommand(messageStub, saverStub);

        Command result = sut.save(commandStub);

        assertThat(result).isEqualTo(sut);
    }

    @Test
    public void shouldReturnInputCommandWhenSave() {
        Command commandStub = mock(StringCommand.class);

        Command result = sut.save(commandStub);

        assertThat(result).isEqualTo(commandStub);
    }

    @Test
    public void shouldCallSaverSaveWhenFlush() {
        sut.flush();

        verify(saverStub).save(sut.getDecoratedString());
    }

    @Test
    public void shouldIncrementMessageWhenAccumulate() {
        int messageStub = 1;
        Command commandStub = new IntCommand(messageStub, saverStub);

        sut.accumulate(commandStub);

        assertThat(sut.getMessage()).isEqualTo(2);
    }

    @Test
    public void shouldCallSaverSaveAndIncrementMessageWhenAccumulate() {
        int messageStub1 = 5;
        Command commandStub1 = new IntCommand(messageStub1, saverStub);
        int messageStub2 = Integer.MAX_VALUE;
        Command commandStub2 = new IntCommand(messageStub2, saverStub);

        sut.accumulate(commandStub1);
        sut.accumulate(commandStub2);

        verify(saverStub).save(commandStub2.getDecoratedString());
        assertThat(sut.getMessage()).isEqualTo(6);
    }
}
