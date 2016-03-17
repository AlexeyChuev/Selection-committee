package net.chuiev.selcommittee.controller.admin;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.Enrollee;
import net.chuiev.selcommittee.repository.EnrolleeRepository;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Forward command, which redirect user to page, where he can unblock enrollee.
 *
 * @author Oleksii Chuiev
 */
public class UnblockEnrolleeForwardCommand extends Command {
    private static final Logger LOG = Logger.getLogger(UnblockEnrolleeForwardCommand.class);

    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        int userRole = (int) session.getAttribute("userRole");
        EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
        List<Enrollee> blockedEnrollees = (List<Enrollee>) enrolleeRepository.findAllBlock();
        request.setAttribute("blockedEnrollees", blockedEnrollees);
        LOG.trace("setAttribute 'blockedEnrollees' " + blockedEnrollees);

        if (userRole == 1) {
            LOG.trace("User role " + userRole);
            result = "/WEB-INF/admin/unblockEnrollee.jsp";
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
