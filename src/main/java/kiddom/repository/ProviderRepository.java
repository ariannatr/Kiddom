package kiddom.repository;

import kiddom.model.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Arianna on 2/6/2017.
 */
@Repository("providerRepository")
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {
   // ProviderEntity findByUsername(String username);
}
