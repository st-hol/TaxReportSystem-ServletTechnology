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
public class SubmitEditReportCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SubmitEditReportCommand.class);


    private ReportService reportService;

    public SubmitEditReportCommand(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        CommandUtility.defineReportToEditAttribute(request);

        final long idReport = Long.parseLong(request.getParameter("idReportToChange"));
        final String companyName = request.getParameter("companyName");
        final String taxpayerCode = request.getParameter("taxpayerCode");

        Report report = new Report();
        report.setId(idReport);
        report.setCompanyName(companyName);
        report.setTaxpayerCode(taxpayerCode);
        report.setShouldBeChanged(false);

        reportService.editReport(report);
        logger.info("Report #"+idReport+" was edited.");


        return "/WEB-INF/client/edit-report.jsp";
    }

}
