package ua.training.model.dao.impl;


import ua.training.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection connection = getConnection();

    @Override
    public ReportDao createReportDao() {
        JdbcReportDao jdbcReportDao = JdbcReportDao.getInstance();
        jdbcReportDao.initConnection(connection);
        return jdbcReportDao;
    }

    @Override
    public ComplaintDao createComplaintDao() {
        JdbcComplaintDao jdbcComplaintDao = JdbcComplaintDao.getInstance();
        jdbcComplaintDao.initConnection(connection);
        return jdbcComplaintDao;
    }

    @Override
    public UserDao createUserDao() {
        JdbcUserDao jdbcUserDao = JdbcUserDao.getInstance();
        jdbcUserDao.initConnection(connection);
        return jdbcUserDao;
    }

    @Override
    public TaxableItemDao createTaxableItemDao() {
        JdbcTaxableItemDao jdbcTaxableItemDao = JdbcTaxableItemDao.getInstance();
        jdbcTaxableItemDao.initConnection(connection);
        return jdbcTaxableItemDao;
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
