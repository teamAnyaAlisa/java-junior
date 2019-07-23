package com.acme.edu.command;

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
        Command commandStub = mock(ByteCommand.class);

        boolean result = sut.equals(commandStub);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseWhenEqualsCalledWithNotByteCommand() {
        Command commandStub = mock(StringCommand.class);

        boolean result = sut.equals(commandStub);

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldCallSaverSaveAndReturnInputCommandWhenSaveWithAnotherCommand() {
        Command commandStub = mock(StringCommand.class);

        Command result = sut.save(commandStub);

        verify(saverStub).save(sut.getDecoratedString());
        assertThat(result).isEqualTo(commandStub);
    }

    @Test
    public void shouldReturnItselfAndCallAccumulateWhenSaveWithByteCommand() {
        byte messageStub = 1;
        Command commandStub = new ByteCommand(messageStub, saverStub);

        Command result = sut.save(commandStub);

        assertThat(result).isEqualTo(sut);
        assertThat(sut.getMessage()).isEqualTo(2);
    }

    @Test
    public void shouldCallSaverSaveWhenFlush() {
        sut.flush();

        verify(saverStub).save(sut.getDecoratedString());
    }

    @Test
    public void shouldIncrementMessageWhenAccumulateWithoutByteOverflow() {
        byte messageStub = 1;
        Command commandStub = new ByteCommand(messageStub, saverStub);

        sut.accumulate(commandStub);

        assertThat(sut.getMessage()).isEqualTo(2);
    }

    @Test
    public void shouldCallSaverSaveAndIncrementMessageWhenAccumulateWithByteOverflow() {
        byte messageStub1 = 5;
        Command commandStub1 = new ByteCommand(messageStub1, saverStub);
        byte messageStub2 = Byte.MAX_VALUE;
        Command commandStub2 = new ByteCommand(messageStub2, saverStub);

        sut.accumulate(commandStub1);
        sut.accumulate(commandStub2);

        verify(saverStub).save(commandStub2.getDecoratedString());
        assertThat(sut.getMessage()).isEqualTo(6);
    }
}
