package kiddom.service;

import kiddom.model.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service("userService")
public interface UserService {
	public UserEntity findByUsername(String username);
	public void saveUser(UserEntity user, ParentEntity parent);
	public void saveUserProvider(UserEntity user, ProviderEntity provider);
	public UserEntity findByUsernamePassword(String username, String Password);
	public void saveActivity(UserEntity user, ProviderEntity provider, SingleEventEntity event, HashSet<ProgramEntity> program);
	public  ParentEntity findParent(ParentPK parentPk);
	public ProviderEntity findProvider(ProviderPK providerPk);
	public void updateUserParent(ParentEntity parenton,ParentEntity parent,UserEntity useron,UserEntity user);
	public void updateUserPoints(ParentEntity parenton,ParentEntity parent,UserEntity useron,UserEntity user);
	public void updateUserProvider(ProviderEntity provideron,ProviderEntity provider,UserEntity useron,UserEntity user);
}
