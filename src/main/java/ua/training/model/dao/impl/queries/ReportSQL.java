package ua.training.model.dao.impl.queries;

/**
 * SQL queries for students table.
 */
public enum ReportSQL {
    READ_ONE("select * from reports where id_report=(?)"),

    READ_ALL("select * from reports"),

    READ_ALL_REPORTS_MADE_BY_ASSIGNED_CLIENTS("select * from reports " +
            " join persons " +
            " on reports.id_person = persons.id_person" +
            " and persons.id_inspector=(?) order by reports.id_report DESC;"),

    READ_ALL_REPORTS_TO_CHANGE_BY_ID_CLIENT("select * from reports where id_person=(?) and should_be_changed=1 " +
            " order by reports.id_report DESC;"),

    INSERT("INSERT INTO reports " +
            "(id_person, company_name, taxpayer_code, completion_time, total_amount_of_property, is_accepted, should_be_changed, inspector_comment) " +
            "VALUES ((?),(?),(?),(?),(?), (?), (?), (?))"),

    DELETE(""),

    UPDATE("UPDATE reports SET is_accepted=(?), should_be_changed=(?), inspector_comment=(?) WHERE (id_report=(?));"),

    EDIT_REPORT("UPDATE reports SET company_name=(?), taxpayer_code=(?), should_be_changed=(?) WHERE (id_report=(?));"),


    SET_TOTAL_AMOUNT_OF_PROPERTY("SET_TOTAL_AMOUNT_OF_PROPERTY reports SET total_amount_of_property=(?) WHERE id_report=(?);"),

    GET_REPORTS_BY_PAGINATION("SELECT SQL_CALC_FOUND_ROWS * FROM reports " +
            " where id_person=(?) " +
            " order by completion_time DESC " +
            " limit ?, ?;"),

    CALC_REPORTS_BY_PERSON_ID("SELECT count(*) from reports where id_person=(?)");

    String QUERY;

    ReportSQL(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}