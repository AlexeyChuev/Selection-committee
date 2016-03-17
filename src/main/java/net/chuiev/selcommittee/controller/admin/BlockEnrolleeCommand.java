package net.chuiev.selcommittee.controller.admin;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.Enrollee;

import net.chuiev.selcommittee.entity.User;
import net.chuiev.selcommittee.repository.EnrolleeRepository;

import net.chuiev.selcommittee.repository.UserRepository;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command which uses to block enrollee. It available for admin.
 *
 * @author Oleksii Chuiev
 */
public class BlockEnrolleeCommand extends Command {
    private static final Logger LOG = Logger.getLogger(BlockEnrolleeCommand.class);

    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        int enrolleeId = Integer.parseInt(request.getParameter("enrolleeId"));
        EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
        Enrollee enrollee = enrolleeRepository.get(enrolleeId);
        LOG.trace("Enrollee was found  " + enrollee);

        if (enrollee != null) {
            UserRepository userRepository = new UserRepository();
            User user = userRepository.get(enrollee.getUserId());
            LOG.trace("User was found  " + user);

            if (user != null) {
                user.setBlocked(true);
                userRepository.update(user);
                LOG.trace("User was blocked  " + user);

                int userRole = (int) session.getAttribute("userRole");
                if (userRole == 1) {
                    request.setAttribute("enrolleeName", enrollee.getFullName());
                    LOG.trace("Set Attribute: 'enrolleeName' " + enrollee.getFullName());
                    LOG.trace("User role " + userRole);
                    result = "/WEB-INF/admin/successBlockEnrollee.jsp";
                }
            }
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
