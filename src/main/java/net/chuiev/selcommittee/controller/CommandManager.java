package net.chuiev.selcommittee.controller;

import net.chuiev.selcommittee.controller.admin.*;
import net.chuiev.selcommittee.controller.client.*;

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

        //admin
        commands.put("adminHomePage", new AdminHomePageCommand());
        commands.put("addNewFacultyForward", new AddNewFacultyForwardCommand());
        commands.put("addNewFaculty", new AddNewFacultyCommand());
        commands.put("deleteFacultyForward", new DeleteFacultyForwardCommand());
        commands.put("deleteFaculty", new DeleteFacultyCommand());
        commands.put("editFacultyForward", new EditFacultyForwardCommand());
        commands.put("addNewInformationAboutFaculty", new AddNewInformationAboutFacultyCommand());
        commands.put("updateFaculty", new UpdateFacultyCommand());
        commands.put("blockEnrolleeForward", new BlockEnrolleeForwardCommand());
        commands.put("blockEnrollee", new BlockEnrolleeCommand());
        commands.put("unblockEnrolleeForward", new UnblockEnrolleeForwardCommand());
        commands.put("unblockEnrollee", new UnblockEnrolleeCommand());
        commands.put("createRegister", new CreateRegisterCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
