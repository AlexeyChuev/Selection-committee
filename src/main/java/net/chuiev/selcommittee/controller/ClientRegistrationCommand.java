package net.chuiev.selcommittee.controller;

import net.chuiev.selcommittee.entity.Enrollee;
import net.chuiev.selcommittee.entity.User;
import net.chuiev.selcommittee.repository.EnrolleeRepository;
import net.chuiev.selcommittee.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Blob;

/**
 * Created by Alex on 3/10/2016.
 */
public class ClientRegistrationCommand extends Command {
    private static final long serialVersionUID = -1071536593611692473L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return doPost(request,response);
    }

    private String doPost(HttpServletRequest request,
                          HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("full_name");

        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String schoolName = request.getParameter("school");

        String result = null;

        User newUser = new User(2, email, password, false);
        UserRepository userRepository = new UserRepository();
        userRepository.create(newUser);

        User addedUser = userRepository.findUserByEmail(email);

        Enrollee enrollee = new Enrollee(fullName, city, region,schoolName, addedUser.getId());
        EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
        enrolleeRepository.create(enrollee);

        /*MailUtils.sendConfirmationEmail(user);
        request.setAttribute("successfulMessage",
                "Your account was created. Check your email and confirm your registration.");*/

        result = "/WEB-INF/client/clientHome.jsp";
        return result;
    }

}
