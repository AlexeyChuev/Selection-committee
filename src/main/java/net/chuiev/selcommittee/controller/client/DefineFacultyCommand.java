package net.chuiev.selcommittee.controller.client;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.entity.FacultySubject;
import net.chuiev.selcommittee.entity.Subject;
import net.chuiev.selcommittee.repository.FacultyRepository;
import net.chuiev.selcommittee.repository.FacultySubjectRepository;
import net.chuiev.selcommittee.repository.SubjectRepository;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Command define faculty, which was selected by user.
 *
 * @author Oleksii Chuiev
 */
public class DefineFacultyCommand extends Command {
    private static final Logger LOG = Logger.getLogger(DefineFacultyCommand.class);

    private static final long serialVersionUID = 2999388021320200831L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        int facultyId = Integer.parseInt(request.getParameter("facultyid"));
        FacultyRepository facultyRepository = new FacultyRepository();
        Faculty faculty = facultyRepository.get(facultyId);
        LOG.trace("faculty found " + faculty);

        FacultySubjectRepository facultySubjectRepository = new FacultySubjectRepository();
        List<FacultySubject> facultySubjects = (List<FacultySubject>) facultySubjectRepository.findSubjectsForFaculty(faculty);

        SubjectRepository subjectRepository = new SubjectRepository();
        Subject exam1 = subjectRepository.get(facultySubjects.get(0).getSubjectId());
        Subject exam2 = subjectRepository.get(facultySubjects.get(1).getSubjectId());
        Subject exam3 = subjectRepository.get(facultySubjects.get(2).getSubjectId());

        request.setAttribute("facultySubject1", exam1);
        LOG.trace("setAttribute facultySubject1 " + exam1);
        request.setAttribute("facultySubject2", exam2);
        LOG.trace("setAttribute facultySubject2 " + exam2);
        request.setAttribute("facultySubject3", exam3);
        LOG.trace("setAttribute facultySubject3 " + exam3);
        request.setAttribute("faculty", faculty);
        LOG.trace("setAttribute faculty " + faculty);

        int userRole = (int) session.getAttribute("userRole");
        if (userRole == 2) {
            LOG.trace("User role " + userRole);
            result = "/WEB-INF/client/applyFaculty.jsp";
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
