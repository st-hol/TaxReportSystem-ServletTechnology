package ua.training.controller;

import ua.training.controller.command.Command;
import ua.training.controller.command.account.Login;
import ua.training.controller.command.account.Logout;
import ua.training.controller.command.account.PersonalCabinet;
import ua.training.controller.command.account.Registration;
import ua.training.controller.command.actions.*;
import ua.training.controller.command.directions.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static ua.training.controller.command.TextConstants.CommandPaths.*;
import static ua.training.controller.command.TextConstants.Routes.*;

/**
 * <h1>Final project</h1>
 *
 * <p>
 *     This program implements the task
 *     which was given as final project
 *     at java external classes.
 *
 *     <i>The main purpose of this task
 *     is demonstration Java skills
 *     received after three months of training.</i>
 * </p>
 *
 * <strong>Variant №5)Tax Report System </strong>
 *
 * @author  Stanislav Holovachuk
 * @version 1.0
 * @since   2019-21-04
 */



public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();


    public void init(ServletConfig servletConfig){

        //account
        commands.put(REGISTRATION,
                new Registration());
        commands.put(LOGIN,
                new Login());
        commands.put(LOGOUT,
                new Logout());
        commands.put(PERSONAL_CABINET,
                new PersonalCabinet());


        //directions
        commands.put(HOME,
                new Home());
        commands.put(REG_ME,
                new RegMe());
        commands.put(LOG_ME,
                new LogMe());
        commands.put(MAKE_COMPLAINT,
                new MakeComplaint());
        commands.put(MAKE_REPORT,
                new MakeReport());
        commands.put(CHECK_REPORT,
                new CheckReport());
        commands.put(SET_TAXABLE,
                new SetTaxable());
        commands.put(EDIT_REPORTS,
                new EditReport());


        //actions
        commands.put(SUBMIT_REPORT,
                new SubmitApplyingReport());
        commands.put(SUBMIT_COMPLAINT,
                new SubmitComplaint());
        commands.put(SHOW_REPORTS,
                new ShowReports());
        commands.put(SUBMIT_EDIT_REPORT,
                new SubmitEditReport());


        commands.put(SUBMIT_CHECKING_REPORT,
                new SubmitCheckingReport());
        commands.put(SUBMIT_SET_TAXABLE,
                new SubmitSetTaxable());
    }



    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    /**
     * This is the working method.
     * It obtains the path from Command->execute() method
     * If path contains "redirect@" it truncates it at send redirect
     * else it makes forward.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return Nothing.
     */
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        path = path.replaceAll(APPLICATION_PATH_REGEX , EMPTY_STRING);

        Command command = commands.getOrDefault(path, (req, resp)->DEFAULT_PATH);
        String page = command.execute(request, response);

        if (page.contains(REDIRECT)) {
            response.sendRedirect(page.replace(REDIRECT, EMPTY_STRING));
        } else {
            request.getRequestDispatcher(page).forward(request,response);
        }
    }
}
