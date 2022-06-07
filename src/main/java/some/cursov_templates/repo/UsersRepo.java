package some.cursov_templates.repo;

import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import some.cursov_templates.entity.User;

import static some.cursov_templates.Constants.*;

@Repository
public interface UsersRepo extends JpaRepository<User, Integer> {

    @Query(value =
            "select * from " + TABLE_USERS + " where " + ENTITY_NAME + " = ?1 limit 1",
        nativeQuery = true)
    @Nullable
    User getByName(String name);

    @Query(value =
        "select * from " + TABLE_USERS + " where " + USER_FIRST_NAME + " = ?1 and " +
            USER_LAST_NAME + " = ?2 limit 1",
        nativeQuery = true)
    @Nullable
    User getByFirstAndLastName(String f, String l);

    @Deprecated
    @Override
    default User getById(Integer id) {
        throw new RuntimeException("Use findById instead");
    }
}
