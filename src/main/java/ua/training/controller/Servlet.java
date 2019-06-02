package ua.training.controller;



import ua.training.controller.command.Command;
import ua.training.controller.command.account.Login;
import ua.training.controller.command.account.Logout;
import ua.training.controller.command.account.PersonalCabinet;
import ua.training.controller.command.account.Registration;
import ua.training.controller.command.actions.*;
import ua.training.controller.command.directions.*;
import ua.training.model.service.ComplaintService;
import ua.training.model.service.ReportService;
import ua.training.model.service.TaxableItemService;
import ua.training.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        commands.put("registration",
                new Registration(new UserService()));
        commands.put("login",
                new Login(new UserService()));
        commands.put("logout",
                new Logout());
        commands.put("personal-cabinet",
                new PersonalCabinet());


        //directions
        commands.put("home",
                new Home());
        commands.put("reg-me",
                new RegMe());
        commands.put("log-me",
                new LogMe());
        commands.put("make-complaint",
                new MakeComplaint());
        commands.put("make-report",
                new MakeReport());
        commands.put("check-report",
                new CheckReport());
        commands.put("set-taxable",
                new SetTaxable());
        commands.put("edit-report",
                new EditReport());


        //actions
        commands.put("submit-report",
                new SubmitApplyingReport(new ReportService(), new UserService()));
        commands.put("submit-complaint",
                new SubmitComplaint(new ComplaintService()));
        commands.put("show-reports",
                new ShowReports(new ReportService()));
        commands.put("submit-edit-report",
                new SubmitEditReport(new ReportService()));


        commands.put("submit-checking-report",
                new SubmitCheckingReport(new ReportService()));
        commands.put("submit-set-taxable",
                new SubmitSetTaxable(new TaxableItemService()));
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
        path = path.replaceAll(".*/app/" , "");

        Command command = commands.getOrDefault(path, (req, resp)->"/welcome.jsp");
        String page = command.execute(request, response);

        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect@", ""));
        } else {
            request.getRequestDispatcher(page).forward(request,response);
        }
    }
}
