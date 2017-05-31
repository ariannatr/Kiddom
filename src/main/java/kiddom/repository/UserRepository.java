package kiddom.repository;

import kiddom.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	 UserEntity findByUsername(String username);

//	void saveUser(UserEntity user, ParentEntity parent);
}
