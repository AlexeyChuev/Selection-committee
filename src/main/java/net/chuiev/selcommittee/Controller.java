package net.chuiev.selcommittee;

import net.chuiev.selcommittee.controller.Command;
import net.chuiev.selcommittee.controller.CommandManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Controller. This servlet handles all
 * requests and processes them according to command name.
 *
 * @author Oleksii Chuiev
 *
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Controller.class);

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * Handles all requests coming from the client by executing
     * command name in a request.
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Controller proccessing starts");

        String commandName = request.getParameter("command");
        Command command = CommandManager.get(commandName);
        String path = command.execute(request, response);
        LOG.trace("Redirect to path = " + path);
        if (path == null) {
            response.sendRedirect("/login.jsp");
            LOG.trace("Redirect to path = " + "/login.jsp");
            LOG.debug("Controller proccessing finished");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            LOG.debug("Controller proccessing finished");
            dispatcher.forward(request, response);
        }

    }
}
