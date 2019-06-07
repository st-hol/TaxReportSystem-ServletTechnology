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
        int currentPage = 1;
        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        }

        performPagination(request, currentPage, RECORDS_PER_PAGE);
        return TO_SHOW_REPORTS;
    }

    private void performPagination(HttpServletRequest request, int currentPage, int recordsPerPage){
        final User currentSessionUser = CommandUtility.getCurrentSessionUser(request);
        final long currentUserId = currentSessionUser.getId();

        int lowerBound = calcLowerBound(currentPage, recordsPerPage);

        PaginationResult paginationResult =
                reportService.getReportsByPagination(lowerBound, recordsPerPage, currentUserId);

        List<Report> reports = paginationResult.getResultList();
        int noOfRecords = paginationResult.getNoOfRecords();
        int noOfPages = calcNoOfPages(noOfRecords, recordsPerPage);

        request.setAttribute(REPORTS, reports);
        request.setAttribute(NO_OF_PAGES, noOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
    }

    private int calcLowerBound(int currentPage, int recordsPerPage){
        return  (currentPage - 1) * recordsPerPage;
    }

    private int calcNoOfPages(int noOfRecords, int recordsPerPage) {
        return (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    }
}
