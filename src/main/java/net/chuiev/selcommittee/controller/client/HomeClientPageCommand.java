package net.chuiev.selcommittee.controller.client;

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
 * Forward enrollee to his home page profile.
 *
 * @author Oleksii Chuiev
 */
public class HomeClientPageCommand extends Command {
    private static final Logger LOG = Logger.getLogger(HomeClientPageCommand.class);

    private static final long serialVersionUID = -3071536593627692473L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        UserRepository userRepository = new UserRepository();
        User user = userRepository.findUserByEmail(email);
        EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
        Enrollee enrollee = enrolleeRepository.findByUserId(user.getId());
        request.setAttribute("name", enrollee.getFullName());
        LOG.trace("setAttribute name " + enrollee.getFullName());
        request.setAttribute("city", enrollee.getCity());
        LOG.trace("setAttribute city " + enrollee.getCity());
        request.setAttribute("region", enrollee.getRegion());
        LOG.trace("setAttribute region " + enrollee.getRegion());
        request.setAttribute("school", enrollee.getSchoolName());
        LOG.trace("setAttribute school " + enrollee.getSchoolName());

        session.setAttribute("enrolleeID", enrollee.getId());
        LOG.trace("setAttribute enrolleeID " + enrollee.getId());

        int userRole = (int) session.getAttribute("userRole");
        if (userRole == 2) {
            LOG.trace("User role " + userRole);
            result = "/WEB-INF/client/clientHome.jsp";
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
