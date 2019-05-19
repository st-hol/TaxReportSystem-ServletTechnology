package ua.training.model.dao;


import ua.training.model.dao.impl.JdbcReportDao;
import ua.training.model.entity.Report;

public interface ReportDao extends GenericDao<Report> {

    JdbcReportDao.PaginationResult findByPagination(int offset, int noOfRecords, long idUser);

}
