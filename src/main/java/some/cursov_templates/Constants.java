package some.cursov_templates;

import lombok.SneakyThrows;

public final class Constants {

    /** Common */
    public static final String EMPTY = "";
    public static final String PACKAGE = "some.cursov_templates";
    public static final String PACKAGES = PACKAGE + ".*";

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

    /** Entities */
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String DESCRIPTION = "description";
    public static final String COST = "cost";
    public static final String IMAGE = "image";
    public static final String BUYER = "buyer";
    public static final String BOUGHT = "bought";
    public static final String ROLE = "role";
    public static final String PASSWORD = "password";

    /** Endpoints */
    public static final String ENDPOINT_INDEX = "/";
    public static final String ENDPOINT_BROWSE = "/brw";
    public static final String ENDPOINT_LOGIN = "/lgn";
    public static final String ENDPOINT_REGISTER = "/rgs";
    public static final String ENDPOINT_ADMINISTRATE = "/adm";

    @SneakyThrows
    private Constants() { throw new IllegalAccessException(); }
}
