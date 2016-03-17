package net.chuiev.selcommittee.controller;

import net.chuiev.selcommittee.entity.User;
import net.chuiev.selcommittee.repository.UserRepository;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command for login user in system.
 *
 * @author Oleksii Chuiev
 */
public class LoginCommand extends Command {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    private static final long serialVersionUID = -3071536593627692473L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty()
                || password.isEmpty()) {
            result = "/WEB-INF/errors/errorUserNotExist.jsp";
            LOG.error("errorMessage: email/password = null or empty");
        }

        UserRepository userRepository = new UserRepository();
        User user = userRepository.findUserByEmail(email);
        LOG.trace("User found: " + user);

        if (user == null) {
            result = "/WEB-INF/errors/errorUserNotExist.jsp";
            LOG.error("errorMessage: user = null");
        } else {
            if (!user.getPassword().equals(password)) {
                LOG.error("errorMessage: invalidate password");
                return "/WEB-INF/errors/errorUserNotExist.jsp";
            }
            int userRole = user.getRole();
            LOG.trace("User Role: " + userRole);

            if (userRole == 1) {
                session.setAttribute("user", user.getEmail());
                session.setAttribute("userRole", user.getRole());
                LOG.trace("Set the session attribute 'user' = " + user.getEmail());
                LOG.trace("Set the session attribute 'userRole' = " + user.getRole());
                result = "controller?command=adminHomePage";
            }
            if (userRole == 2) {
                if (user.isBlocked()) {
                    LOG.error("errorMessage: User blocked.");
                    result = "/WEB-INF/errors/errorBlockedUser.jsp";
                } else {
                    session.setAttribute("email", email);
                    session.setAttribute("userRole", userRole);
                    LOG.trace("Set the session attribute 'user' = " + user.getEmail());
                    LOG.trace("Set the session attribute 'userRole' = " + user.getRole());
                    result = "controller?command=clientHomePage";
                }
            }
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
