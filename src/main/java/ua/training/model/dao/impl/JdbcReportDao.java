package ua.training.model.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.ReportDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.queries.ComplaintSQL;
import ua.training.model.dao.impl.queries.ReportSQL;
import ua.training.model.dao.impl.queries.UserSQL;
import ua.training.model.dao.mapper.ComplaintMapper;
import ua.training.model.dao.mapper.ReportMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.Complaint;
import ua.training.model.entity.Report;
import ua.training.model.entity.User;

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

        try (PreparedStatement ps = connection.prepareStatement(ReportSQL.INSERT.getQUERY())) {

            ps.setLong(1, report.getPerson().getId());
            ps.setString(2, report.getCompanyName());
            ps.setString(3, report.getTaxpayerCode());
            ps.setTimestamp(4, report.getCompletionTime());
            ps.setLong(5, report.getTotalAmountOfProperty());
            ps.setBoolean(6, report.isAccepted());
            ps.setBoolean(7, report.isShouldBeChanged());
            ps.setString(8, report.getInspectorComment());
            ps.execute();

        } catch (SQLException e) {
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


}


