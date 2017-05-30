package kiddom.service;

import kiddom.model.UserEntity;


public interface UserService {
	public UserEntity findUserUsername(String email);
	public void saveUser(UserEntity user);
}
