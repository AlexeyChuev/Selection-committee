package net.chuiev.selcommittee.controller.client;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.Enrollee;
import net.chuiev.selcommittee.entity.User;
import net.chuiev.selcommittee.repository.EnrolleeRepository;
import net.chuiev.selcommittee.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alex on 3/11/2016.
 */
public class HomeClientPageCommand extends Command {
    private static final long serialVersionUID = -3071536593627692473L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result=null;
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        UserRepository userRepository = new UserRepository();
        User user = userRepository.findUserByEmail(email);
        EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
        Enrollee enrollee = enrolleeRepository.findByUserId(user.getId());
        request.setAttribute("name", enrollee.getFullName());
        request.setAttribute("city", enrollee.getCity());
        request.setAttribute("region", enrollee.getRegion());
        request.setAttribute("school", enrollee.getSchoolName());

        session.setAttribute("enrolleeID", enrollee.getId());


        int userRole = (int)session.getAttribute("userRole");

        if(userRole==2)result="/WEB-INF/client/clientHome.jsp";
        return result;
    }
}
