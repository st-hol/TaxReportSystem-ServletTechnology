package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;


/**
 * This class realize some utility logic
 * for manipulation with commands.
 *
 * @author Stanislav Holovachuk
 */
public class CommandUtility {

    private static UserService userService;

    static
    {
        userService = new UserService();
    }


    /**
     * This is the executing certain command method
     * which provides the concrete logic for each
     * class that implements it.
     *
     * @param request HttpServletRequest.
     * @param email String.
     *
     */
    public static boolean checkUserIsLogged(HttpServletRequest request, String email){

        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext()
                .getAttribute("loggedUsers");


        if(loggedUsers.stream().anyMatch(email::equals)) {
            return true;
        }

        loggedUsers.add(email);
        request.getSession()
                .getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }



    public static void unlogUser(HttpServletRequest request, String email) {

        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>)
                request.getSession().getServletContext().getAttribute("loggedUsers");

        loggedUsers.remove(email);

        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

        final HttpSession session = request.getSession();
        session.removeAttribute("email");
        session.removeAttribute("password");
        session.removeAttribute("role");
    }


    /**
     * Obtain student from current session.
     *
     * @param request HttpServletRequest.
     */
    public static User getCurrentSessionUser(HttpServletRequest request){

        final HttpSession session = request.getSession();
        String email = session.getAttribute("email").toString();

        UserService userService = new UserService();
        return userService.getUserByEmail(email);
    }


    /**
     * This method is invoked in commands which need to disallow using cache.
     * It solves the issue when user could logged out and than return
     * to its personal cabinet by clicking "back" button in browser.
     *
     * @param request HttpServletRequest.
     * @param response HttpServletResponse.
     */
    //to prevent user coming back to cached pages after logout
    public static void disallowBackToCached(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final HttpSession session = request.getSession();
        final String path = request.getServletContext().getContextPath();

        //to prevent user coming back to cached pages after logout
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
        if (session.getAttribute("email") == null || session.getAttribute("password") == null
                || session.getAttribute("role") == null) {
            response.sendRedirect(path +  "/WEB-INF/common/error/invalidSession.jsp");
            //return  "redirect@" + path + "/jsp/error/invalidSession.jsp";
        }
    }


    /**
     * Uses to set attribute on certain command.
     *
     * @param request HttpServletRequest.
     */
    public static void defineUsersAttribute(HttpServletRequest request) {
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
    }





}


