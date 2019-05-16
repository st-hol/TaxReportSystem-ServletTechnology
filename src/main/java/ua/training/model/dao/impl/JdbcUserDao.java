package ua.training.model.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.queries.UserSQL;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.User;


import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.*;

public class JdbcUserDao implements UserDao {

    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JdbcUserDao.class);

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }


    /**
     * Create User(user/admin) in database.
     *
     * @param user for create.
     */
    @Override
    public void create(@NotNull final User user) {

        try (PreparedStatement ps = connection.prepareStatement(UserSQL.INSERT.getQUERY())) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());

            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole().getRoleID());

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
    public User findById(long id) {
        UserMapper userMapper = new UserMapper();

        User result = new User();
        result.setId(-1);

        try (PreparedStatement ps = connection.prepareStatement(UserSQL.READ_ONE.getQUERY())) {

            ps.setLong(1, id);

            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = userMapper.extractFromResultSet(rs);
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
    public List<User> findAll() {
        Map<Long, User> users = new HashMap<>();

        final String query = UserSQL.READ_ALL.getQUERY();

        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(query);
            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
            }
//            for (User u: users.values()) {
//                System.out.println(u.getEmail());
//            }
            return new ArrayList<>(users.values());
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
            return null;
            //todo optional
        }
    }

    @Override
    public void update(User user) {
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
     * Uses for obtaining user's role.
     *
     * @param email String
     * @param  password String
     * @return User.ROLE
     */
    @Override
    public User.ROLE getRoleByEmailPassword(final String email, final String password) {
        User.ROLE result = User.ROLE.UNKNOWN;

        try (PreparedStatement ps = connection.prepareStatement(UserSQL.READ_BY_EMAIL_PASSWORD.getQUERY())) {

            ps.setString(1, email);
            ps.setString(2, password);

            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = User.ROLE.getRoleById(rs.getInt("id_role"));
            }
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * searches User in database by email and password
     *
     * @param email String
     * @param password String
     * @return boolean
     */
    @Override
    public boolean userIsExist(final String email, final String password) {

        try (PreparedStatement ps = connection.prepareStatement(UserSQL.READ_BY_EMAIL_PASSWORD.getQUERY())) {

            ps.setString(1, email);
            ps.setString(2, password);

            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        return false;
    }


    /**
     * checks if email in database is already taken
     *
     * @param email String
     * @return boolean
     */
    @Override
    public boolean emailIsAlreadyTaken(final String email) {

        try (PreparedStatement ps = connection.prepareStatement(UserSQL.READ_BY_EMAIL.getQUERY())) {

            ps.setString(1, email);

            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        return false;
    }


    /**
     * SQL query obtains all Students limited by lower and upper bounds ordered by descent according to rating
     * and quantity of all records got from database.
     *
     * @param lowerBound integer value.
     * @param upperBound integer value.
     * @return object of user-defined class PaginationResult. Which contains of two fields:
     * 1)the List of obtained Students.
     * 2)number of records was read.
     */
    @Override
    public PaginationResult findByPagination(int lowerBound, int upperBound) {

        PaginationResult paginationResult = new PaginationResult();

        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM application_for_admission as app " +
                "left join students as st on app.id_student = st.id_student " +
                "where app.is_enrolled = 1 order by st.rating DESC " +
                "limit  "
                + lowerBound + ", " + upperBound;

        Map<Long, User> users = new HashMap<>();
        UserMapper userMapper = new UserMapper();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
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
        paginationResult.setResultList(new ArrayList<>(users.values()));
        return paginationResult;
    }

    /**
     * It is user-defined class just for returning result from findByPagination() method.
     */
    public class PaginationResult {
        private int noOfRecords;
        private List<User> resultList;

        public int getNoOfRecords() {
            return noOfRecords;
        }

        public void setNoOfRecords(int noOfRecords) {
            this.noOfRecords = noOfRecords;
        }

        public List<User> getResultList() {
            return resultList;
        }

        public void setResultList(List<User> resultList) {
            this.resultList = resultList;
        }
    }


}


