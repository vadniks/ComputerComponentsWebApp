package some.cursov_templates.repo;

import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import some.cursov_templates.entity.User;

import java.util.List;

import static some.cursov_templates.Constants.*;

@Repository
public interface UsersRepo extends JpaRepository<User, Integer> {

    @Query(value =
            "select * from " + TABLE_USERS + " where " + ENTITY_NAME + " = ?1 limit 1",
        nativeQuery = true)
    @Nullable
    User getByName(String name);

    @Query(value =
            "select * from " + TABLE_USERS + " where ?1 = ?2",
        nativeQuery = true)
    <T> List<User> getAllBy(String byWhich, T selection);
}
