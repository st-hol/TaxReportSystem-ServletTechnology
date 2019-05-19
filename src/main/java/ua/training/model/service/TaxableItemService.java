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

    private DaoFactory daoFactory = DaoFactory.getInstance();


    /**
     * obtains item by id.
     *
     * @param id long.
     */
    public TaxableItem getTaxableItemById(long id){
        try (TaxableItemDao dao = daoFactory.createTaxableItemDao()) {
            return dao.findById(id);
        }
    }

    /**
     * obtains List of all students.
     */
    public List<TaxableItem> getAllTaxableItems() {
        try (TaxableItemDao dao = daoFactory.createTaxableItemDao()) {
            return dao.findAll();
        }
    }


}


//todo Optional