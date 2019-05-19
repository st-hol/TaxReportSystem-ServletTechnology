package ua.training.model.service;


import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ReportDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.JdbcReportDao;
import ua.training.model.dao.impl.JdbcUserDao;
import ua.training.model.entity.Report;

import java.util.List;


/**
 * This class realize logic
 * for manipulation with db.
 *
 * @author Stanislav Holovachuk
 */
public class ReportService {

    private DaoFactory daoFactory = DaoFactory.getInstance();


    /**
     * Submits user's report.
     *
     * @param report Report.
     */
    public void submitReportAction(Report report)  {
        try (ReportDao reportDao  = daoFactory.createReportDao()) {
            reportDao.create(report);
        }
    }


    /**
     * obtains List of all reports.
     */
    public List<Report> getAllReports() {
        try (ReportDao dao = daoFactory.createReportDao()) {
            return dao.findAll();
        }
    }




    /**
     * obtains List of certain quantity of enrolled students.
     */
    public JdbcReportDao.PaginationResult getReportsByPagination(int lowerBound, int upperBound, long idUser) {
        try (ReportDao dao = daoFactory.createReportDao()) {
            return dao.findByPagination(lowerBound, upperBound, idUser);
        }
    }


}


