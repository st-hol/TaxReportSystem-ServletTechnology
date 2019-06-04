package ua.training.model.dao;


import ua.training.model.dao.impl.JdbcReportDao;
import ua.training.model.entity.Report;
import ua.training.model.entity.User;
import ua.training.model.service.ReportService;

import java.util.List;

public interface ReportDao extends GenericDao<Report> {

    ReportService.PaginationResult findByPagination(int offset, int noOfRecords, long idUser);

    List<Report> findAllAssignedClients(User inspector);

    void editReport(Report report);

    List<Report> findReportsToChange(User client);
}
