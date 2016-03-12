package net.chuiev.selcommittee.controller;

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
public class BlockEnrolleeForwardCommand extends Command {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result=null;
        HttpSession session = request.getSession(false);

        int userRole = (int)session.getAttribute("userRole");
        EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
        List<Enrollee> unblockEnrollees = (List<Enrollee>) enrolleeRepository.findAllUnblock();
        request.setAttribute("unblockEnrollees", unblockEnrollees);

        if(userRole==1)result="/WEB-INF/admin/blockEnrollee.jsp";
        return result;
    }
}
