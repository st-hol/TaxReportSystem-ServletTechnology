package ua.training.model.dao.impl.queries;

/**
 * SQL queries for students table.
 */
public enum UserSQL {
    READ_ONE("select * from persons where id_person=(?)"),

    READ_ALL("select * from persons"),

    INSERT("INSERT INTO persons " +
            "(first_name, last_name, email, password, id_role) " +
            "VALUES ((?),(?),(?),(?),(?))"),

    DELETE(""),
    UPDATE(""),

    READ_BY_EMAIL("SELECT * FROM persons where email=(?);"),

    READ_BY_EMAIL_PASSWORD("SELECT * FROM persons where email=(?) and password=(?);"),


    READ_ALL_INSPECTORS("select * from persons where id_role=1"),

    READ_ALL_BY_INSPECTOR_ID("SELECT * FROM persons where id_inspector=(?);"),

    ASSIGN_INSPECTOR_TO_CLIENT("UPDATE persons SET id_inspector=(?) WHERE id_person=(?);");


    String QUERY;

    UserSQL(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}