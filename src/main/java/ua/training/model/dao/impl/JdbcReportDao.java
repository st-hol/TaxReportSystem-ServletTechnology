package ua.training.model.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.ReportDao;
import ua.training.model.dao.impl.queries.ReportSQL;
import ua.training.model.dao.impl.queries.TaxableItemSQL;
import ua.training.model.dao.mapper.ReportMapper;
import ua.training.model.entity.Report;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcReportDao implements ReportDao {

    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JdbcReportDao.class);

    public JdbcReportDao(Connection connection) {
        this.connection = connection;
    }


    /**
     * Create User(report/admin) in database.
     *
     * @param report for create.
     */
    @Override
    public void create(@NotNull final Report report) {
        //todo transaction

        try (PreparedStatement ps = connection.prepareStatement(ReportSQL.INSERT.getQUERY())) {

            connection.setAutoCommit(false);

            ps.setLong(1, report.getPerson().getId());
            ps.setString(2, report.getCompanyName());
            ps.setString(3, report.getTaxpayerCode());
            ps.setTimestamp(4, report.getCompletionTime());
            //ps.setLong(5, report.getTotalAmountOfProperty());
            ps.setBoolean(6, report.getIsAccepted());
            ps.setBoolean(7, report.getShouldBeChanged());
            ps.setString(8, report.getInspectorComment());

            ResultSet rs = ps.executeQuery(TaxableItemSQL.GET_TOTAL_AMOUNT_OF_PROPERTY_FOR_CERTAIN_PERSON.getQUERY());
            if (rs.next()){
                long totalAmountOfProperty = rs.getLong("total_amount");
                ps.setLong(5, totalAmountOfProperty);
            }

            ps.execute();

            connection.commit();
        } catch (SQLException e) {
            //connection.rollback();
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
    }

    /**
     * finds User in database.
     *
     * @param id student id.
     */
    @Override
    public Report findById(long id) {
        ReportMapper complaintMapper = new ReportMapper();

        Report result = new Report();
        result.setId(-1);

        try (PreparedStatement ps = connection.prepareStatement(ReportSQL.READ_ONE.getQUERY())) {

            ps.setLong(1, id);

            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = complaintMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * obtains all Students from database.
     *
     */
    @Override
    public List<Report> findAll() {
        Map<Long, Report> reports = new HashMap<>();

        final String query = ReportSQL.READ_ALL.getQUERY();

        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(query);
            ReportMapper reportMapper = new ReportMapper();

            while (rs.next()) {
                Report report = reportMapper.extractFromResultSet(rs);
                report = reportMapper.makeUnique(reports, report);
            }
//            for (User u: reports.values()) {
//                System.out.println(u.getEmail());
//            }
            return new ArrayList<>(reports.values());
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
            return null;
            //todo optional
        }
    }

    @Override
    public void update(Report report) {
        throw new UnsupportedOperationException ("This action has not yet been developed.");
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException ("This action has not yet been developed.");
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
    public PaginationResult findByPagination(int lowerBound, int upperBound, long idUser) {

        PaginationResult paginationResult = new PaginationResult();

//        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM reports " +
//                " where id_person=" + idUser +
//                " order by completion_time DESC " +
//                " limit "
//                + lowerBound + ", " + upperBound;

        Map<Long, Report> reports = new HashMap<>();
        ReportMapper reportMapper = new ReportMapper();

        try (PreparedStatement ps = connection.prepareStatement(ReportSQL.GET_REPORTS_BY_PAGINATION.getQUERY())) {
            ps.setLong(1, idUser);
            ps.setInt(2, lowerBound);
            ps.setInt(3, upperBound);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Report report = reportMapper.extractFromResultSet(rs);
                report = reportMapper.makeUnique(reports, report);
            }
            rs.close();

            rs = ps.executeQuery("SELECT FOUND_ROWS()");
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

    /**
     * It is user-defined class just for returning result from findByPagination() method.
     */
    public class PaginationResult {
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


