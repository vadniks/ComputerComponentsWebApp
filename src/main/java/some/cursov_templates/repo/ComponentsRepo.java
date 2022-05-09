package some.cursov_templates.repo;

import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import some.cursov_templates.entity.PcComponent;

import java.util.List;

import static some.cursov_templates.Constants.*;

@Repository
public interface ComponentsRepo extends JpaRepository<PcComponent, Integer> {

    @Query(value =
            "select * from " + TABLE_COMPONENTS + " where " + COMPONENT_TYPE + " = ?1",
        nativeQuery = true)
    List<PcComponent> getAllByType(Integer type);

    @Nullable
    @Query(value =
            "select " + COMPONENT_TYPE + " from " + TABLE_COMPONENTS + " where " + ENTITY_ID + " = ?1",
        nativeQuery = true)
    Integer getTypeById(Integer id);

    @Query(value =
            "select * from " + TABLE_COMPONENTS + " where id = ?1",
        nativeQuery = true)
    <T> List<PcComponent> getAllBy(T selection);
}
