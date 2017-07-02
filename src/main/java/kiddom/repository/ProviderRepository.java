package kiddom.repository;

import kiddom.model.ProviderEntity;
import kiddom.model.ProviderPK;
import kiddom.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Arianna on 2/6/2017.
 */
@Repository("providerRepository")
public interface ProviderRepository extends PagingAndSortingRepository<ProviderEntity, ProviderPK> {
   // ProviderEntity findByUsername(String username);
}
