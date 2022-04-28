package some.cursov_server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import some.cursov_server.entity.PcComponent;

@Repository
public interface ComponentsRepo extends JpaRepository<PcComponent, Integer> {

}
