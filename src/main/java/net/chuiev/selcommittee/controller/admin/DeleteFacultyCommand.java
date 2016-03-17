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
import java.util.Enumeration;
import java.util.List;

/**
 * Command for deleting faculty. Available for admin.
 *
 * @author Oleksii Chuiev
 */
public class DeleteFacultyCommand extends Command {
    private static final Logger LOG = Logger.getLogger(DeleteFacultyCommand.class);

    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        int facultyId = Integer.parseInt(request.getParameter("facultiesSelect"));
        FacultyRepository facultyRepository = new FacultyRepository();
        Faculty faculty = facultyRepository.get(facultyId);
        request.setAttribute("facultyName", faculty.getName());
        LOG.trace("Set attribute 'facultyName' " + faculty.getName());

        facultyRepository.delete(facultyId);
        LOG.trace("Faculty was deleted " + faculty.getName());

        int userRole = (int) session.getAttribute("userRole");
        if (userRole == 1) {
            LOG.trace("User role " + userRole);
            result = "/WEB-INF/admin/successDeleteFaculty.jsp";
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
