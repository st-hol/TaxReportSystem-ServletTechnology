package ua.training.controller.command.directions;

import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.training.controller.command.TextConstants.HOME;


/**
 * This class is responsible for forwarding
 * to home page from user-role or admin-role
 * personal cabinet.
 *
 * @author Stanislav Holovachuk
 */

public class Home implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return HOME;
    }
}















//        String path = request.getServletContext().getContextPath();
//        //System.out.println(path);
//        return "redirect@" + path + "/jsp/welcome.jsp";





//            Optional<Object> role = Optional.ofNullable(request.getSession().getAttribute("role"));
//            return role.map(o -> "redirect@" + o.toString().toLowerCase())
//                    .orElse("redirect@login");



//servlet jss - routes