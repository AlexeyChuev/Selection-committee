package net.chuiev.selcommittee.controller.admin;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.repository.FacultyRepository;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command, that add new faculty. This command can use only admin.
 *
 * @author Oleksii Chuiev
 */
public class AddNewFacultyCommand extends Command {
    private static final Logger LOG = Logger.getLogger(AddNewFacultyCommand.class);

    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        String facultyName = request.getParameter("name");
        int budget = Integer.parseInt(request.getParameter("budget"));
        int total = Integer.parseInt(request.getParameter("total"));

        if (budget > total) {
            LOG.error("errorMessage: Budget > total");
            return "/WEB-INF/errors/adminErrorBudgetBiggerTotal.jsp";
        }
        FacultyRepository facultyRepository = new FacultyRepository();
        facultyRepository.create(new Faculty(facultyName, budget, total));
        Faculty justAddedFaculty = facultyRepository.findFacultyByName(facultyName);
        LOG.trace("Faculty, that just was added: " + justAddedFaculty);

        if (justAddedFaculty != null) {
            int userRole = (int) session.getAttribute("userRole");
            if (userRole == 1) {
                LOG.trace("User role: " + userRole);
                result = "/WEB-INF/admin/succesAddFaculty.jsp";
            }
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
