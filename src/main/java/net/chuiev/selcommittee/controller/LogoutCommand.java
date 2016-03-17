package net.chuiev.selcommittee.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command for logout user in system.
 *
 * @author Oleksii Chuiev
 */
public class LogoutCommand extends Command {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    private static final long serialVersionUID = -2785976616686657267L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        LOG.debug("Command execution finished");
        return "/index.jsp";
    }
}
