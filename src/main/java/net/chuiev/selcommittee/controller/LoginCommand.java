package net.chuiev.selcommittee.controller;

import net.chuiev.selcommittee.entity.Enrollee;
import net.chuiev.selcommittee.entity.Role;
import net.chuiev.selcommittee.entity.User;
import net.chuiev.selcommittee.repository.EnrolleeRepository;
import net.chuiev.selcommittee.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alex on 3/10/2016.
 */
public class LoginCommand extends Command {
    private static final long serialVersionUID = -3071536593627692473L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result = null;
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty()
                || password.isEmpty()) {
            result = "/WEB-INF/errors/errorUserNotExist.jsp";
        }

        UserRepository userRepository = new UserRepository();
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            result = "/WEB-INF/errors/errorUserNotExist.jsp";
        } else {
            if(!user.getPassword().equals(password))return  "/WEB-INF/errors/errorUserNotExist.jsp";
            int userRole = user.getRole();

            if (userRole == 1) {
                result = "/WEB-INF/admin/adminHome.jsp";
            }
            if (userRole == 2) {
                if(user.isBlocked())result= "/WEB-INF/errors/errorBlockedUser.jsp";
                else result = "/WEB-INF/client/clientHome.jsp";
            }
            session.setAttribute("user", user);
            session.setAttribute("userRole", userRole);
        }
        return result;
    }


}
