package net.chuiev.selcommittee.controller.client;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.Enrollee;
import net.chuiev.selcommittee.entity.Submission;
import net.chuiev.selcommittee.entity.SubmissionSubject;
import net.chuiev.selcommittee.entity.User;
import net.chuiev.selcommittee.repository.EnrolleeRepository;
import net.chuiev.selcommittee.repository.SubmissionRepository;
import net.chuiev.selcommittee.repository.SubmissionSubjectRepository;
import net.chuiev.selcommittee.repository.UserRepository;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command for enrollee, which wants to add submission on selected faculty.
 *
 * @author Oleksii Chuiev
 */
public class EnrolleeApplyCommand extends Command {
    private static final Logger LOG = Logger.getLogger(EnrolleeApplyCommand.class);

    private static final long serialVersionUID = -2671536593611692473L;
    private static SubmissionSubjectRepository submissionSubjectRepository = new SubmissionSubjectRepository();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        UserRepository userRepository = new UserRepository();
        User user = userRepository.findUserByEmail(email);
        LOG.trace("User found " + user);

        EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
        Enrollee enrollee = enrolleeRepository.findByUserId(user.getId());
        LOG.trace("Enrollee found " + enrollee);

        int facultyId = Integer.parseInt(request.getParameter("facultyid"));
        int enrolleeId = enrollee.getId();

        SubmissionRepository submissionRepository = new SubmissionRepository();
        Submission testSubmission = submissionRepository.getByFacultyAndEnrolle(facultyId, enrolleeId);
        if (testSubmission != null) {
            LOG.error("Submission already exists");
            return "/WEB-INF/errors/errorSubmissionAlreadyExist.jsp";
        }
        Submission submission = new Submission();
        submission.setFacultyId(facultyId);
        submission.setEnrolleeId(enrolleeId);
        submissionRepository.create(submission);

        Submission justAddedSubmission = submissionRepository.getByFacultyAndEnrolle(facultyId, enrolleeId);
        LOG.trace("submission created " + submission);
        //exams and them marks
        int exam1Mark = Integer.parseInt(request.getParameter("exam1"));
        int exam2Mark = Integer.parseInt(request.getParameter("exam2"));
        int exam3Mark = Integer.parseInt(request.getParameter("exam3"));
        int exam1Id = Integer.parseInt(request.getParameter("exam1id"));
        int exam2Id = Integer.parseInt(request.getParameter("exam2id"));
        int exam3Id = Integer.parseInt(request.getParameter("exam3id"));

        addSubmissionSubjectExam(justAddedSubmission.getId(), exam1Id, exam1Mark);
        addSubmissionSubjectExam(justAddedSubmission.getId(), exam2Id, exam2Mark);
        addSubmissionSubjectExam(justAddedSubmission.getId(), exam3Id, exam3Mark);
        LOG.trace("SubmissionSubjectExam (3) created ");

        //certificate and its marks
        //id subject = 1 --> maths
        int maths = Integer.parseInt(request.getParameter("maths"));
        submissionSubjectRepository.create(new SubmissionSubject(justAddedSubmission.getId(), 1, maths, 2));
        //id subject = 2 --> history
        int history = Integer.parseInt(request.getParameter("history"));
        submissionSubjectRepository.create(new SubmissionSubject(justAddedSubmission.getId(), 2, history, 2));
        //id subject = 3 --> geography
        int geography = Integer.parseInt(request.getParameter("geography"));
        submissionSubjectRepository.create(new SubmissionSubject(justAddedSubmission.getId(), 3, geography, 2));
        //id subject = 4 --> chemistry
        int chemistry = Integer.parseInt(request.getParameter("chemistry"));
        submissionSubjectRepository.create(new SubmissionSubject(justAddedSubmission.getId(), 4, chemistry, 2));
        //id subject = 5 --> physics
        int physics = Integer.parseInt(request.getParameter("physics"));
        submissionSubjectRepository.create(new SubmissionSubject(justAddedSubmission.getId(), 5, physics, 2));
        //id subject = 6 --> english
        int english = Integer.parseInt(request.getParameter("english"));
        submissionSubjectRepository.create(new SubmissionSubject(justAddedSubmission.getId(), 6, english, 2));
        //id subject = 7 --> ukrainian
        int ukrainian = Integer.parseInt(request.getParameter("ukrainian"));
        submissionSubjectRepository.create(new SubmissionSubject(justAddedSubmission.getId(), 7, ukrainian, 2));
        //id subject = 8 --> biology
        int biology = Integer.parseInt(request.getParameter("biology"));
        submissionSubjectRepository.create(new SubmissionSubject(justAddedSubmission.getId(), 8, biology, 2));
        //id subject = 9 --> computer_science
        int computer_science = Integer.parseInt(request.getParameter("computer_science"));
        submissionSubjectRepository.create(new SubmissionSubject(justAddedSubmission.getId(), 9, computer_science, 2));
        //id subject = 10 --> economics
        int economics = Integer.parseInt(request.getParameter("economics"));
        submissionSubjectRepository.create(new SubmissionSubject(justAddedSubmission.getId(), 10, economics, 2));
        LOG.trace("SubmissionSubjectCertificate (10) created ");

        result = "/WEB-INF/client/successAddSubmission.jsp";

        LOG.debug("Command execution finished");
        return result;
    }


    /**
     * Add submissionSubject to its table in DB.
     *
     * @param grade = mark
     *        gradetype = 1, it's for exam
     */
    private static void addSubmissionSubjectExam(int submissionId, int subjectId, int grade) {
        submissionSubjectRepository.create(new SubmissionSubject(submissionId, subjectId, grade, 1));
    }
}
