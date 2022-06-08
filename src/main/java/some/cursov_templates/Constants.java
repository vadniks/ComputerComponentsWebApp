package some.cursov_templates;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import some.cursov_templates.entity.PcComponent;
import some.cursov_templates.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    public static final String ROLE_USER = "role0";
    public static final String ROLE_ADMIN = "role1";
    public static final String UTF_8 = "UTF_8";
    public static final char SELECTIONS_SEPARATOR = ',';
    public static final EmptyResponse STATUS_NOT_OK = new EmptyResponse(HttpStatus.BAD_REQUEST);

    /** Database */
    public static final String DB_NAME = "postgres";
    // TODO: for dockerized start use "jdbc:postgresql://postgres:5432/", for usual (in IDE) use "jdbc:postgresql://localhost:5432/"
    public static final String JDBC_URL = "jdbc:postgresql://postgres:5432/" + DB_NAME;
    public static final String DRIVER_NAME = "org.postgresql.Driver";
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = DB_USERNAME;
    public static final String DB_DIALECT = "org.hibernate.dialect.PostgreSQL92Dialect";
    public static final String DB_DIALECT_PROP = "hibernate.dialect";
    public static final String DB_HBM = "update";
    public static final String DB_HBM_PROP =  "hibernate.hbm2ddl.auto";
    public static final String DB_INIT_SCHEMA_PROP = "spring.session.jdbc.initialize-schema";
    public static final String DB_INIT_SCHEMA = "always";

    /** Tables */
    public static final String TABLE_COMPONENTS = "components";
    public static final String TABLE_USERS = "users";

    /** Entities */
    public static final String ENTITY_ID = "id";
    public static final String ENTITY_NAME = "name";
    public static final String COMPONENT_TYPE = "type";
    public static final String COMPONENT_DESCRIPTION = "description";
    public static final String COMPONENT_COST = "cost";
    public static final String COMPONENT_IMAGE = "image";
    public static final String USER_ROLE = "role";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRST_NAME = "firstName";
    public static final String USER_LAST_NAME = "lastName";
    public static final String USER_PHONE = "phone";
    public static final String USER_ADDRESS = "address";
    public static final String USER_SELECTIONS = "selections";

    /** Endpoints */
    public static final String ENDPOINT_INDEX = EMPTY + ROOT;
    public static final String ENDPOINT_BROWSE = "/brw";
    public static final String ENDPOINT_LOGIN = "/lgn";
    public static final String ENDPOINT_REGISTER = "/rgs";
    public static final String ENDPOINT_ADMIN = "/adm";
    public static final String ENDPOINT_ABOUT = "/abt";
    public static final String GET_COMPONENT = "/cmp";
    public static final String POST_SELECT = "/slc"; //TODO: rename to choose
    public static final String POST_CLEAR = "/clr";
    public static final String POST_LOGOUT = "/lgo";
    public static final String ENDPOINT_ERROR = "/error";
    public static final String GET_USER = "/usr";
    public static final String POST_REMOVE = "/rmv";
    public static final String POST_INSERT_OR_UPDATE_COMPONENT = "/iouc";
    public static final String POST_INSERT_OR_UPDATE_USER = "/iouu";
    public static final String HAS_ROLE_ADMIN = "hasRole(" + ROLE_ADMIN + ')';
    public static final String IS_ANONYMOUS = "isAnonymous()";
    public static final String POST_ORDER = "/ord";
    public static final String HAS_ROLE_USER = "hasRole(" + ROLE_USER + ')';
    public static final String POST_CANCEL_ORDER = "/cnl";

    /** Pages */
    public static final String PAGE_INDEX = "index";
    public static final String PAGE_BROWSE = "browse";
    public static final String PAGE_LOGIN = "login";
    public static final String PAGE_REGISTER = "register";
    public static final String PAGE_ADMIN = "admin";
    public static final String PAGE_ABOUT = "about";
    public static final String PAGE_ERROR = "error";

    /** Resources */
    public static final String RESOURCE_STATIC_NAME = "static";
    public static final String RESOURCE_TEMPLATES_NAME = "templates";
    public static final String RESOURCE_STATIC = "/static/**";
    public static final String RESOURCE_STATIC_LOCATION = "classpath:/static/";
    public static final String RESOURCE_BACK_END = "/res_back/**";
    public static final String RESOURCE_BACK_END_LOCATION = "classpath:/res_back/";
    public static final String RESOURCES_DIR_FROM_HTML = "../../static/res";
    public static final String RESOURCES_BACK_END_DIR_FROM_HTML = "../../res_back";

    /** Templates */
    public static final String TEMPLATE_PREFIX = "classpath:/templates/";
    public static final String TEMPLATE_POSTFIX = ".html";
    public static final String TEMPLATE_MESSAGES_NAME = "org/springframework/security/messages";
    public static final String COMPONENT_IMAGE_PREFIX = "pc_";
    public static final String COMPONENT_IMAGE_POSTFIX = ".jpg";

    /** Attributes */
    public static final String ATTRIBUTE_ITEMS = "items";
    public static final String ATTRIBUTE_ON_CLICK = "on_click";
    public static final String ATTRIBUTE_TYPE = "type";
    public static final String ATTRIBUTE_TOTAL = "total";
    public static final String ATTRIBUTE_COMPONENTS = "components";
    public static final String ATTRIBUTE_USERS = "users";
    public static final String ATTRIBUTE_HAS_ORDERED = "has_ordered";

    /** Sessions */
    public static final String SESSION_CHOSEN_ITEMS = "chosen_items";

    /** Components */
    public static final String UNSELECTED_COST = "0$";
    public static final String UNSELECTED_DESCRIPTION = "Not Selected";

    /** Hyper references */
    public static final String FROM_BROWSE_TO_INDEX_WITH_TYPE = ENDPOINT_BROWSE + "?type=";
    public static final String FROM_LOGIN_TO_LOGIN_WITH_ERROR = ENDPOINT_LOGIN + "?error=true";
    public static final String REDIRECT = "redirect:";
    public static final String REDIRECT_TO_INDEX = REDIRECT + ENDPOINT_INDEX;
    public static final String REDIRECT_TO_ERROR = REDIRECT + ENDPOINT_ERROR;

    /** Metadata */
    @Target(FIELD) @Retention(SOURCE) public @interface ImplicitAutowire {}
    @Target(TYPE) @Retention(SOURCE) public @interface TypeAlias {}

    /** Classes begin */

    @TypeAlias public static class StringPairMap extends HashMap<String, String>
    { public StringPairMap() { super(); }
      public StringPairMap set(String a, String b) { put(a, b); return this; } }

    @TypeAlias public static class Items extends ArrayList<StringPairMap>
    { public Items(int initialCapacity) { super(initialCapacity); } }

    @TypeAlias public static class EmptyResponse extends ResponseEntity<Void>
    { public EmptyResponse(HttpStatus status) { super(status); } }

    @RequiredArgsConstructor
    public static class Pair<A, B> { public final A a; public final B b; }

    @TypeAlias public static class ComponentsAndUsers extends Pair<List<PcComponent>, List<User>>
    { public ComponentsAndUsers(List<PcComponent> a, List<User> b) { super(a, b); } }

    /** Aliases end */

    /** Functions begin */

    public static String toStr(int a) { return Integer.toString(a); }
    public static String toStr(char a) { return Character.toString(a); }
    public static int toInt(String a) { return Integer.parseInt(a); }
    public static boolean notNullNotEmpty(@Nullable String a) { return a != null && !a.isEmpty(); }

    public static String rmDollar(String a)
    { val b = a.indexOf(DOLLAR);
      assert b > 0;
      return a.substring(0, b); }

    @TestOnly @Deprecated public static void debug(String a) { debug(a, EMPTY); }
    @TestOnly @Deprecated public static void debug(String a, Object... b)
    { System.err.println(a + '\t' + Arrays.toString(b)); }

    public static boolean isNotAuthenticated(HttpServletRequest a) { return a.getRemoteUser() == null; }

    /** Functions end */

    @SneakyThrows
    private Constants() { throw new IllegalAccessException(); }
}
