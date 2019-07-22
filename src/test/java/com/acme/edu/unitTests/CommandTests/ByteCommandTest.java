package com.acme.edu.unitTests.CommandTests;

import com.acme.edu.command.ByteCommand;
import com.acme.edu.command.Command;
import com.acme.edu.command.StringCommand;
import com.acme.edu.saver.LogSaver;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ByteCommandTest {
    private ByteCommand sut;
    private LogSaver saverStub = mock(LogSaver.class);

    @Before
    public void setUp() {
        byte messageStub = 1;
        sut = new ByteCommand(messageStub, saverStub);
    }

    @Test
    public void shouldAddPrimitivePrefixWhenGetDecoratedString() {
        String result = sut.getDecoratedString();

        assertThat(result).contains("primitive: ");
    }

    @Test
    public void shouldReturnTrueWhenEqualsCalledWithByteCommand() {
        Command stub = mock(ByteCommand.class);

        boolean result = sut.equals(stub);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseWhenEqualsCalledWithNotByteCommand() {
        Command stub = mock(StringCommand.class);

        boolean result = sut.equals(stub);

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
    public void shouldReturnItselfWhenSaveWithByteCommand() {
        byte messageStub = 1;
        Command stub = new ByteCommand(messageStub, saverStub);

        Command result = sut.save(stub);

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
        byte messageStub = 1;
        Command stub = new ByteCommand(messageStub, saverStub);

        sut.accumulate(stub);

        assertThat(sut.getMessage()).isEqualTo(2);
    }

    @Test
    public void shouldCallSaverSaveAndIncrementMessageWhenAccumulate() {
        byte messageStub1 = 5;
        Command stub1 = new ByteCommand(messageStub1, saverStub);
        byte messageStub2 = Byte.MAX_VALUE;
        Command stub2 = new ByteCommand(messageStub2, saverStub);

        sut.accumulate(stub1);
        sut.accumulate(stub2);

        verify(saverStub).save(stub2.getDecoratedString());
        assertThat(sut.getMessage()).isEqualTo(6);
    }
}
