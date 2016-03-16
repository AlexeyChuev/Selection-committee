package net.chuiev.selcommittee.controller.admin;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.AdmissionRegisterRecord;
import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.entity.User;
import net.chuiev.selcommittee.repository.UserRepository;
import net.chuiev.selcommittee.services.AdmissionRegisterService;
import net.chuiev.selcommittee.services.email.MailThread;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Alex on 3/16/2016.
 */
public class SendEmailsCommand extends Command {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result = null;
        HttpSession session = request.getSession(false);
        AdmissionRegisterService admissionRegisterService = new AdmissionRegisterService();
        Map<Faculty, List<AdmissionRegisterRecord>> facultiesAndTheirAdmissions = admissionRegisterService.getFacultiesAndTheirAdmissions();
        for (Map.Entry<Faculty, List<AdmissionRegisterRecord>> map : facultiesAndTheirAdmissions.entrySet()) {
            Faculty faculty = map.getKey();
            for (AdmissionRegisterRecord record : map.getValue()) {
                User user = new UserRepository().get(record.getEnrollee().getUserId());
                if ("Entered on a budget".equals(record.getAdmissionState())) {
                    String resultEmail = "<" + user.getEmail() + ">";
                    String textEmail = "Dear, " + record.getEnrollee().getFullName() +
                            ". You was entered on a budget on a " + faculty.getName() + ".";
                    new MailThread(resultEmail, "Results of admission to University", textEmail, new Properties()).start();
                }
                if ("Entered on a contract".equals(record.getAdmissionState())) {
                    String resultEmail = "<" + user.getEmail() + ">";
                    String textEmail = "Dear, " + record.getEnrollee().getFullName() +
                            ". You was entered on a contract on a " + faculty.getName() + ".";
                    new MailThread(resultEmail, "Results of admission to University", textEmail, new Properties()).start();
                }
                if ("Not admitted".equals(record.getAdmissionState())) {
                    String resultEmail = "<" + user.getEmail() + ">";
                    String textEmail = "Dear, " + record.getEnrollee().getFullName() +
                            ". Sorry, but your grades didn't good enough to enter to the University on " + faculty.getName() + ".";
                    new MailThread(resultEmail, "Results of admission to University", textEmail, new Properties()).start();
                }
            }
        }
        int userRole = (int) session.getAttribute("userRole");
        if (userRole == 1) {
            result = "/WEB-INF/admin/sendEmailsInProgress.jsp";
        }
        return result;
    }
}
