package com.acme.edu.command;

import com.acme.edu.command.ByteCommand;
import com.acme.edu.command.Command;
import com.acme.edu.command.StringCommand;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogSaver;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ByteCommandTest {
    private ByteCommand sut;

    @Before
    public void setUp() {
        byte messageStub = 1;
        sut = new ByteCommand(messageStub);
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

//    @Test
//    public void shouldIncrementMessageWhenAccumulateWithoutByteOverflow() throws LogSaverException {
//        byte messageStub = 1;
//        Command commandStub = new ByteCommand(messageStub, saverStub);
//
//        sut.accumulate(commandStub);
//
//        assertThat(sut.getMessage()).isEqualTo(2);
//    }
//
//    @Test
//    public void shouldCallSaverSaveAndIncrementMessageWhenAccumulateWithByteOverflow() throws LogSaverException {
//        byte messageStub1 = 5;
//        Command commandStub1 = new ByteCommand(messageStub1, saverStub);
//        byte messageStub2 = Byte.MAX_VALUE;
//        Command commandStub2 = new ByteCommand(messageStub2, saverStub);
//
//        sut.accumulate(commandStub1);
//        sut.accumulate(commandStub2);
//
//        verify(saverStub).save(commandStub2.getDecoratedString());
//        assertThat(sut.getMessage()).isEqualTo(6);
//    }
}
