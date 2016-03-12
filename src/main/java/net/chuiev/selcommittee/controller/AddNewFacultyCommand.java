package net.chuiev.selcommittee.controller;

import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.repository.FacultyRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alex on 3/12/2016.
 */
public class AddNewFacultyCommand extends Command {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result = null;
        HttpSession session = request.getSession(false);

        String facultyName = request.getParameter("name");

        int budget = Integer.parseInt(request.getParameter("budget"));
        int total = Integer.parseInt(request.getParameter("total"));

        if (budget > total) return "/WEB-INF/errors/adminErrorBudgetBiggerTotal.jsp";
        FacultyRepository facultyRepository = new FacultyRepository();
        facultyRepository.create(new Faculty(facultyName, budget, total));

        Faculty justAddedFaculty = facultyRepository.findFacultyByName(facultyName);
        if (justAddedFaculty != null) {
            int userRole = (int) session.getAttribute("userRole");
            if (userRole == 1) result = "/WEB-INF/admin/succesAddFaculty.jsp";
        }
        return result;
    }
}
