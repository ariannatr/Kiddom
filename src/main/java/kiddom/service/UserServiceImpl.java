package kiddom.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//import com.example.model.Role;
import kiddom.model.UserEntity;
//import com.example.repository.RoleRepository;
import kiddom.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	//@Autowired
	//private UserRepository userRepository;
	//@Autowired
    //private RoleRepository roleRepository;
    //@Autowired
  //  private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserEntity findUserUsername(String username) {
	//	return userRepository.findByUsername(username);
		return null;
	}

	@Override
	public void saveUser(UserEntity user) {
	//	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setActive(1);
        //Role userRole = roleRepository.findByRole("ADMIN");
        //user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	//	userRepository.save(user);
	}

}
