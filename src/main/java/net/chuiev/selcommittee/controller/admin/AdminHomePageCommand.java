package net.chuiev.selcommittee.controller.admin;

import net.chuiev.selcommittee.controller.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command redirect admin to his profile page.
 *
 * @author Oleksii Chuiev
 */
public class AdminHomePageCommand extends Command {
    private static final Logger LOG = Logger.getLogger(AdminHomePageCommand.class);

    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        int userRole = (int) session.getAttribute("userRole");
        if (userRole == 1) {
            LOG.trace("User role " + userRole);
            result = "/WEB-INF/admin/adminHome.jsp";
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
