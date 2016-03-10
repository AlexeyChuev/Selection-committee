package net.chuiev.selcommittee.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 3/10/2016.
 */
public class CommandManager {

    private static Map<String, Command> commands = new HashMap<String, Command>();

    static {
        commands.put("login", new LoginCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("client_registration", new ClientRegistrationCommand());
        commands.put("logout", new LogoutCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
