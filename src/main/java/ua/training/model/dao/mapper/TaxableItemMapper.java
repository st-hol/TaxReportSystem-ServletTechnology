package ua.training.model.dao.mapper;


import ua.training.model.entity.Complaint;
import ua.training.model.entity.TaxableItem;
import ua.training.model.service.TaxableItemService;
import ua.training.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TaxableItemMapper implements ObjectMapper<TaxableItem> {

    private static final String ID_ITEM = "id_item";
    private static final String NAME_ITEM = "name_item";
    private static final String PRICE = "price";


    @Override
    public TaxableItem extractFromResultSet(ResultSet rs) throws SQLException {

        TaxableItem taxableItem = new TaxableItem();

        taxableItem.setId(rs.getInt(ID_ITEM));
        taxableItem.setName(rs.getString(NAME_ITEM));
        taxableItem.setPrice(rs.getLong(PRICE));

        return taxableItem;
    }

    @Override
    public TaxableItem makeUnique(Map<Long, TaxableItem> existing, TaxableItem entity) {
        existing.putIfAbsent(entity.getId(), entity);

        return existing.get(entity.getId());
    }
}
