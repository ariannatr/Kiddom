package kiddom.service;

import kiddom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import kiddom.model.*;
//import com.example.model.Role;

//import com.example.repository.RoleRepository;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Qualifier("userRepository")
    @Autowired
	private UserRepository userRepository;
	//@Autowired
    //private RoleRepository roleRepository;
    //@Autowired
  //  private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
		//return null;
	}

	@Override
	public void saveUser(UserEntity user, ParentEntity parent) {
		user.setPassword(/*bCryptPasswordEncoder.encode(*/user.getPassword()/*)*/);
		user = new UserEntity();

		System.out.println("Creating user...");
		//user.setUsername(username);
		//user.setPassword(request.getParameter("passwordsignup"));
		user.setType(1);
		user.setUserId(user.getUserId());
		user.setUsername(user.getUsername());
		user.setParentByUserId(user.getParentByUserId());

		System.out.println("Creating parent user...");
		parent = new ParentEntity();
		parent.setName(parent.getName());
		parent.setSurname(parent.getSurname());
		parent.setEmail(parent.getEmail());
		parent.setTelephone(parent.getTelephone());
		parent.setTown(parent.getTown());
		parent.setArea(parent.getArea());
		/*if (request.getParameter("image") != null) {
			parent.setPhoto(request.getParameter("image"));
		}*/
		parent.setAvailPoints(0);
		parent.setRestrPoints(0);
		parent.setTotalPoints(0);
//		dd.insert(user);
//		pdd.insert(parent);
        //Role userRole = roleRepository.findByRole("ADMIN");
        //user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
//		parentRepository.save(parent);
	}

}
