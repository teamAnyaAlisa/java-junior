package com.acme.edu.saver;

import com.acme.edu.customExceptions.LogFileSaverException;
import com.acme.edu.customExceptions.LogSaverException;

import java.io.*;

public class LogFileSaver implements LogSaver {
    private File filePath;
    private String fileEncoding = "windows-1251";
    // TODO: final
    private BufferedWriter outLogStream;

    public LogFileSaver(String filePath) throws LogSaverException {
        this(filePath, "windows-1251");
    }

    public LogFileSaver(String filePath, String fileEncoding) throws LogSaverException {
        if ((fileEncoding != null) && !fileEncoding.equals("")) {
            this.fileEncoding = fileEncoding;
        }

        if ((filePath == null) || filePath.equals("")) {
            throw new LogFileSaverException("filepath should be not empty string");
        }

        this.filePath = new File(filePath);
        outLogStream = openFile();
    }

    private BufferedWriter openFile() throws LogSaverException {
        BufferedWriter outLogStream = null;
        try {
             outLogStream = new BufferedWriter(
                       new OutputStreamWriter(
                           new BufferedOutputStream(
                               new FileOutputStream(filePath)), fileEncoding));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LogFileSaverException("file with given path is not found", e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new LogFileSaverException("given encoding is not supported", e);
        }
        return outLogStream;
    }

    @Override
    public void save(String message) throws LogSaverException {
        try {
            outLogStream.write(message);
            outLogStream.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new LogFileSaverException("fails writing to the given file", e);
        }
    }

    @Override
    public void close() throws LogSaverException {
        try {
            outLogStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new LogFileSaverException("fails closing file", e);
        }
    }
}
