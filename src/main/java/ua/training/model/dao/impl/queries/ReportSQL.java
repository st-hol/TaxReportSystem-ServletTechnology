package ua.training.model.dao.impl.queries;

/**
 * SQL queries for students table.
 */
public enum ReportSQL {
    READ_ONE("select * from reports where id_report=(?)"),

    READ_ALL("select * from reports"),

    INSERT("INSERT INTO reports " +
            "(id_person, company_name, taxpayer_code, completion_time, total_amount_of_property, is_accepted, should_be_changed, inspector_comment) " +
            "VALUES ((?),(?),(?),(?),(?), (?), (?), (?))"),

    DELETE(""),
    UPDATE("");


    String QUERY;

    ReportSQL(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}