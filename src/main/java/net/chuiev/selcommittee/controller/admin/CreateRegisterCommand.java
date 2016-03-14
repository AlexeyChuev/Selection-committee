package net.chuiev.selcommittee.controller.admin;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.entity.AdmissionRegisterRecord;
import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.services.AdmissionRegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 3/13/2016.
 */
public class CreateRegisterCommand extends Command {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result = null;
        HttpSession session = request.getSession(false);
        AdmissionRegisterService admissionRegisterService = new AdmissionRegisterService();
        Map<Faculty, List<AdmissionRegisterRecord>> facultiesAndTheirAdmissions = admissionRegisterService.getFacultiesAndTheirAdmissions();
        request.setAttribute("map", facultiesAndTheirAdmissions);
        int userRole = (int) session.getAttribute("userRole");
        if (userRole == 1) {
            result = "/WEB-INF/admin/viewRegister.jsp";
        }
        return result;
    }
}
