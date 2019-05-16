package ua.training.model.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.ComplaintDao;
import ua.training.model.dao.impl.queries.ComplaintSQL;
import ua.training.model.dao.mapper.ComplaintMapper;
import ua.training.model.entity.Complaint;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcComplaintDao implements ComplaintDao {

    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JdbcComplaintDao.class);

    public JdbcComplaintDao(Connection connection) {
        this.connection = connection;
    }


    /**
     * Create User(complaint/admin) in database.
     *
     * @param complaint for create.
     */
    @Override
    public void create(@NotNull final Complaint complaint) {

        try (PreparedStatement ps = connection.prepareStatement(ComplaintSQL.INSERT.getQUERY())) {

            ps.setLong(1, complaint.getUser().getId());
            ps.setString(2, complaint.getContent());
            ps.setTimestamp(3, complaint.getCompletionTime());

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
    public Complaint findById(long id) {
        ComplaintMapper complaintMapper = new ComplaintMapper();

        Complaint result = new Complaint();
        result.setId(-1);

        try (PreparedStatement ps = connection.prepareStatement(ComplaintSQL.READ_ONE.getQUERY())) {

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
    public List<Complaint> findAll() {
        Map<Long, Complaint> complaints = new HashMap<>();

        final String query = ComplaintSQL.READ_ALL.getQUERY();

        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(query);
            ComplaintMapper complaintMapper = new ComplaintMapper();

            while (rs.next()) {
                Complaint complaint = complaintMapper.extractFromResultSet(rs);
                complaint = complaintMapper.makeUnique(complaints, complaint);
            }
//            for (User u: complaints.values()) {
//                System.out.println(u.getEmail());
//            }
            return new ArrayList<>(complaints.values());
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
            return null;
            //todo optional
        }
    }

    @Override
    public void update(Complaint complaint) {
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


