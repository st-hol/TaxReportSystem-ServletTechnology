package ua.training.controller.command.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.Report;
import ua.training.model.service.ReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        CommandUtility.defineAssignedReportsAttribute(request);

        final long idReport = Long.parseLong(request.getParameter("idReport"));
        final int isAccepted = Integer.parseInt(request.getParameter("isAccepted"));
        final int shouldBeChanged = Integer.parseInt(request.getParameter("shouldBeChanged"));
        final String inspectorComment = request.getParameter("inspectorComment");

        final Report report = new Report();

        report.setId(idReport);
        report.setAcceptedFromInt(isAccepted);
        report.setShouldChangeFromInt(shouldBeChanged);
        report.setInspectorComment(inspectorComment);

        reportService.concludeCheckingReport(report);
        logger.info("Report #"+idReport+" was reviewed by inspector.");


        return "/WEB-INF/inspector/check-report.jsp";
    }
}
















