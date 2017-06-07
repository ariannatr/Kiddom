package kiddom.service;

import kiddom.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import kiddom.model.*;

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

    @Qualifier("categoryRepository")
    @Autowired
    private CategoryRepository categoryRepository;

    @Qualifier("subcategoryRepository")
    @Autowired
    private SubCategoryRepository subcategoryRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
		//return null;
	}

    @Override
    public CategoriesEntity findByName(String name) {
        return categoryRepository.findByName(name);
        //return null;
    }


	@Override
    public UserEntity findByUsernamePassword(String username,String password){
        UserEntity userExists = findByUsername(username);
        if(userExists != null)
        {
            if(bCryptPasswordEncoder.matches((password),userExists.getPassword()))//
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

		System.out.println("Creating user..."+user.getPassword());
		user.setType(1);
		/*if (request.getParameter("image") != null) {
			parent.setPhoto(request.getParameter("image"));
		}*/
        //Role userRole = roleRepository.findByRole("ADMIN");
        //user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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

    @Override
    public void saveActivity(UserEntity user,ProviderEntity provider,SingleEventEntity event)
    {
        event.setProviderByProviderId(provider);
        System.out.print("Trying to save event at :"+event.getAddress()+" Starts "+event.getStartTime()+" Ends "+event.getEndTime());
        activityRepository.save(event);
    }

    @Override
    public void saveCategory(CategoriesEntity category)
    {
        System.out.print("Trying to save category :"+category.getName());
        categoryRepository.save(category);
    }

    @Override
    public void saveSubCategory(CategoriesEntity category, List<SubcategoriesEntity> subcategory)
    {
        categoryRepository.save(category);
        System.out.print("Trying to save category :"+category.getName());
        for(SubcategoriesEntity sub :subcategory) {
           //
            //sub.setCategory_name(category.getName());
            sub.setCategoriesByCatId(category);
        }
        subcategoryRepository.save(subcategory);
        category.setSubcategoriesByCatId(subcategory);
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public  void delete(SubcategoriesEntity subcat){
        subcategoryRepository.delete(subcat);
    }
    @Override
    public void update(CategoriesEntity category)
    {
        categoryRepository.save(category);
    }

    @Override
    public List<CategoriesEntity> getCategories(){
        return categoryRepository.findAll();
    }
}
