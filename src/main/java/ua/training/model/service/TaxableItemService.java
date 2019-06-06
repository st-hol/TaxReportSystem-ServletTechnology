package ua.training.model.service;


import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TaxableItemDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.JdbcUserDao;
import ua.training.model.entity.TaxableItem;
import ua.training.model.entity.User;
import ua.training.model.exception.AlreadyExistingDBRecordException;

import java.util.List;
import java.util.Random;


/**
 * This class realize logic
 * for manipulation with db.
 *
 * @author Stanislav Holovachuk
 */
public class TaxableItemService {

    private DaoFactory daoFactory;

    private static TaxableItemService instance;

    private TaxableItemService() {
        daoFactory = DaoFactory.getInstance();
    }

    public static TaxableItemService getInstance() {
        if (instance == null) {
            synchronized (TaxableItemService.class) {
                if (instance == null) {
                    instance = new TaxableItemService();
                }
            }
        }
        return instance;
    }


    /**
     * obtains item by id.
     *
     * @param id long.
     */
    public TaxableItem getTaxableItemById(long id) {
        TaxableItemDao dao = daoFactory.createTaxableItemDao();
        return dao.findById(id);
    }

    /**
     * obtains List of all students.
     */
    public List<TaxableItem> getAllTaxableItems() {
        TaxableItemDao dao = daoFactory.createTaxableItemDao();
        return dao.findAll();
    }

    public void setTaxableItemsPerPerson(TaxableItem taxableItem) {
        TaxableItemDao dao = daoFactory.createTaxableItemDao();
        if (alreadyExist(taxableItem)) {
            dao.update(taxableItem);
        } else {
            dao.regTaxableToPerson(taxableItem);
        }
    }


    public boolean alreadyExist(TaxableItem taxableItem) {
        TaxableItemDao dao = daoFactory.createTaxableItemDao();
        return dao.isAlreadyExisting(taxableItem);
    }

}