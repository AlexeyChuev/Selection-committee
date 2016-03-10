package net.chuiev.selcommittee.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 3/10/2016.
 */
public class NoCommand extends Command {
    private static final long serialVersionUID = -2785976616686657267L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        return "/error.jsp";
    }
}
