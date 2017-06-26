package kiddom.service;

import kiddom.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import kiddom.model.*;

import java.security.Provider;
import java.util.Collection;
import java.util.List;
//import com.example.model.Role;

//import com.example.repository.RoleRepository;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Qualifier("userRepository")
    @Autowired
	private UserRepository userRepository;

    @Qualifier("activityRepository")
    @Autowired
    private ActivityRepository activityRepository;

    @Qualifier("parentRepository")
	@Autowired
    private ParentRepository parentRepository;

    @Qualifier("providerRepository")
    @Autowired
    private ProviderRepository providerRepository;

    @Qualifier("cookieRepository")
    @Autowired
    private CookieRepository cookieRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
		//return null;
	}

	@Override
    public  ParentEntity findParent(ParentPK parentPk){
	    ParentEntity parent = parentRepository.findOne(parentPk);
	    return parent;
    }

    @Override
    public ProviderEntity findProvider(ProviderPK providerPk){
        ProviderEntity provider = providerRepository.findOne(providerPk);
        return provider;
    }

	@Override
    public UserEntity findByUsernamePassword(String username,String password){
        UserEntity userExists = findByUsername(username);
        if (userExists != null)
        {
            if (bCryptPasswordEncoder.matches((password),userExists.getPassword()))//
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
            System.out.println("There is no user registered with this uname");
            return null;
        }
    }

	@Override
	public void saveUser(UserEntity user, ParentEntity parent) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		System.out.println("Creating user..." + user.getPassword());
		user.setType(1);
		//if (request.getParameter("image") != null) {
		//	parent.setPhoto(request.getParameter("image"));
		//}
        userRepository.save(user);
        //CookiesEntity cookie = new CookiesEntity();
        System.out.println("Creating parent user...");
        System.out.println("Trying to add " + parent.getName() + " " + parent.getSurname() + "  " + parent.getEmail());
        //parent.setUsername(user.getUsername());
        parent.setUser(user);
        //cookie.setCategory("none");
        //cookie.setParentByParentId(parent);
        //cookie.setUsername(user.getUsername());
		parentRepository.save(parent);
        //cookieRepository.save(cookie);
        System.out.println("Done.");
    }

    @Override
    public void saveUserProvider(UserEntity user, ProviderEntity provider) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println("Creating user...");
        user.setType(2);
       /*if (request.getParameter("image") != null) {
			parent.setPhoto(request.getParameter("image"));
		}*/
		//user.setProviderByUserId(provider);
        userRepository.save(user);
        System.out.println("Creating provider user...");
        System.out.println("Trying to add " + provider.getName() + " " + provider.getSurname() + " " + provider.getEmail());
        provider.setUser(user);
        providerRepository.save(provider);
        System.out.println("Done.");
    }

    @Override
    public void saveActivity(UserEntity user, ProviderEntity provider, SingleEventEntity event)
    {
        /*
        String photos = null;
        if (request.getParameter("image1") != null) {
			photos += request.getParameter("image1");
			photos += "\n";
		}
        if (request.getParameter("image2") != null) {
			photos += request.getParameter("image2");
			photos += "\n";
		}
        if (request.getParameter("image3") != null) {
			photos += request.getParameter("image3");
			photos += "\n";
		}
        if (request.getParameter("image4") != null) {
			photos += request.getParameter("image4");
			photos += "\n";
		}
        if (request.getParameter("image5") != null) {
			photos += request.getParameter("image5");
			photos += "\n";
		}
		event.setPhotos(photos);*/
        event.setProviders(provider);
        System.out.println("Event by " + provider.getPk().getUser().getUsername());
        activityRepository.save(event);
        System.out.println("Done.");
    }

    @Override
    public void updateUserParent(ParentEntity parenton,ParentEntity parent,UserEntity useron,UserEntity user){
        if(!parent.getEmail().replaceAll(" ","").equals("")){
            parenton.setEmail(parent.getEmail());
        }
        if(!parent.getName().replaceAll(" ","").equals(""))
        {
            parenton.setName(parent.getName());
        }
        if(!parent.getSurname().replaceAll(" ","").equals(""))
        {
            parenton.setSurname(parent.getSurname());
        }
        if(!parent.getArea().replaceAll(" ","").equals(""))
        {
            parenton.setArea(parent.getArea());
        }
        if(!parent.getTown().replaceAll(" ","").equals(""))
        {
            parenton.setTown(parent.getTown());
        }
        if(!parent.getArea().replaceAll(" ","").equals(""))
        {
            parenton.setArea(parent.getArea());
        }
        if(!parent.getTelephone().replaceAll(" ","").equals(""))
        {
            parenton.setTelephone(parent.getTelephone());
        }
        if(!user.getPassword().replaceAll(" ","").equals(""))
        {
            useron.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        System.out.println("new telephone "+parent.getTelephone());
        userRepository.save(useron);
        parentRepository.save(parenton);
    }



    @Override
    public void updateUserProvider(ProviderEntity provideron,ProviderEntity provider,UserEntity useron,UserEntity user){
        if(!provider.getEmail().replaceAll(" ","").equals("")){
            provideron.setEmail(provider.getEmail());
        }

        if(!provider.getName().replaceAll(" ","").equals(""))
        {
            provideron.setName(provider.getName());
        }
        if(!provider.getSurname().replaceAll(" ","").equals(""))
        {
            provideron.setSurname(provider.getSurname());
        }
        if(!provider.getArea().replaceAll(" ","").equals(""))
        {
            provideron.setArea(provider.getArea());
        }
        if(!provider.getTown().replaceAll(" ","").equals(""))
        {
            provideron.setTown(provider.getTown());
        }
        if(!provider.getArea().replaceAll(" ","").equals(""))
        {
            provideron.setArea(provider.getArea());
        }
        if(!provider.getTelephone().replaceAll(" ","").equals(""))
        {
            provideron.setTelephone(provider.getTelephone());
        }
        if(!provider.getTr().replaceAll(" ","").equals(""))
        {
            provideron.setTr(provider.getTr());
        }
        if(!user.getPassword().replaceAll(" ","").equals(""))
        {
            useron.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        userRepository.save(useron);
        providerRepository.save(provideron);
    }
}
