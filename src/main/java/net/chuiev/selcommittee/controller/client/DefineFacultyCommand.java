package net.chuiev.selcommittee.controller.client;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.entity.FacultySubject;
import net.chuiev.selcommittee.entity.Subject;
import net.chuiev.selcommittee.repository.FacultyRepository;
import net.chuiev.selcommittee.repository.FacultySubjectRepository;
import net.chuiev.selcommittee.repository.SubjectRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex on 3/11/2016.
 */
public class DefineFacultyCommand extends Command {
    private static final long serialVersionUID = 2999388021320200831L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result=null;
        HttpSession session = request.getSession(false);

        int facultyId = Integer.parseInt(request.getParameter("facultyid"));

        FacultyRepository facultyRepository = new FacultyRepository();
        Faculty faculty = facultyRepository.get(facultyId);

        FacultySubjectRepository facultySubjectRepository = new FacultySubjectRepository();
        List<FacultySubject> facultySubjects = (List<FacultySubject>) facultySubjectRepository.findSubjectsForFaculty(faculty);

        SubjectRepository subjectRepository = new SubjectRepository();
        Subject exam1 = subjectRepository.get(facultySubjects.get(0).getSubjectId());
        Subject exam2 = subjectRepository.get(facultySubjects.get(1).getSubjectId());
        Subject exam3 = subjectRepository.get(facultySubjects.get(2).getSubjectId());

        request.setAttribute("facultySubject1", exam1);
        request.setAttribute("facultySubject2", exam2);
        request.setAttribute("facultySubject3", exam3);
        request.setAttribute("faculty", faculty);

        int userRole = (int)session.getAttribute("userRole");

        if(userRole==2) result="/WEB-INF/client/applyFaculty.jsp";


        return result;
    }
}
