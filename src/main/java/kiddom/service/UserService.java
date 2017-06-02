package kiddom.service;

import kiddom.model.ProviderEntity;
import org.springframework.stereotype.Service;
import kiddom.model.UserEntity;
import kiddom.model.ParentEntity;

@Service("userService")
public interface UserService {
	public UserEntity findByUsername(String username);
	public void saveUser(UserEntity user, ParentEntity parent);
	public void saveUserProvider(UserEntity user, ProviderEntity provider);
	public UserEntity findByUsernamePassword(String username,String Password);
}
