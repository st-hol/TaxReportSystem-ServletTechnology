package ua.training.controller.command.account;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.training.controller.command.TextConstants.Routes.REDIRECT;


/**
 * Processes logging out.
 * @author Stanislav Holovachuk
 */
public class Logout implements Command {
    private static final Logger logger = LogManager.getLogger(Logout.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final String email = (String)request.getSession().getAttribute("email");
        CommandUtility.logoutUser(request, email);
        logger.info("User [" + email + "] " + "logged out." );

        String path = request.getServletContext().getContextPath();
        return REDIRECT + path + "/";
    }
}






















//        final HttpSession session = request.getSession();
//        session.removeAttribute("password");
//        session.removeAttribute("login");
//        session.removeAttribute("role");

//Optional<Object> login = Optional.ofNullable(request.getSession().getAttribute("login"));

//login.ifPresent(lgn -> System.out.println(lgn.toString()));

//login.ifPresent(lgn -> CommandUtility.logoutUser(request, lgn.toString()));
//        return "redirect:login";





//    @Override
//    public String execute(HttpServletRequest request) {
//
//        Optional<Object> email = Optional.ofNullable(request.getSession().getAttribute("email"));
//
//        email.ifPresent(e -> CommandUtility.logoutUser(request, e.toString()));
//
//        return "redirect:login";
//    }


//    @Override
//    public String execute(HttpServletRequest request) throws IOException, ServletException {
////        CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, "Guest");
////        return "/welcome.jsp";
//
//        final HttpSession session = request.getSession();
//
//        session.removeAttribute("password");
//        session.removeAttribute("login");
//        session.removeAttribute("role");
//
//        response.sendRedirect(super.getServletContext().getContextPath());
//
//    }
