package kiddom.service;

import kiddom.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;


@Service("userService")
public interface UserService {
	public UserEntity findByUsername(String username);
	public void saveUser(UserEntity user, ParentEntity parent);
	public void saveUserProvider(UserEntity user, ProviderEntity provider);
	public UserEntity findByUsernamePassword(String username, String Password);
	public  ParentEntity findParent(ParentPK parentPk);
	public ProviderEntity findProvider(ProviderPK providerPk);
	public void updateUserParent(ParentEntity parenton,ParentEntity parent,UserEntity useron,UserEntity user);
	public void updateUserPoints(ParentEntity parenton,ParentEntity parent,UserEntity useron,UserEntity user);
	public void updateUserProvider(ProviderEntity provideron,ProviderEntity provider,UserEntity useron,UserEntity user);


}
