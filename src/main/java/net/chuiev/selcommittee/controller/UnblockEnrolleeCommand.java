package net.chuiev.selcommittee.controller;

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
 * Created by Alex on 3/12/2016.
 */
public class UnblockEnrolleeCommand extends Command {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result = null;
        HttpSession session = request.getSession(false);

        int enrolleeId = Integer.parseInt(request.getParameter("enrolleeId"));

        EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
        Enrollee enrollee = enrolleeRepository.get(enrolleeId);
        if (enrollee != null) {
            UserRepository userRepository = new UserRepository();
            User user = userRepository.get(enrollee.getUserId());
            if (user != null) {
                user.setBlocked(false);
                userRepository.update(user);
                int userRole = (int) session.getAttribute("userRole");
                if (userRole == 1) {
                    request.setAttribute("enrolleeName", enrollee.getFullName());
                    result = "/WEB-INF/admin/successUnblockEnrollee.jsp";
                }
            }
        }
        return result;
    }
}
