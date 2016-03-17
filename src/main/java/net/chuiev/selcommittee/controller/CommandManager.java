package net.chuiev.selcommittee.controller;

import net.chuiev.selcommittee.controller.admin.*;
import net.chuiev.selcommittee.controller.client.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for managing commands for controller.
 *
 * @author Oleksii Chuiev
 *
 */
public class CommandManager {
    private static final Logger LOG = Logger.getLogger(CommandManager.class);

    private static Map<String, Command> commands = new HashMap<String, Command>();

    static {
        commands.put("login", new LoginCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("logout", new LogoutCommand());

        commands.put("client_registration", new ClientRegistrationCommand());
        commands.put("clientHomePage", new HomeClientPageCommand());
        commands.put("viewAllFaculties", new ViewAllFacultiesCommand());
        commands.put("sortFacultiesAZ", new SortFacultiesAZCommand());
        commands.put("sortFacultiesZA", new SortFacultiesZACommand());
        commands.put("sortFacultiesByBudget", new SortFacultiesByBudgetCommand());
        commands.put("sortFacultiesByTotal", new SortFacultiesByTotalCommand());
        commands.put("defineFaculty", new DefineFacultyCommand());
        commands.put("applySubmission", new EnrolleeApplyCommand());

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
        commands.put("sendEmails", new SendEmailsCommand());

        LOG.debug("Container commands was initialized");
    }

    /**
     * Returns command using command name
     *
     * @param command
     *            Name of the command.
     * @return Command object or <code>noCommand</code>
     *          object, if command wasn't find by name.
     */
    public static Command get(String command) {
        if (command == null || !commands.containsKey(command)) {
            LOG.trace("Can't find command = " + command);
            return commands.get("noCommand");
        }
        return commands.get(command);
    }
}
