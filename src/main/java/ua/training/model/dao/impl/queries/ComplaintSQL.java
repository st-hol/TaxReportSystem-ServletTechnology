package ua.training.model.dao.impl.queries;

/**
 * SQL queries for students table.
 */
public enum ComplaintSQL {
    READ_ONE("select * from complaints where id_complaint=(?)"),

    READ_ALL("select * from complaints"),

    INSERT("INSERT INTO complaints " +
            "(id_person, content, completion_time) " +
            "VALUES ((?),(?),(?))"),

    DELETE(""),
    UPDATE("");

    String QUERY;

    ComplaintSQL(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}