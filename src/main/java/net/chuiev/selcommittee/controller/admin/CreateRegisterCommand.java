package net.chuiev.selcommittee.controller.admin;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.AdmissionRegisterRecord;
import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.services.AdmissionRegisterService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This command admin invoke, then he wants to create final
 * register with results of admission for all enrollees.
 *
 * @author Oleksii Chuiev
 */
public class CreateRegisterCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CreateRegisterCommand.class);

    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command execution starts");

        String result = null;
        HttpSession session = request.getSession(false);
        AdmissionRegisterService admissionRegisterService = new AdmissionRegisterService();
        Map<Faculty, List<AdmissionRegisterRecord>> facultiesAndTheirAdmissions = admissionRegisterService.getFacultiesAndTheirAdmissions();
        LOG.trace("facultiesAndTheirAdmissions was create: " + facultiesAndTheirAdmissions);
        request.setAttribute("map", facultiesAndTheirAdmissions);
        LOG.trace("Set Attribute: 'map' " + facultiesAndTheirAdmissions);

        int userRole = (int) session.getAttribute("userRole");
        if (userRole == 1) {
            LOG.trace("User role " + userRole);
            result = "/WEB-INF/admin/viewRegister.jsp";
        }

        LOG.debug("Command execution finished");
        return result;
    }
}
