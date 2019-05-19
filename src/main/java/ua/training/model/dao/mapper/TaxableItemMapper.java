package ua.training.model.dao.mapper;


import ua.training.model.entity.Complaint;
import ua.training.model.entity.TaxableItem;
import ua.training.model.service.TaxableItemService;
import ua.training.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TaxableItemMapper implements ObjectMapper<TaxableItem> {

    @Override
    public TaxableItem extractFromResultSet(ResultSet rs) throws SQLException {
        TaxableItemService taxableItemService = new TaxableItemService();

        TaxableItem taxableItem = new TaxableItem();

        taxableItem.setId(rs.getInt("id_item"));
        taxableItem.setName(rs.getString("name_item"));
        taxableItem.setPrice(rs.getLong("price"));

        return taxableItem;
    }

    @Override
    public TaxableItem makeUnique(Map<Long, TaxableItem> existing, TaxableItem entity) {
        existing.putIfAbsent(entity.getId(), entity);

        return existing.get(entity.getId());
    }
}
