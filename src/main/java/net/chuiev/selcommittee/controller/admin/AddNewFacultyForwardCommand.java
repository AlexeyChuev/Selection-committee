package net.chuiev.selcommittee.controller.admin;

import net.chuiev.selcommittee.controller.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Forward command, which redirect user to page, where he can add faculty.
 *
 * @author Oleksii Chuiev
 */
public class AddNewFacultyForwardCommand extends Command {
    private static final Logger LOG = Logger.getLogger(AddNewFacultyForwardCommand.class);

    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        int userRole = (int) session.getAttribute("userRole");
        if (userRole == 1) {
            LOG.trace("User role " + userRole);
            result = "/WEB-INF/admin/addNewFaculty.jsp";
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
