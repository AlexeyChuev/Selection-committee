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
 * Command for updating faculty. Available to admin.
 *
 * @author Oleksii Chuiev
 */
public class UpdateFacultyCommand extends Command {
    private static final Logger LOG = Logger.getLogger(UpdateFacultyCommand.class);

    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        int facultyId = Integer.parseInt(request.getParameter("facultyid"));
        String facultyName = request.getParameter("newName");
        int budget = Integer.parseInt(request.getParameter("budgetVolume"));
        int total = Integer.parseInt(request.getParameter("totalVolume"));
        if (budget > total) {
            LOG.error("errorMeaasge: budget > total");
            return "/WEB-INF/errors/adminErrorBudgetBiggerTotal.jsp";
        }
        FacultyRepository facultyRepository = new FacultyRepository();
        facultyRepository.update(new Faculty(facultyId, facultyName, budget, total));

        Faculty justAddedFaculty = facultyRepository.findFacultyByName(facultyName);
        LOG.trace("Faculty updated " + justAddedFaculty);

        if (justAddedFaculty != null) {
            int userRole = (int) session.getAttribute("userRole");
            request.setAttribute("facultyName", justAddedFaculty.getName());
            LOG.trace("setAttribute 'facultyName' " + justAddedFaculty.getName());

            if (userRole == 1) {
                LOG.trace("User role " + userRole);
                result = "/WEB-INF/admin/successUpdateFaculty.jsp";
            }
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
