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
 * Command, which invokes then user try to firstly register in system.
 *
 * @author Oleksii Chuiev
 */
public class ClientRegistrationCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ClientRegistrationCommand.class);

    private static final long serialVersionUID = -1071536593611692473L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return doPost(request, response);
    }

    /**
     * Forward user to enrollee home page.
     *
     * @return path to page - enrollee profile
     */
    private String doPost(HttpServletRequest request,
                          HttpServletResponse response) {
        LOG.debug("Command execution starts");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("full_name");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String schoolName = request.getParameter("school");
        String result = null;

        User newUser = new User(2, email, password, false);
        UserRepository userRepository = new UserRepository();

        User userTest = userRepository.findUserByEmail(email);
        if (userTest != null) {
            LOG.error("ErrorMessage: User already exists");
            return "/WEB-INF/errors/errorUserAlreadyExist.jsp";
        }
        userRepository.create(newUser);

        User addedUser = userRepository.findUserByEmail(email);
        LOG.trace("newUser was created " + newUser);

        Enrollee enrollee = new Enrollee(fullName, city, region, schoolName, addedUser.getId());
        EnrolleeRepository enrolleeRepository = new EnrolleeRepository();

        Enrollee testEnrollee = enrolleeRepository.findByUserId(addedUser.getId());
        if (testEnrollee != null) {
            LOG.error("ErrorMessage: User already exists");
            return "/WEB-INF/errors/errorUserAlreadyExist.jsp";
        }
        enrolleeRepository.create(enrollee);
        LOG.trace("Enrollee was created " + enrollee);

        HttpSession session = request.getSession(true);
        session.setAttribute("email", email);
        session.setAttribute("userRole", 2);
        LOG.trace("Session set attribute 'email' " + email);
        LOG.trace("Session set attribute 'userRole' " + 2);

        result = "controller?command=clientHomePage";

        LOG.debug("Command execution finished");
        return result;
    }

}
