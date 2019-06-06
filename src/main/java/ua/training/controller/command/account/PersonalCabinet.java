package ua.training.controller.command.account;

import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.training.controller.command.TextConstants.Parameters.*;
import static ua.training.controller.command.TextConstants.Routes.*;

/**
 * Move user to menu.
 * If access 'admin' move to admin menu.
 * If access 'user' move to user menu.
 *
 * @author Stanislav Holovachuk
 */
public class PersonalCabinet implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        final HttpSession session = request.getSession();
        final User.ROLE role = (User.ROLE) session.getAttribute(ROLE);

        if ( session.getAttribute(ROLE) != User.ROLE.UNKNOWN) {
            //to prevent user coming back to cached pages after logout by clicking "back arrow" in browser
            CommandUtility.disallowBackToCached(request, response);
        }

        if (role.equals(User.ROLE.INSPECTOR) || role.equals(User.ROLE.CLIENT)) {
            return BASE;
        } else {
            return USER_NOT_EXIST;
        }
    }
}
