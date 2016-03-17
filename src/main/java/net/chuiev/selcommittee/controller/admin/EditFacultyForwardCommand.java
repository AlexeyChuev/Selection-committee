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
import java.util.List;

/**
 * Forward command, which redirect user to page,
 * where he can edit information about faculty.
 *
 * @author Oleksii Chuiev
 */
public class EditFacultyForwardCommand extends Command {
    private static final Logger LOG = Logger.getLogger(EditFacultyForwardCommand.class);

    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        FacultyRepository facultyRepository = new FacultyRepository();
        List<Faculty> faculties = (List<Faculty>) facultyRepository.findAll();
        LOG.trace("Find faculties " + faculties);
        request.setAttribute("faculties", faculties);
        LOG.trace("Set attribute 'faculties' " + faculties);

        int userRole = (int) session.getAttribute("userRole");
        if (userRole == 1) {
            LOG.trace("User role " + userRole);
            result = "/WEB-INF/admin/editFaculty.jsp";
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
