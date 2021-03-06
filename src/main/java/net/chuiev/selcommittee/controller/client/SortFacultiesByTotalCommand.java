package net.chuiev.selcommittee.controller.client;

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
 * Command sort list of faculties on page with all faculties by total number of seats.
 *
 * @author Oleksii Chuiev
 */
public class SortFacultiesByTotalCommand extends Command {
    private static final Logger LOG = Logger.getLogger(SortFacultiesByTotalCommand.class);

    private static final long serialVersionUID = 2295388000020200831L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        FacultyRepository facultyRepository = new FacultyRepository();
        List<Faculty> faculties = (List<Faculty>) facultyRepository.sortedFacultiesByTotalVolume();
        request.setAttribute("faculties", faculties);
        LOG.trace("setAttribute faculties " + faculties);

        int userRole = (int) session.getAttribute("userRole");
        if (userRole == 2) {
            result = "/WEB-INF/client/allFaculties.jsp";
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
