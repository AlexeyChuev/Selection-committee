package net.chuiev.selcommittee.controller.admin;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.AdmissionRegisterRecord;
import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.entity.User;
import net.chuiev.selcommittee.repository.UserRepository;
import net.chuiev.selcommittee.services.AdmissionRegisterService;
import net.chuiev.selcommittee.services.email.MailThread;
import org.apache.log4j.Logger;

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
 * Command, which admin invoke, then he wants to notify
 * all enrollees about results of admission.
 *
 * @author Oleksii Chuiev
 */
public class SendEmailsCommand extends Command {
    private static final Logger LOG = Logger.getLogger(SendEmailsCommand.class);

    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);

        ExecutorService service = Executors.newFixedThreadPool(5);
        LOG.trace("ExecutorService for 5 threads was created " + service);

        AdmissionRegisterService admissionRegisterService = new AdmissionRegisterService();
        Map<Faculty, List<AdmissionRegisterRecord>> facultiesAndTheirAdmissions = admissionRegisterService.getFacultiesAndTheirAdmissions();
        LOG.trace("Get facultiesAndTheirAdmissions " + facultiesAndTheirAdmissions);

        for (Map.Entry<Faculty, List<AdmissionRegisterRecord>> map : facultiesAndTheirAdmissions.entrySet()) {
            Faculty faculty = map.getKey();
            LOG.trace("Get faculty " + faculty);

            for (AdmissionRegisterRecord record : map.getValue()) {
                User user = new UserRepository().get(record.getEnrollee().getUserId());
                LOG.trace("User was found " + user);

                String resultEmail = "<" + user.getEmail() + ">";
                LOG.trace("User email  " + resultEmail);

                if ("Accepted on a budget".equals(record.getAdmissionState())) {
                    String textEmail = "Dear, " + record.getEnrollee().getFullName() +
                            ". Congratulations! You was accepted on a budget on a " + faculty.getName() + ".";
                    service.execute(new MailThread(resultEmail, "Results of admission to University", textEmail, new Properties()));
                    LOG.trace("Sending email for user, that was accepted on budget " + textEmail);
                }
                if ("Accepted on a contract".equals(record.getAdmissionState())) {
                    String textEmail = "Dear, " + record.getEnrollee().getFullName() +
                            ". You was accepted on a contract on a " + faculty.getName() + ".";
                    service.execute(new MailThread(resultEmail, "Results of admission to University", textEmail, new Properties()));
                    LOG.trace("Sending email for user, that was accepted on contract " + textEmail);
                }
                if ("Not accepted".equals(record.getAdmissionState())) {
                    String textEmail = "Dear, " + record.getEnrollee().getFullName() +
                            ". Sorry, but your grades weren't good enough to be accepted to the University on " + faculty.getName() + ".";
                    service.execute(new MailThread(resultEmail, "Results of admission to University", textEmail, new Properties()));
                    LOG.trace("Sending email for user, that wasn't accepted " + textEmail);
                }
            }
        }
        int userRole = (int) session.getAttribute("userRole");
        if (userRole == 1) {
            LOG.trace("User role " + userRole);
            result = "/WEB-INF/admin/sendEmailsInProgress.jsp";
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
