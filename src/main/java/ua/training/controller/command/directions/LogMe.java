package ua.training.controller.command.directions;



import ua.training.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.training.controller.command.TextConstants.Routes.TO_LOGIN;

/**
 * This class is responsible for forwarding
 * to logging page from home page.
 *
 * @author Stanislav Holovachuk
 */

public class LogMe implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return TO_LOGIN;
    }
}
