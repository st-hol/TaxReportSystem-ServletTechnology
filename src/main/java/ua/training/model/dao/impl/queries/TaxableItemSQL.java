package ua.training.model.dao.impl.queries;

/**
 * SQL queries for students table.
 */
public enum TaxableItemSQL {
    READ_ONE("select * from taxable_items where id_item=(?)"),

    READ_ALL("select * from taxable_items"),

    INSERT("INSERT INTO taxable_items " +
            "(id_item, name_item, price) " +
            "VALUES ((?),(?),(?))"),

    DELETE(""),
    UPDATE(""),

    GET_TOTAL_AMOUNT_OF_PROPERTY_FOR_CERTAIN_PERSON("select sum(persons_has_taxable_items.quantity * taxable_items.price) " +
            " as total_amount from taxable_items " +
            " left join persons_has_taxable_items " +
            " on taxable_items.id_item = persons_has_taxable_items.id_item " +
            " left join persons " +
            " on persons_has_taxable_items.id_person = persons.id_person" +
            " where persons.id_person=(?);"),


    ALREADY_EXISTING_TAXABLE("SELECT * FROM persons_has_taxable_items where id_person=(?) and id_item=(?);"),

    REG_TAXABLE_PER_PERSON("INSERT INTO persons_has_taxable_items (id_person, id_item, quantity) VALUES ((?),(?),(?));"),

    CHANGE_TAXABLE_FOR_PERSON("UPDATE persons_has_taxable_items SET quantity=(?) WHERE (id_person =(?))and(id_item=(?));");

    String QUERY;

    TaxableItemSQL(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}