package ua.training.controller.command.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.Report;
import ua.training.model.entity.User;
import ua.training.model.service.ReportService;
import ua.training.model.service.UserService;
import static ua.training.controller.command.TextConstants.Parameters.*;
import static ua.training.controller.command.TextConstants.Routes.*;

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


    public SubmitApplyingReport() {
        this.reportService = ReportService.getInstance();
        this.userService = UserService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        CommandUtility.disallowBackToCached(request, response);

        final User currentSessionUser = CommandUtility.getCurrentSessionUser(request);
        final long currentUserId = currentSessionUser.getId();
        final String companyName = request.getParameter(COMPANY_NAME);
        final String taxpayerCode = request.getParameter(TAXPAYER_CODE);

        Report report = accomplishNewReport(currentUserId, companyName, taxpayerCode);
        reportService.submitReportAction(report);
        logger.info("User " + currentSessionUser.getFirstName() + " " + currentSessionUser.getLastName()
                + " submitted report.");

        return TO_SUBMIT_REPORT;
    }


    private Report accomplishNewReport(long userId, String companyName, String taxpayerCode){
        Report report = new Report();

        report.setPerson(
                userService.getUserById(userId));
        report.setCompletionTime(Timestamp.from(Instant.now()));
        report.setCompanyName(companyName);
        report.setTaxpayerCode(taxpayerCode);

        return report;
    }

}
