package net.chuiev.selcommittee.controller;

import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.repository.FacultyRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex on 3/12/2016.
 */
public class EditFacultyForwardCommand extends Command {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result=null;
        HttpSession session = request.getSession(false);
        FacultyRepository facultyRepository = new FacultyRepository();
        List<Faculty> faculties = (List<Faculty>) facultyRepository.findAll();
        request.setAttribute("faculties", faculties);

        int userRole = (int)session.getAttribute("userRole");

        if(userRole==1)result="/WEB-INF/admin/editFaculty.jsp";
        return result;
    }
}
