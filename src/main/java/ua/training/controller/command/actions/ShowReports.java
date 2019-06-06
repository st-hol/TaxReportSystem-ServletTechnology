package ua.training.controller.command.actions;


import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.dao.impl.JdbcReportDao;
import ua.training.model.dao.impl.JdbcUserDao;
import ua.training.model.entity.Report;
import ua.training.model.entity.User;
import ua.training.model.service.ReportService;
import static ua.training.model.service.ReportService.PaginationResult;
import ua.training.model.service.UserService;

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
        int RECORDS_PER_PAGE = 3;

        final User currentSessionUser = CommandUtility.getCurrentSessionUser(request);
        final long currentUserId = currentSessionUser.getId();

        //to prevent user coming back to cached pages after logout
        CommandUtility.disallowBackToCached(request, response);
        
        int currentPage = 1;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        int lowerBound = (currentPage - 1) * RECORDS_PER_PAGE;
        PaginationResult paginationResult =
                reportService.getReportsByPagination(lowerBound, RECORDS_PER_PAGE, currentUserId);


        List<Report> reports = paginationResult.getResultList();
        int noOfRecords = paginationResult.getNoOfRecords();
        int noOfPages = paginationResult.calcNoOfPages(noOfRecords, RECORDS_PER_PAGE);

        request.setAttribute("reports", reports);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", currentPage);

        return "/WEB-INF/client/show-reports.jsp";
    }
}
