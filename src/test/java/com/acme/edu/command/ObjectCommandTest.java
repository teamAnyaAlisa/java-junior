package com.acme.edu.command;

import com.acme.edu.command.BooleanCommand;
import com.acme.edu.command.Command;
import com.acme.edu.command.ObjectCommand;
import com.acme.edu.command.StringCommand;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogConsoleSaver;
import com.acme.edu.saver.LogSaver;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ObjectCommandTest {
    private ObjectCommand sut;

    @Before
    public void setUp() {
        Object messageStub = mock(Object.class);
        sut = new ObjectCommand(messageStub);
    }

    @Test
    public void shouldAddPrimitivePrefixWhenGetDecoratedString() {
        String result = sut.getDecoratedString();

        assertThat(result).contains("reference: ");
    }

    @Test
    public void shouldReturnTrueWhenEqualsCalledWithObjectCommand() {
        Command commandStub = mock(ObjectCommand.class);

        boolean result = sut.equals(commandStub);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseWhenEqualsCalledWithNotObjectCommand() {
        Command commandStub = mock(StringCommand.class);

        boolean result = sut.equals(commandStub);

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldHasNoSideEffectsWhenAccumulate() {
        Command commandStub = mock(ObjectCommand.class);
        Object previousMessageStub = sut.getMessage();

        sut.accumulate(commandStub);

        assertThat(sut.getMessage()).isEqualTo(previousMessageStub);
    }
}
