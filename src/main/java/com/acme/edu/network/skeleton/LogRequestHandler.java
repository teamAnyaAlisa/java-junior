// парсит сообщение и вызывает нужный метод

package com.acme.edu.network.skeleton;

import com.acme.edu.command.*;
import com.acme.edu.customExceptions.LogSaverException;
import com.acme.edu.saver.LogConsoleSaver;
import com.acme.edu.saver.LogSaver;

public class LogRequestHandler {
    private LogSaver logSaver;

    public LogRequestHandler() throws LogSaverException {
        logSaver = new LogConsoleSaver();
    }

    public Command parseClientMessage (String message) {
        String[] clientCommand = message.split(" ", 2);
        System.out.println(clientCommand[0]+ " " + clientCommand[1]);
        System.out.println(logSaver.toString());
        switch (clientCommand[0]) {
            case "int":
                return new IntCommand(Integer.parseInt(clientCommand[1]), logSaver);
            case "byte":
                return new ByteCommand(Byte.parseByte(clientCommand[1]), logSaver);
            case "char":
                return new CharCommand(clientCommand[1].charAt(0), logSaver);
            case "String":
                return new StringCommand(clientCommand[1], logSaver);
            case "boolean":
                return new BooleanCommand(Boolean.parseBoolean(clientCommand[1]), logSaver);
            default:
                return null;
        }
    }
}
