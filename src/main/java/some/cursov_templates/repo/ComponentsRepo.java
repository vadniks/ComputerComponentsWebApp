package some.cursov_templates.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import some.cursov_templates.entity.PcComponent;

@Repository
public interface ComponentsRepo extends JpaRepository<PcComponent, Integer> {

}
