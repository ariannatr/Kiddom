package kiddom.service;

import org.springframework.stereotype.Service;
import kiddom.model.UserEntity;
import kiddom.model.ParentEntity;

@Service("userService")
public interface UserService {
	public UserEntity findByUsername(String username);
	public void saveUser(UserEntity user, ParentEntity parent);
	public UserEntity findByUsernamePassword(String username,String Password);
}
