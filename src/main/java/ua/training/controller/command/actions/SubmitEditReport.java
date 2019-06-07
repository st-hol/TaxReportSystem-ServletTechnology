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
public class SubmitEditReport implements Command {

    private static final Logger logger = LogManager.getLogger(SubmitEditReport.class);
    private ReportService reportService;

    public SubmitEditReport() {
        this.reportService = ReportService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        CommandUtility.disallowBackToCached(request, response);
        CommandUtility.populateReportToEditAttribute(request);

        final long idReport = Long.parseLong(request.getParameter(ID_REPORT_TO_CHANGE));
        final String companyName = request.getParameter(COMPANY_NAME);
        final String taxpayerCode = request.getParameter(TAXPAYER_CODE);

        Report report = accomplishNewReport(idReport, companyName, taxpayerCode);
        reportService.editReport(report);
        logger.info("Report #" + idReport + " was edited.");

        String path = request.getServletContext().getContextPath();
        return REDIRECT + path + REDIRECT_TO_EDIT_REPORT;
    }

    private Report accomplishNewReport(long idReport, String companyName, String taxpayerCode) {
        final Report report = new Report();
        report.setId(idReport);
        report.setCompanyName(companyName);
        report.setTaxpayerCode(taxpayerCode);
        report.setShouldBeChanged(false);

        return report;
    }
}












