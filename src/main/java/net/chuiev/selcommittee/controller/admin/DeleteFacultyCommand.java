package net.chuiev.selcommittee.controller.admin;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.repository.FacultyRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Alex on 3/12/2016.
 */
public class DeleteFacultyCommand extends Command {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result=null;
        HttpSession session = request.getSession(false);

        int facultyId = Integer.parseInt(request.getParameter("facultiesSelect"));
        FacultyRepository facultyRepository = new FacultyRepository();
        Faculty faculty =facultyRepository.get(facultyId);
        request.setAttribute("facultyName", faculty.getName());
        facultyRepository.delete(facultyId);

        int userRole = (int)session.getAttribute("userRole");

        if(userRole==1)result="/WEB-INF/admin/successDeleteFaculty.jsp";
        return result;
    }
}
