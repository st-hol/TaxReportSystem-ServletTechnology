package ua.training.model.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.ReportDao;
import ua.training.model.dao.impl.queries.ReportSQL;
import ua.training.model.dao.impl.queries.TaxableItemSQL;
import ua.training.model.dao.mapper.ReportMapper;
import ua.training.model.entity.Report;
import ua.training.model.entity.User;
import ua.training.model.service.ReportService;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcReportDao implements ReportDao {

    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JdbcReportDao.class);
    private static JdbcReportDao instance;

    private JdbcReportDao() {

    }

    public static JdbcReportDao getInstance() {
        if (instance == null) {
            synchronized (JdbcReportDao.class) {
                if (instance == null) {
                    instance = new JdbcReportDao();
                }
            }
        }
        return instance;
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void initConnection(Connection connection) {
        instance.setConnection(connection);
    }

    /**
     * Create User(report/admin) in database.
     *
     * @param report for create.
     */
    @Override
    public void create(@NotNull final Report report) {

        try (PreparedStatement reportPs = connection.prepareStatement(ReportSQL.INSERT.getQUERY());
             PreparedStatement itemPs = connection.prepareStatement(TaxableItemSQL.GET_TOTAL_AMOUNT_OF_PROPERTY_FOR_CERTAIN_PERSON.getQUERY())) {

            //transaction
            connection.setAutoCommit(false);

            reportPs.setLong(1, report.getPerson().getId());
            reportPs.setString(2, report.getCompanyName());
            reportPs.setString(3, report.getTaxpayerCode());
            reportPs.setTimestamp(4, report.getCompletionTime());
            //reportPs.setLong(5, report.getTotalAmountOfProperty());
            reportPs.setBoolean(6, report.getIsAccepted());
            reportPs.setBoolean(7, report.getShouldBeChanged());
            reportPs.setString(8, report.getInspectorComment());


            itemPs.setLong(1, report.getPerson().getId());
            ResultSet rs = itemPs.executeQuery();
            if (rs.next()) {
                long totalAmountOfProperty = rs.getLong("total_amount");
                reportPs.setLong(5, totalAmountOfProperty);
            }

            reportPs.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.fatal("Caught SQLException exception while doing rollback", e);
                e.printStackTrace();
            }
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
    }

    /**
     * finds User in database.
     *
     * @param id person id.
     */
    @Override
    public Report findById(long id) {
        ReportMapper reportMapper = new ReportMapper();

        Report result = new Report();
        result.setId(-1);

        try (PreparedStatement ps = connection.prepareStatement(ReportSQL.READ_ONE.getQUERY())) {

            ps.setLong(1, id);

            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = reportMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * obtains all persons from database.
     */
    @Override
    public List<Report> findAll() {
        Map<Long, Report> reports = new HashMap<>();

        final String query = ReportSQL.READ_ALL.getQUERY();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            return mapFindManyResultSet(rs, reports);
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Report> findAllAssignedClients(User inspector) {
        Map<Long, Report> reports = new HashMap<>();

        try (PreparedStatement ps = connection.prepareStatement(ReportSQL.READ_ALL_REPORTS_MADE_BY_ASSIGNED_CLIENTS.getQUERY())) {
            ps.setLong(1, inspector.getId());
            ResultSet rs = ps.executeQuery();
            return mapFindManyResultSet(rs, reports);
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Report> findReportsToChange(User client) {
        Map<Long, Report> reports = new HashMap<>();

        try (PreparedStatement ps = connection.prepareStatement(ReportSQL.READ_ALL_REPORTS_TO_CHANGE_BY_ID_CLIENT.getQUERY())) {
            ps.setLong(1, client.getId());
            ResultSet rs = ps.executeQuery();
            return mapFindManyResultSet(rs, reports);
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
            return null;
        }
    }


    //utility method. created in order not to duplicate code below
    private List<Report> mapFindManyResultSet(ResultSet rs, Map<Long, Report> reports) throws SQLException{
        ReportMapper reportMapper = new ReportMapper();
        while (rs.next()) {
            Report report = reportMapper.extractFromResultSet(rs);
            report = reportMapper.makeUnique(reports, report);
        }
        return new ArrayList<>(reports.values());
    }

    @Override
    public void editReport(Report report) {
        try (PreparedStatement ps = connection.prepareStatement(ReportSQL.EDIT_REPORT.getQUERY())) {

            ps.setString(1, report.getCompanyName());
            ps.setString(2, report.getTaxpayerCode());
            ps.setInt(3, report.getShouldBeChanged() ? 1 : 0);
            ps.setLong(4, report.getId());

            ps.execute();

        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
    }

    @Override
    public void update(Report report) {
        try (PreparedStatement ps = connection.prepareStatement(ReportSQL.UPDATE.getQUERY())) {

            ps.setInt(1, report.getIsAccepted() ? 1 : 0);
            ps.setInt(2, report.getShouldBeChanged() ? 1 : 0);
            ps.setString(3, report.getInspectorComment());
            ps.setLong(4, report.getId());

            ps.execute();

        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("This action has not yet been developed.");
    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * SQL query obtains all Reports limited by lower and upper bounds ordered by descent according to time
     * and quantity of all records got from database.
     *
     * @param lowerBound integer value.
     * @param upperBound integer value.
     * @return object of user-defined class PaginationResult. Which contains of two fields:
     * 1)the List of obtained Reports.
     * 2)number of records was read.
     */
    @Override
    public ReportService.PaginationResult findByPagination(int lowerBound, int upperBound, long idUser) {

        ReportService.PaginationResult paginationResult = new ReportService.PaginationResult();

        Map<Long, Report> reports = new HashMap<>();
        ReportMapper reportMapper = new ReportMapper();

        try (PreparedStatement reportsPS = connection.prepareStatement(ReportSQL.GET_REPORTS_BY_PAGINATION.getQUERY());
             PreparedStatement countRowsPS = connection.prepareStatement(ReportSQL.CALC_REPORTS_BY_PERSON_ID.getQUERY())) {
            reportsPS.setLong(1, idUser);
            reportsPS.setInt(2, lowerBound);
            reportsPS.setInt(3, upperBound);

            ResultSet rs = reportsPS.executeQuery();
            while (rs.next()) {
                Report report = reportMapper.extractFromResultSet(rs);
                report = reportMapper.makeUnique(reports, report);
            }
            rs.close();


            countRowsPS.setLong(1, idUser);
            rs = countRowsPS.executeQuery();
            if (rs.next()) {
                paginationResult.setNoOfRecords(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        paginationResult.setResultList(new ArrayList<>(reports.values()));
        return paginationResult;
    }


}


