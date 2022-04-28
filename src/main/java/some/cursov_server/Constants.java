package some.cursov_server;

import lombok.SneakyThrows;

public final class Constants {

    /** Common */
    public static final String EMPTY = "";

    /** Database */
    public static final String DB_NAME = "db";
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
    public static final String DRIVER_NAME = "org.postgresql.Driver";
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = DB_USERNAME;
    public static final String DB_DIALECT = "org.hibernate.dialect.PostgreSQL92Dialect";
    public static final String DB_DIALECT_PROP = "hibernate.dialect";
    public static final String DB_HBM = "update";
    public static final String DB_HBM_PROP =  "hibernate.hbm2ddl.auto";

    /** Tables */
    public static final String TABLE_COMPONENTS = "components";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_SELECTIONS = "selections";

    /** Enpoints */
    public static final String ENDPOINT_INDEX = EMPTY;

    @SneakyThrows
    private Constants() { throw new IllegalAccessException(); }
}
