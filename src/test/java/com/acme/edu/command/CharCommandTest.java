package com.acme.edu.command;

import com.acme.edu.command.CharCommand;
import com.acme.edu.command.Command;
import com.acme.edu.command.StringCommand;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogSaver;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CharCommandTest {
    private CharCommand sut;

    @Before
    public void setUp() {
        char messageStub = 'a';
        sut = new CharCommand(messageStub);
    }

    @Test
    public void shouldAddPrimitivePrefixWhenGetDecoratedString() {
        String result = sut.getDecoratedString();

        assertThat(result).contains("char: ");
    }

    @Test
    public void shouldReturnTrueWhenEqualsCalledWithCharCommand() {
        Command commandStub = mock(CharCommand.class);

        boolean result = sut.equals(commandStub);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseWhenEqualsCalledWithNotCharCommand() {
        Command commandStub = mock(StringCommand.class);

        boolean result = sut.equals(commandStub);

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldHasNoSideEffectsWhenAccumulate() {
        Command commandStub = mock(CharCommand.class);
        char previousMessageStub = sut.getMessage();

        sut.accumulate(commandStub);

        assertThat(sut.getMessage()).isEqualTo(previousMessageStub);
    }
}
