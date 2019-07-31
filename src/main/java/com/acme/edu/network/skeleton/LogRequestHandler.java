// парсит сообщение и вызывает нужный метод

package com.acme.edu.network.skeleton;

import com.acme.edu.command.*;

public class LogRequestHandler {
    public static Command parseClientMessage (String message) {
        String[] clientCommand = message.split(" ", 2);
        switch (clientCommand[0]) {
            case "int":
                return new IntCommand(Integer.parseInt(clientCommand[1]));
            case "byte":
                return new ByteCommand(Byte.parseByte(clientCommand[1]));
            case "char":
                return new CharCommand(clientCommand[1].charAt(0));
            case "string":
                return new StringCommand(clientCommand[1]);
            case "boolean":
                return new BooleanCommand(Boolean.parseBoolean(clientCommand[1]));
            case "flush":
                return new FlushCommand();
            default:
                return null;
        }
    }
}
