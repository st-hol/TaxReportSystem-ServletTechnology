package ua.training.controller.command.account;

import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Move user to menu.
 * If access 'admin' move to admin menu.
 * If access 'user' move to user menu.
 *
 * @author Stanislav Holovachuk
 */
public class PersonalCabinetCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        final HttpSession session = request.getSession();
        final User.ROLE role = (User.ROLE) session.getAttribute("role");



        if ( session.getAttribute("role") != User.ROLE.UNKNOWN) {
            //to prevent user coming back to cached pages after logout
            CommandUtility.disallowBackToCached(request, response);
        }


        if (role.equals(User.ROLE.INSPECTOR) || role.equals(User.ROLE.CLIENT)) {
            //todo set param role
            return "/WEB-INF/common/base.jsp";
        } else {
            return "/WEB-INF/common/login.jsp?userExist=false";
        }
    }
}



//
//        if (role.equals(User.ROLE.INSPECTOR)) {
//                return "/WEB-INF/inspector/base.jsp";
//                } else if (role.equals(User.ROLE.CLIENT)) {
//                return "/WEB-INF/client/base.jsp";
//                } else {
//                return "/WEB-INF/common/login.jsp?userExist=false";
//                }