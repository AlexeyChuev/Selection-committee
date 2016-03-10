package net.chuiev.selcommittee.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 3/10/2016.
 */
public class LoginCommand extends Command {
    private static final long serialVersionUID = -3071536593627692473L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result = null;
        /*if (request.geactionType == ActionType.POST) {
            result = doPost(request, response);
        }*/
        return result;
    }


}
