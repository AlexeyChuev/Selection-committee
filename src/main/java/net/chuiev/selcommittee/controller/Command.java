package net.chuiev.selcommittee.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Abstract class for Command pattern.
 *
 * @author Oleksii Chuiev
 *
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 8879403039606311780L;

    /**
     * Execution method for command. Returns path to jsp pages using client request.
     *
     * @param request
     *            - client request
     * @param response
     *            - server response
     * @return Address to jsp-pag.
     * @throws IOException
     * @throws ServletException
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;
}
