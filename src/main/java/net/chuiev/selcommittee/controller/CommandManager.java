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
        commands.put("logout", new LogoutCommand());

        //client
        commands.put("client_registration", new ClientRegistrationCommand());
        commands.put("clientHomePage", new HomeClientPageCommand());
        commands.put("viewAllFaculties", new ViewAllFacultiesCommand());
        commands.put("sortFacultiesAZ", new SortFacultiesAZCommand());
        commands.put("sortFacultiesZA", new SortFacultiesZACommand());
        commands.put("sortFacultiesByBudget", new SortFacultiesByBudgetCommand());
        commands.put("sortFacultiesByTotal", new SortFacultiesByTotalCommand());
        commands.put("defineFaculty", new DefineFacultyCommand());
        commands.put("applySubmission", new EnrolleeApplyCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
