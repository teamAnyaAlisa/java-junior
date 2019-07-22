package com.acme.edu.unitTests.LoggerControllerTests;

import com.acme.edu.command.Command;
import com.acme.edu.command.StringCommand;
import com.acme.edu.logger.LoggerController;
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
        sut = new LoggerController(saverStub);
    }

    @Test
    public void shouldCallSaverSaveWhenLogWithString() {
        String message = "test string";

        sut.log(message);

        verify(saverStub).save(message);
    }

    @Test
    public void shouldSetCurrentMessageToInputCommandWhenLogWithCommandForFirstTime() {
        Command stub1 = new StringCommand("test string 1", saverStub);

        sut.log(stub1);

        assertThat(sut.getCurrentMessage()).isEqualTo(stub1);
    }

    @Test
    public void shouldSetCurrentMessageToInputCommandAndSavePreviousWhenLogWithCommand() {
        Command stub1 = new StringCommand("test string 1", saverStub);
        Command stub2 = mock(StringCommand.class);

        when(stub2.save(stub1)).thenReturn(stub1);

        sut.log(stub2);
        sut.log(stub1);

        verify(stub2).save(stub1);
        assertThat(sut.getCurrentMessage()).isEqualTo(stub1);
    }

    @Test
    public void shouldCallCurrentMessageFlushAndFlushStateWhenFlush() {
        Command stub = mock(StringCommand.class);

        sut.log(stub);
        sut.flush();

        verify(stub).flush();
        assertThat(sut.getCurrentMessage()).isEqualTo(null);
    }
}
