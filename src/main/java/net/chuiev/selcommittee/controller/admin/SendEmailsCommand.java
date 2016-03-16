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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Alex on 3/16/2016.
 */
public class SendEmailsCommand extends Command {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result = null;
        HttpSession session = request.getSession(false);

        ExecutorService service = Executors.newFixedThreadPool(5);

        AdmissionRegisterService admissionRegisterService = new AdmissionRegisterService();
        Map<Faculty, List<AdmissionRegisterRecord>> facultiesAndTheirAdmissions = admissionRegisterService.getFacultiesAndTheirAdmissions();
        for (Map.Entry<Faculty, List<AdmissionRegisterRecord>> map : facultiesAndTheirAdmissions.entrySet()) {
            Faculty faculty = map.getKey();

            for (AdmissionRegisterRecord record : map.getValue()) {
                User user = new UserRepository().get(record.getEnrollee().getUserId());
                String resultEmail = "<" + user.getEmail() + ">";
                if ("Accepted on a budget".equals(record.getAdmissionState())) {
                    String textEmail = "Dear, " + record.getEnrollee().getFullName() +
                            ". Congratulations! You was accepted on a budget on a " + faculty.getName() + ".";
                    service.execute(new MailThread(resultEmail, "Results of admission to University", textEmail, new Properties()));
                }
                if ("Accepted on a contract".equals(record.getAdmissionState())) {
                    String textEmail = "Dear, " + record.getEnrollee().getFullName() +
                            ". You was accepted on a contract on a " + faculty.getName() + ".";
                    service.execute(new MailThread(resultEmail, "Results of admission to University", textEmail, new Properties()));
                }
                if ("Not accepted".equals(record.getAdmissionState())) {
                    String textEmail = "Dear, " + record.getEnrollee().getFullName() +
                            ". Sorry, but your grades weren't good enough to be accepted to the University on " + faculty.getName() + ".";
                    service.execute(new MailThread(resultEmail, "Results of admission to University", textEmail, new Properties()));
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
