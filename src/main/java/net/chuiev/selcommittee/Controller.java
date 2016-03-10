package net.chuiev.selcommittee;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.controller.CommandManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 3/10/2016.
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        Command command = CommandManager.get(commandName);
        String path = command.execute(request, response);
        if (path == null) {
            response.sendRedirect("/login.jsp");
        }
        else{
            RequestDispatcher disp = request.getRequestDispatcher(path);
            disp.forward(request, response);
        }
    }
}
