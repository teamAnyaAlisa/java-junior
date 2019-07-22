package com.acme.edu.unitTests.LogSaverTests;

import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.saver.LogConsoleSaver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static java.lang.System.lineSeparator;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogConsoleSaverTest implements SysoutCaptureAndAssertionAbility {
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldPrintStringToConsoleWhenSave() {
        LogConsoleSaver sut = new LogConsoleSaver();

        sut.save("test string 1");

        assertSysoutEquals("test string 1" + lineSeparator());
    }
}
