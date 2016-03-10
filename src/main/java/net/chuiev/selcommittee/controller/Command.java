package net.chuiev.selcommittee.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Alex on 3/10/2016.
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 8879403039606311780L;

    public abstract String execute(HttpServletRequest request,
                                   HttpServletResponse response)
            throws IOException, ServletException;

}
