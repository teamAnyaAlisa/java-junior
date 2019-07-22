package com.acme.edu.unitTests.CommandTests;

import com.acme.edu.command.CharCommand;
import com.acme.edu.command.Command;
import com.acme.edu.command.StringCommand;
import com.acme.edu.saver.LogSaver;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CharCommandTest {
    private CharCommand sut;
    private LogSaver saverStub = mock(LogSaver.class);

    @Before
    public void setUp() {
        char messageStub = 'a';
        sut = new CharCommand(messageStub, saverStub);
    }

    @Test
    public void shouldAddPrimitivePrefixWhenGetDecoratedString() {
        String result = sut.getDecoratedString();

        assertThat(result).contains("char: ");
    }

    @Test
    public void shouldReturnTrueWhenEqualsCalledWithCharCommand() {
        Command stub = mock(CharCommand.class);

        boolean result = sut.equals(stub);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseWhenEqualsCalledWithNotCharCommand() {
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
}
