package ua.training.controller.command.actions;


import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.Report;
import ua.training.model.entity.User;
import ua.training.model.service.ReportService;
import static ua.training.model.service.ReportService.PaginationResult;
import static ua.training.controller.command.TextConstants.Parameters.*;
import static ua.training.controller.command.TextConstants.Routes.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * This command is responsible for
 * showing list of reports.
 *
 * @author Stanislav Holovachuk
 */
public class ShowReports implements Command {

    private ReportService reportService;


    public ShowReports() {
        this.reportService = ReportService.getInstance();
    }

    /**
     * Here pagination is provided.
     * <p>
     * Has hardcoded quantity of displayed records(recordsPerPage).
     * Operates by getting page-number from request and delegates it to service
     * which returns certain records.
     *
     * @author Stanislav Holovachuk
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //to prevent user coming back to cached pages after logout
        CommandUtility.disallowBackToCached(request, response);

        int RECORDS_PER_PAGE = 3;
        final User currentSessionUser = CommandUtility.getCurrentSessionUser(request);
        final long currentUserId = currentSessionUser.getId();


        int currentPage = 1;
        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        }

        int lowerBound = (currentPage - 1) * RECORDS_PER_PAGE;
        PaginationResult paginationResult =
                reportService.getReportsByPagination(lowerBound, RECORDS_PER_PAGE, currentUserId);


        List<Report> reports = paginationResult.getResultList();
        int noOfRecords = paginationResult.getNoOfRecords();
        int noOfPages = paginationResult.calcNoOfPages(noOfRecords, RECORDS_PER_PAGE);

        request.setAttribute(REPORTS, reports);
        request.setAttribute(NO_OF_PAGES, noOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);

        return TO_SHOW_REPORTS;
    }
}
