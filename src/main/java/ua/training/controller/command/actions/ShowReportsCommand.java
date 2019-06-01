package ua.training.controller.command.actions;


import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.dao.impl.JdbcReportDao;
import ua.training.model.dao.impl.JdbcUserDao;
import ua.training.model.entity.Report;
import ua.training.model.entity.User;
import ua.training.model.service.ReportService;
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
public class ShowReportsCommand implements Command {

    private ReportService reportService;

    public ShowReportsCommand(ReportService reportService) {
        this.reportService = reportService;
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


        final User currentSessionUser = CommandUtility.getCurrentSessionUser(request);
        final long currentUserId = currentSessionUser.getId();

        //to prevent user coming back to cached pages after logout
        CommandUtility.disallowBackToCached(request, response);


        int page = 1;
        int recordsPerPage = 3;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }


        JdbcReportDao.PaginationResult paginationResult =
                reportService.getReportsByPagination(
                        (page - 1) * recordsPerPage, recordsPerPage, currentUserId);


        List<Report> reports = paginationResult.getResultList();
        int noOfRecords = paginationResult.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("reports", reports);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);


        //todo make it pretty (.map)
        return "/WEB-INF/client/show-reports.jsp";
    }
}


//
//        StudentDao dao = studentService.getDaoFactory();
//        List<Student> list = dao.findByPagination((page-1)*recordsPerPage,
//                recordsPerPage);
//        dao.close();


//        System.out.println("records:" + noOfRecords);
//        System.out.println("pages:" + noOfPages);
//        System.out.println("cur:" + page);


//     return "/WEB-INF/common/enrolledlist.jsp";

//        System.out.println("students::");
//        for (Student student: enrolledStudents ) {
//            System.out.println(student.toString());
//        }