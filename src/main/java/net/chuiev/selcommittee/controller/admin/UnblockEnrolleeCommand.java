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
 * Command for unblocking enrollee. Available for admin.
 *
 * @author Oleksii Chuiev
 */
public class UnblockEnrolleeCommand extends Command {
    private static final Logger LOG = Logger.getLogger(UnblockEnrolleeCommand.class);

    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        int enrolleeId = Integer.parseInt(request.getParameter("enrolleeId"));
        EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
        Enrollee enrollee = enrolleeRepository.get(enrolleeId);
        LOG.trace("Enrollee was found " + enrollee);

        if (enrollee != null) {
            UserRepository userRepository = new UserRepository();
            User user = userRepository.get(enrollee.getUserId());
            if (user != null) {
                user.setBlocked(false);
                userRepository.update(user);
                LOG.trace("user was updated " + user);

                int userRole = (int) session.getAttribute("userRole");
                if (userRole == 1) {
                    request.setAttribute("enrolleeName", enrollee.getFullName());
                    LOG.trace("setAttribute 'enrolleeName' " + enrollee.getFullName());
                    result = "/WEB-INF/admin/successUnblockEnrollee.jsp";
                }
            }
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
