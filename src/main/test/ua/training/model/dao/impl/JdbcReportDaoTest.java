package ua.training.model.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.training.model.entity.Report;
import ua.training.model.service.ReportService;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcReportDaoTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }




    /**
     * check the time for DESC
     */
    @Test
    public void findByPagination() {
        ReportService reportService = new ReportService();
        List<Report> reports = reportService.getReportsByPagination(0, 3, 2)
                .getResultList();

        boolean sorted = true;
        for (int i = 1; i < reports.size(); i++) {
            if ( ! reports.get(i-1).getCompletionTime().before(reports.get(i).getCompletionTime())) {
                sorted = false;
            }
        }
        assertTrue(sorted);
    }
}