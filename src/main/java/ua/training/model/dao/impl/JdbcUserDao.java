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

    private static JdbcUserDao instance;

    private JdbcUserDao() {

    }

    public static JdbcUserDao getInstance() {
        if (instance == null) {
            synchronized (JdbcUserDao.class) {
                if (instance == null) {
                    instance = new JdbcUserDao();
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
//            ps.setLong(6, user.getAssignedInspector().getId());

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
     * obtains all users from database.
     */
    @Override
    public List<User> findAll() {
        Map<Long, User> users = new HashMap<>();

        final String query = UserSQL.READ_ALL.getQUERY();

        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery(query);
            return mapFindManyResultSet(rs, users);
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findAllInspectors() {
        Map<Long, User> users = new HashMap<>();

        final String query = UserSQL.READ_ALL_INSPECTORS.getQUERY();

        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery(query);
            return mapFindManyResultSet(rs, users);
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception:", e);
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<User> findAssignedByInspector(final long idInspector) {
        Map<Long, User> users = new HashMap<>();

        try (PreparedStatement ps = connection.prepareStatement(UserSQL.READ_ALL_BY_INSPECTOR_ID.getQUERY())) {
            ps.setLong(1, idInspector);
            final ResultSet rs = ps.executeQuery();
            return mapFindManyResultSet(rs, users);
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
            return null;
        }
    }


    //utility method. created in order not to duplicate code below
    private List<User> mapFindManyResultSet(ResultSet rs, Map<Long, User> users) throws SQLException {
        final UserMapper userMapper = new UserMapper();
        while (rs.next()) {
            User user = userMapper.extractFromResultSet(rs);
            user = userMapper.makeUnique(users, user);
        }
        return new ArrayList<>(users.values());
    }


    @Override
    public void assignInspector(User client, User inspector) {
        try (PreparedStatement ps = connection.prepareStatement(UserSQL.ASSIGN_INSPECTOR_TO_CLIENT.getQUERY())) {

            ps.setLong(1, inspector.getId());
            ps.setLong(2, client.getId());

            ps.execute();

        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("This action has not yet been developed.");
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
     * Uses for obtaining user's role.
     *
     * @param email    String
     * @param password String
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
     * @param email    String
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
}


