package kiddom.service;

import kiddom.repository.CookieRepository;
import kiddom.repository.ParentRepository;
import kiddom.repository.ProviderRepository;
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

    @Qualifier("parentRepository")
	@Autowired
    private ParentRepository parentRepository;
    @Qualifier("providerRepository")
    @Autowired
    private ProviderRepository providerRepository;
    @Qualifier("cookieRepository")
    @Autowired
    private CookieRepository cookieRepository;
  //  @Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
		//return null;
	}

	@Override
    public UserEntity findByUsernamePassword(String username,String password){
        UserEntity userExists = findByUsername(username);
        if(userExists != null)
        {
            if(userExists.getPassword().equals(password))
            {
                System.out.println("Same password");
                return userExists;
            }
            else
            {
                System.out.print("Username "+userExists.getUsername()+" pass in base "+userExists.getPassword()+" pass given "+password);
                return null;
            }
        }
        else
        {
            return null;
        }
    }

	@Override
	public void saveUser(UserEntity user, ParentEntity parent) {
		user.setPassword(/*bCryptPasswordEncoder.encode(*/user.getPassword()/*)*/);
		//user = new UserEntity();

		System.out.println("Creating user...");
		//user.setUsername(username);
		//user.setPassword(request.getParameter("passwordsignup"));
		user.setType(1);
		//user.setUserId(user.getUserId());
		//user.setUsername(user.getUsername());



		/*if (request.getParameter("image") != null) {
			parent.setPhoto(request.getParameter("image"));
		}*/

//		dd.insert(user);
//		pdd.insert(parent);
        //Role userRole = roleRepository.findByRole("ADMIN");
        //user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
      //  parent.setUsername(user.getUsername());
        user.setParentByUserId(parent);
        userRepository.save(user);
        CookiesEntity cookie = new CookiesEntity();
        System.out.println("Creating parent user...");
        //parent = new ParentEntity();
        parent.setName(parent.getName());
        parent.setSurname(parent.getSurname());
        parent.setEmail(parent.getEmail());
        parent.setTelephone(parent.getTelephone());
        parent.setTown(parent.getTown());
        parent.setArea(parent.getArea());
        System.out.println("Trying to add "+parent.getName()+" "+parent.getSurname()+"  "+parent.getEmail());


        parent.setAvailPoints(0);
        parent.setRestrPoints(0);
        parent.setTotalPoints(0);
        parent.setUsername(user.getUsername());
        parent.setUserByParentId(user);
        cookie.setCategory("none");
        cookie.setParentByParentId(parent);
        cookie.setUsername(user.getUsername());
		parentRepository.save(parent);
        cookieRepository.save(cookie);

	}

    @Override
    public void saveUserProvider(UserEntity user, ProviderEntity provider) {
        user.setPassword(/*bCryptPasswordEncoder.encode(*/user.getPassword()/*)*/);
        //user = new UserEntity();

        System.out.println("Creating user...");
        user.setType(2);
       /*if (request.getParameter("image") != null) {
			parent.setPhoto(request.getParameter("image"));
		}*/
        user.setProviderByUserId(provider);
        userRepository.save(user);

        System.out.println("Creating provider user...");
        System.out.println("Trying to add "+provider.getName()+" "+provider.getSurname()+"  "+provider.getEmail());
        provider.setApproved(0);
        provider.setGottenPoints(0);
        provider.setOwedPoints(0);
        provider.setUserByProviderId(user);
        provider.setTotalPoints(0);
        System.out.println("Trying to add "+provider.getName()+" "+provider.getSurname()+"  "+provider.getEmail());

        providerRepository.save(provider);


    }

}
