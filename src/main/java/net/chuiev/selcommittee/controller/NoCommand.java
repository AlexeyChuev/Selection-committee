package net.chuiev.selcommittee.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for cases, then there are no command, that find by name
 *
 * @author Oleksii Chuiev
 *
 */
public class NoCommand extends Command {
    private static final Logger LOG = Logger.getLogger(NoCommand.class);

    private static final long serialVersionUID = -2785976616686657267L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        LOG.error("Set request attribute: 'errorMessage' = " + errorMessage);

        LOG.debug("Command execution finished");
        return "/error.jsp";
    }
}
