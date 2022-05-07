package some.cursov_templates;

import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.TestOnly;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

public final class Constants {

    /** Common */
    public static final String EMPTY = "";
    public static final String PACKAGE = "some.cursov_templates";
    public static final String PACKAGES = PACKAGE + ".*";
    public static final EmptyResponse STATUS_OK = new EmptyResponse(HttpStatus.OK);
    public static final char DOLLAR = '$';
    public static final char ROOT = '/';
    public static final String TOTAL_COST = "Total cost: ";

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
    public static final String ENTITY_ID = "id";
    public static final String ENTITY_NAME = "name";
    public static final String COMPONENT_TYPE = "type";
    public static final String COMPONENT_DESCRIPTION = "description";
    public static final String COMPONENT_COST = "cost";
    public static final String COMPONENT_IMAGE = "image";
    public static final String SELECTION_BUYER = "buyer";
    public static final String SELECTION_BOUGHT = "bought";
    public static final String USER_ROLE = "role";
    public static final String USER_PASSWORD = "password";

    /** Endpoints */
    public static final String ENDPOINT_INDEX = EMPTY + ROOT;
    public static final String ENDPOINT_BROWSE = "/brw";
    public static final String ENDPOINT_LOGIN = "/lgn";
    public static final String ENDPOINT_REGISTER = "/rgs";
    public static final String ENDPOINT_ADMIN = "/adm";
    public static final String ENDPOINT_ABOUT = "/abt";
    public static final String ENDPOINT_COMPONENT = "/cmp";
    public static final String ENDPOINT_SELECT = "/slc";
    public static final String ENDPOINT_CLEAR = "/clr";

    /** Pages */
    public static final String PAGE_INDEX = "index";
    public static final String PAGE_BROWSE = "browse";
    public static final String PAGE_LOGIN = "login";
    public static final String PAGE_REGISTER = "register";
    public static final String PAGE_ADMIN = "admin";
    public static final String PAGE_ABOUT = "about";

    /** Resources */
    public static final String RESOURCE_STATIC = "/static/**";
    public static final String RESOURCE_STATIC_LOCATION = "classpath:/static/";
    public static final String RESOURCE_BACK_END = "/res_back/**";
    public static final String RESOURCE_BACK_END_LOCATION = "classpath:/res_back/";
    public static final String RESOURCES_DIR_FROM_HTML = "../../static/res";
    public static final String RESOURCES_BACK_END_DIR_FROM_HTML = "../../res_back";

    /** Templates */
    public static final String TEMPLATE_PREFIX = "classpath:/templates/";
    public static final String TEMPLATE_POSTFIX = ".html";
    public static final String TEMPLATE_MESSAGES_NAME = "messages";
    public static final String COMPONENT_IMAGE_PREFIX = "pc_";
    public static final String COMPONENT_IMAGE_POSTFIX = ".jpg";

    /** Attributes */
    public static final String ATTRIBUTE_ITEMS = "items";
    public static final String ATTRIBUTE_ON_CLICK = "on_click";
    public static final String ATTRIBUTE_TYPE = "type";
    public static final String ATTRIBUTE_TOTAL = "total";

    /** Sessions */
    public static final String SESSION_CHOSEN_ITEMS = "chosen_items";

    /** Components */
    public static final String UNSELECTED_COST = "0$";
    public static final String UNSELECTED_DESCRIPTION = "Not Selected";

    /** Hyper references */
    public static final String FROM_BROWSE_TO_INDEX_WITH_TYPE = "/brw?type=";

    /** Metadata */
    @Target(FIELD) @Retention(SOURCE) public @interface ImplicitAutowire {}
    @Target(TYPE) @Retention(SOURCE) public @interface TypeAlias {}

    /** Aliases (emulating type aliases) begin */

    @TypeAlias public static class StringPairMap extends HashMap<String, String>
    { public StringPairMap() { super(); }
      public StringPairMap set(String a, String b) { put(a, b); return this; } }

    @TypeAlias public static class Items extends ArrayList<StringPairMap>
    { public Items(int initialCapacity) { super(initialCapacity); } }

    @TypeAlias public static class EmptyResponse extends ResponseEntity<Void>
    { public EmptyResponse(HttpStatus status) { super(status); } }

    /** Aliases end */

    /** Functions begin */

    public static String toStr(int a) { return Integer.toString(a); }
    public static String toStr(char a) { return Character.toString(a); }
    public static int toInt(String a) { return Integer.parseInt(a); }

    public static String rmDollar(String a)
    { val b = a.indexOf(DOLLAR);
      if (b <= 0) throw new IllegalStateException();
      return a.substring(0, b); }

    @TestOnly @Deprecated public static void debug(String a, Object b)
    { System.err.println(a + '\t' + b); }

    /** Functions end */

    @SneakyThrows
    private Constants() { throw new IllegalAccessException(); }
}
