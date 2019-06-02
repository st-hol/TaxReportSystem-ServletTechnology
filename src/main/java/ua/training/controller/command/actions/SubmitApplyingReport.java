package ua.training.controller.command.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.Report;
import ua.training.model.entity.User;
import ua.training.model.service.ReportService;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * This class is responsible for applying for admission
 * from user-role personal cabinet.
 *
 * @author Stanislav Holovachuk
 */
public class SubmitApplyingReport implements Command {

    private static final Logger logger = LogManager.getLogger(SubmitApplyingReport.class);


    private ReportService reportService;
    private UserService userService;


    public SubmitApplyingReport(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        final User currentSessionUser = CommandUtility.getCurrentSessionUser(request);
        final long currentUserId = currentSessionUser.getId();


        final String companyName = request.getParameter("companyName");
        final String taxpayerCode = request.getParameter("taxpayerCode");


        Report report = new Report();

        report.setPerson(
                userService.getUserById(currentUserId));
        report.setCompletionTime(Timestamp.from(Instant.now()));
        report.setCompanyName(companyName);
        report.setTaxpayerCode(taxpayerCode);


        reportService.submitReportAction(report);
        logger.info("User " + currentSessionUser.getFirstName() + " " + currentSessionUser.getLastName()
                + " submitted report.");


        return "/WEB-INF/client/submit-report.jsp";
//        String path = request.getServletContext().getContextPath();
//        return "redirect@" + path + "/app/make-report";
    }

}
