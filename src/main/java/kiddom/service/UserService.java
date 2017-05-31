package kiddom.service;

import kiddom.model.ParentEntity;
import kiddom.model.UserEntity;
import org.springframework.stereotype.Service;

@Service("userService")
public interface UserService {
	public UserEntity findByUsername(String username);
	public void saveUser(UserEntity user, ParentEntity parent);
}
