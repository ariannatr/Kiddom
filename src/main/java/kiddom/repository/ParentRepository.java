package kiddom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kiddom.model.ParentEntity;

@Repository("parentRepository")
public interface ParentRepository extends JpaRepository<ParentEntity, String> {
     ParentEntity findByUsername(String username);
   // void save(String user, ParentEntity parent)
}
