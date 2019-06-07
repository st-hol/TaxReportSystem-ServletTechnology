package ua.training.model.service;


import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ReportDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.JdbcReportDao;
import ua.training.model.dao.impl.JdbcUserDao;
import ua.training.model.entity.Report;
import ua.training.model.entity.User;

import java.util.List;


/**
 * This class realize logic
 * for manipulation with db.
 *
 * @author Stanislav Holovachuk
 */
public class ReportService {

    private DaoFactory daoFactory;

    private static ReportService instance;

    private ReportService() {
        daoFactory = DaoFactory.getInstance();
    }

    public static ReportService getInstance() {
        if (instance == null) {
            synchronized (ReportService.class) {
                if (instance == null) {
                    instance = new ReportService();
                }
            }
        }
        return instance;
    }

    /**
     * Submits user's report.
     *
     * @param report Report.
     */
    public void submitReportAction(Report report) {
        ReportDao reportDao = daoFactory.createReportDao();
        reportDao.create(report);

    }


    public void editReport(Report report) {
        ReportDao reportDao = daoFactory.createReportDao();
        reportDao.editReport(report);

    }

    /**
     * Inspector checks the report and gives verdict.
     *
     * @param report Report.
     */
    public void concludeCheckingReport(Report report) {
        ReportDao reportDao = daoFactory.createReportDao();
        reportDao.update(report);

    }

    /**
     * obtains List of all reports that are made by users who are assigned to this inspector.
     */
    public List<Report> getAllReports(User inspector) {
        ReportDao dao = daoFactory.createReportDao();
        return dao.findAllAssignedClients(inspector);

    }


    /**
     * obtains List of all reports that marked as should be changed.
     */
    public List<Report> getReportsToChange(User client) {
        ReportDao dao = daoFactory.createReportDao();
        return dao.findReportsToChange(client);

    }


    /**
     * obtains List of certain quantity of enrolled students.
     */
    public PaginationResult getReportsByPagination(int lowerBound, int upperBound, long idUser) {
        ReportDao dao = daoFactory.createReportDao();
        return dao.findByPagination(lowerBound, upperBound, idUser);
    }


    /**
     * It is user-defined class just for returning result from findByPagination() method.
     */
    public static class PaginationResult {
        private int noOfRecords;
        private List<Report> resultList;

        public int getNoOfRecords() {
            return noOfRecords;
        }

        public void setNoOfRecords(int noOfRecords) {
            this.noOfRecords = noOfRecords;
        }

        public List<Report> getResultList() {
            return resultList;
        }

        public void setResultList(List<Report> resultList) {
            this.resultList = resultList;
        }

    }

}


