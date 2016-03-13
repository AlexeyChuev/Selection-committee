package net.chuiev.selcommittee.controller.client;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.Enrollee;
import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.entity.User;
import net.chuiev.selcommittee.repository.EnrolleeRepository;
import net.chuiev.selcommittee.repository.FacultyRepository;
import net.chuiev.selcommittee.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex on 3/11/2016.
 */
public class ViewAllFacultiesCommand extends Command {
    private static final long serialVersionUID = 2295388021320200832L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result=null;
        HttpSession session = request.getSession(false);
        FacultyRepository facultyRepository = new FacultyRepository();
        List<Faculty> faculties = (List<Faculty>) facultyRepository.findAll();
        request.setAttribute("faculties", faculties);


        int userRole = (int)session.getAttribute("userRole");

        if(userRole==2)result="/WEB-INF/client/allFaculties.jsp";
        return result;
    }
}
