package com.acme.edu.logger;

import com.acme.edu.command.Command;
import com.acme.edu.command.StringCommand;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.customExceptions.NullSaverException;
import com.acme.edu.saver.LogSaver;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class LoggerControllerTest {
    private LoggerController sut;
    private LogSaver saverStub = mock(LogSaver.class);

    @Before
    public void setUp() {
        try {
            sut = new LoggerController(saverStub);
        } catch (NullSaverException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldCallSaverSaveWhenLogWithString() throws LogSaverException {
        String message = "test string";

        sut.log(message);

        verify(saverStub).save(message);
    }

    @Test
    public void shouldSetCurrentMessageToInputCommandWhenLogWithCommandForFirstTime() throws LogSaverException {
        Command commandStub = new StringCommand("test string 1", saverStub);

        sut.log(commandStub);

        assertThat(sut.getCurrentMessage()).isEqualTo(commandStub);
    }

    @Test
    public void shouldSetCurrentMessageToInputCommandAndSavePreviousWhenLogWithCommand() throws LogSaverException {
        Command commandStub1 = new StringCommand("test string 1", saverStub);
        Command commandStub2 = mock(StringCommand.class);

        when(commandStub2.save(commandStub1)).thenReturn(commandStub1);

        sut.log(commandStub2);
        sut.log(commandStub1);

        verify(commandStub2).save(commandStub1);
        assertThat(sut.getCurrentMessage()).isEqualTo(commandStub1);
    }

    @Test
    public void shouldCallCurrentMessageFlushAndFlushStateWhenFlush() throws LogSaverException {
        Command commandStub = mock(StringCommand.class);

        sut.log(commandStub);
        sut.flush();

        verify(commandStub).flush();
        assertThat(sut.getCurrentMessage()).isEqualTo(null);
    }

    @Test
    public void shouldHasNoSideEffectsWhenFlushWithEmptyCurrentState() throws LogSaverException {
        sut.flush();

        assertThat(sut.getCurrentMessage()).isEqualTo(null);
    }

    @Test(expected = NullSaverException.class)
    public void shouldThrowNullSaverExceptionWhenCreateLoggerControllerWithNullSaver() throws NullSaverException {
        new LoggerController(null);
    }
}
