package net.chuiev.selcommittee.controller.admin;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.Enrollee;
import net.chuiev.selcommittee.repository.EnrolleeRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex on 3/12/2016.
 */
public class UnblockEnrolleeForwardCommand extends Command {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result=null;
        HttpSession session = request.getSession(false);

        int userRole = (int)session.getAttribute("userRole");
        EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
        List<Enrollee> blockedEnrollees = (List<Enrollee>) enrolleeRepository.findAllBlock();
        request.setAttribute("blockedEnrollees", blockedEnrollees);

        if(userRole==1)result="/WEB-INF/admin/unblockEnrollee.jsp";
        return result;
    }
}
