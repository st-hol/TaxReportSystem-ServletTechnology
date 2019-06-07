package ua.training.controller.command.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.Report;
import ua.training.model.service.ReportService;
import static ua.training.controller.command.TextConstants.Parameters.*;
import static ua.training.controller.command.TextConstants.Routes.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * This class is responsible for acception/rejection of report.
 * from user-role personal cabinet.
 *
 * @author Stanislav Holovachuk
 */
public class SubmitCheckingReport implements Command {

    private static final Logger logger = LogManager.getLogger(SubmitCheckingReport.class);
    private ReportService reportService;

    public SubmitCheckingReport() {
        this.reportService = ReportService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        CommandUtility.populateAssignedReportsAttribute(request);

        final long idReport = Long.parseLong(request.getParameter(ID_REPORT));
        final int isAccepted = Integer.parseInt(request.getParameter(IS_ACCEPTED));
        final int shouldBeChanged = Integer.parseInt(request.getParameter(SHOULD_BE_CHANGED));
        final String inspectorComment = request.getParameter(INSPECTOR_COMMENT);

        final Report report = accomplishNewReport(idReport, isAccepted, shouldBeChanged, inspectorComment);
        reportService.concludeCheckingReport(report);
        logger.info("Report #"+idReport+" was reviewed by inspector.");

        return TO_CHECK_REPORT;
    }

    private Report accomplishNewReport(long idReport, int isAccepted, int shouldBeChanged, String inspectorComment){
        final Report report = new Report();

        report.setId(idReport);
        report.setAcceptedFromInt(isAccepted);
        report.setShouldChangeFromInt(shouldBeChanged);
        report.setInspectorComment(inspectorComment);

        return report;
    }
}
















