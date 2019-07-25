package com.acme.edu.saver;

import com.acme.edu.customExceptions.LogSaverException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class LogFileSaverTest {
    @Test
    public void shouldSaveStringToGivenFileWhenSave() throws LogSaverException, IOException {
        LogSaver saver = new LogFileSaver("test.txt");
        String stringStub = "test string";

        saver.save(stringStub);
        saver.close();

        // TODO write our own writer
        List<String> allLines = Files.readAllLines((new File("test.txt")).toPath());
        assertThat(allLines.get(allLines.size() - 1)).isEqualTo(stringStub);

        File testFile = new File("test.txt");
        //testFile.delete();
    }
}

// TODO: check other saver functions
