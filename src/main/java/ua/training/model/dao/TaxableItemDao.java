package ua.training.model.dao;


import ua.training.model.entity.TaxableItem;

public interface TaxableItemDao extends GenericDao<TaxableItem> {

    void regTaxableToPerson(TaxableItem taxableItem);

    boolean isAlreadyExisting(TaxableItem taxableItem);

}
