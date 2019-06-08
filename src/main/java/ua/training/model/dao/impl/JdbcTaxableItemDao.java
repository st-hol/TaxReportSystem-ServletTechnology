package ua.training.model.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.ComplaintDao;
import ua.training.model.dao.TaxableItemDao;
import ua.training.model.dao.impl.queries.ComplaintSQL;
import ua.training.model.dao.impl.queries.TaxableItemSQL;
import ua.training.model.dao.mapper.ComplaintMapper;
import ua.training.model.dao.mapper.TaxableItemMapper;
import ua.training.model.entity.Complaint;
import ua.training.model.entity.TaxableItem;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTaxableItemDao implements TaxableItemDao {

    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JdbcTaxableItemDao.class);
    private static JdbcTaxableItemDao instance;

    private JdbcTaxableItemDao() {

    }

    public static JdbcTaxableItemDao getInstance() {
        if (instance == null) {
            synchronized (JdbcTaxableItemDao.class) {
                if (instance == null) {
                    instance = new JdbcTaxableItemDao();
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
     * Create record in database.
     *
     * @param taxableItem for create.
     */
    @Override
    public void create(@NotNull final TaxableItem taxableItem) {

        try (PreparedStatement ps = connection.prepareStatement(TaxableItemSQL.INSERT.getQUERY())) {

            ps.setLong(1, taxableItem.getId());
            ps.setString(2, taxableItem.getName());
            ps.setLong(1, taxableItem.getPrice());

            ps.execute();

        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
    }

    /**
     * finds item in database.
     *
     * @param id student id.
     */
    @Override
    public TaxableItem findById(long id) {
        TaxableItemMapper taxableItemMapper = new TaxableItemMapper();

        TaxableItem result = new TaxableItem();
        result.setId(-1);

        try (PreparedStatement ps = connection.prepareStatement(TaxableItemSQL.READ_ONE.getQUERY())) {

            ps.setLong(1, id);

            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = taxableItemMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * obtains all items from database.
     */
    @Override
    public List<TaxableItem> findAll() {
        Map<Long, TaxableItem> taxableItems = new HashMap<>();

        final String query = TaxableItemSQL.READ_ALL.getQUERY();

        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(query);
            TaxableItemMapper taxableItemMapper = new TaxableItemMapper();

            while (rs.next()) {
                TaxableItem taxableItem = taxableItemMapper.extractFromResultSet(rs);
                taxableItem = taxableItemMapper.makeUnique(taxableItems, taxableItem);
            }

            return new ArrayList<>(taxableItems.values());
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(TaxableItem taxableItem) {
        try (PreparedStatement ps = connection.prepareStatement(TaxableItemSQL.CHANGE_TAXABLE_FOR_PERSON.getQUERY())) {

            ps.setInt(1, taxableItem.getQuantity());
            ps.setLong(2, taxableItem.getIdPerson());
            ps.setLong(3, taxableItem.getId());

            ps.execute();

        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
    }

    @Override
    public void regTaxableToPerson(TaxableItem taxableItem) {
        try (PreparedStatement ps = connection.prepareStatement(TaxableItemSQL.REG_TAXABLE_PER_PERSON.getQUERY())) {

            ps.setLong(1, taxableItem.getIdPerson());
            ps.setLong(2, taxableItem.getId());
            ps.setInt(3, taxableItem.getQuantity());

            ps.execute();

        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
    }

    @Override
    public boolean isAlreadyExisting(TaxableItem taxableItem) {
        try (PreparedStatement ps = connection.prepareStatement(TaxableItemSQL.ALREADY_EXISTING_TAXABLE.getQUERY())) {

            ps.setLong(1, taxableItem.getIdPerson());
            ps.setLong(2, taxableItem.getId());

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


}


