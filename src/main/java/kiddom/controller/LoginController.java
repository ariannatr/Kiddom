package kiddom.controller;


import kiddom.model.*;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value={"/login"}, method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") @Valid UserEntity user, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists = userService.findByUsernamePassword(user.getUsername(),user.getPassword());

		if (userExists != null) {  //found a user with username and password
			System.out.println("Found the user "+userExists.getUsername());
			//redirectAttrs.addFlashAttribute("user",userExists);
            modelAndView.addObject("user",userExists);
            modelAndView.addObject("name",userExists.getUsername());
			//return "redirect:/profile";
            modelAndView.setViewName("redirect:/profile");
		}
		else
        {//didn't find a user with username and password
			//need to check is user exists to print a "wrong password message"
			userExists = userService.findByUsername(user.getUsername());
			if(userExists != null)  //wrong password given
			{
				System.out.println("wrong password");
				modelAndView.setViewName("redirect:/error_page");
			}
			else
				modelAndView.setViewName("redirect:/error_page");
        }
		/*if (bindingResult.hasErrors()) {
			modelAndView.setViewName("error_page");
		}*/
//		modelAndView.setViewName("register");
		return modelAndView;
		//return "redirect:/index";
	}


	@RequestMapping(value={"/", "/index"}, method = RequestMethod.GET, produces= "application/javascript")
	public ModelAndView index(Principal principal,@ModelAttribute("user") UserEntity user){
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("index");
		if(principal != null){
			modelAndView.addObject("user",user);
			System.out.println("The one logged in is "+user.getUsername());
		}
		return modelAndView;
	}

	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView register(){
		ModelAndView modelAndView = new ModelAndView();
		UserEntity user = new UserEntity();
		ParentEntity parent=new ParentEntity();
		modelAndView.addObject("user", user);
		modelAndView.addObject("parent",parent);
		modelAndView.setViewName("register");
		return modelAndView;
	}

    @RequestMapping(value="/activity_reg", method = RequestMethod.GET)
    public ModelAndView activity_register(@ModelAttribute("provider") @Valid ProviderEntity provider,@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists=userService.findByUsername(user.getUsername());
		/*if(userExists == null){
			System.out.println("No one is logged in");
			modelAndView.setViewName("/error_page");
			return modelAndView;
		}
		else if(userExists.getType()==1) {
			System.out.println("The one registered is not a provider");
			modelAndView.setViewName("/provider_req");
			return modelAndView;
		}*/
        modelAndView.addObject("user", user);
        modelAndView.addObject("provider",provider);
        modelAndView.setViewName("/activity_reg");
        return modelAndView;
    }

	@RequestMapping(value="/activity", method = RequestMethod.GET)
	public ModelAndView activity_show(@ModelAttribute("provider") @Valid ProviderEntity provider,@ModelAttribute("user") @Valid UserEntity user){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.addObject("provider",provider);
		modelAndView.setViewName("/activity");
		return modelAndView;
	}

	@RequestMapping(value="/activity_reg", method = RequestMethod.POST)
	public ModelAndView activity_creation(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("single_event") @Valid SingleEventEntity event,BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists=userService.findByUsername(user.getUsername());
		/*if(userExists == null){
			modelAndView.addObject("user",user);
			modelAndView.setViewName("/error_page");
			return modelAndView;
		}
		else if(userExists.getType()==1){
			modelAndView.addObject("user",user);
			modelAndView.addObject("provider",provider);
			modelAndView.setViewName("/provider_req");
			return modelAndView;
		}*/
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("user",user);
			modelAndView.setViewName("/error_page");
			return modelAndView;
		} else {
			userService.saveActivity(user, provider, event);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", user);
			modelAndView.addObject("provider", provider);
			modelAndView.addObject("single_event", event);
		}
		modelAndView.setViewName("redirect:/activity");
		return modelAndView;
	}


	@RequestMapping(value="/register_prov", method = RequestMethod.GET)
	public ModelAndView register_prov(@ModelAttribute("user") @Valid UserEntity user,@ModelAttribute("provider") @Valid ProviderEntity provider){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.addObject("provider", provider);
		modelAndView.setViewName("register_prov");
		return modelAndView;
	}

    @RequestMapping(value="/error_page", method = RequestMethod.GET)
    public ModelAndView error(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        //UserEntity user = new UserEntity();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("error_page");
        return modelAndView;
    }

	@RequestMapping(value="/faq", method = RequestMethod.GET)
	public ModelAndView faq(@ModelAttribute("user") @Valid UserEntity user){
		ModelAndView modelAndView = new ModelAndView();
		//UserEntity user = new UserEntity();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("faq");
		return modelAndView;
	}

	@RequestMapping(value="/google_map", method = RequestMethod.GET)
	public ModelAndView google_map(@ModelAttribute("user") @Valid UserEntity user){
		ModelAndView modelAndView = new ModelAndView();
		//UserEntity user = new UserEntity();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("google_map");
		return modelAndView;
	}

    @RequestMapping(value="/about", method = RequestMethod.GET)
    public ModelAndView about(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public ModelAndView profile(@ModelAttribute("user") @Valid UserEntity user,@ModelAttribute("parent") @Valid ParentEntity parent){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("The one seeing his profil is "+user.getUsername());
        modelAndView.addObject("user", user);
		modelAndView.addObject("parent", parent);
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @RequestMapping(value="/profileProvider", method = RequestMethod.GET)
    public ModelAndView profileProvider(@ModelAttribute("provider") @Valid ProviderEntity provider,@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        //UserEntity user = new UserEntity();
        modelAndView.addObject("user", user);
        modelAndView.addObject("provider",provider);
        modelAndView.setViewName("profileProvider");
        return modelAndView;
    }

/*	@RequestMapping(value="/category_submit", method = RequestMethod.GET)
	public ModelAndView category_submit(@ModelAttribute("user") @Valid UserEntity user){
		ModelAndView modelAndView = new ModelAndView();
		//UserEntity user = new UserEntity();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("category_submit");
		return modelAndView;
	}*/

	/*@RequestMapping(value="/category_submit", method = RequestMethod.POST)
	public ModelAndView category_create(@ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("category") @Valid CategoriesEntity category, @ModelAttribute @Valid List<SubcategoriesEntity> subcategories, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		//UserEntity user = new UserEntity();
		modelAndView.addObject("user", user);
		CategoriesEntity cat=userService.findByName(category.getName());
		if(cat!=null){
            bindingResult
                    .rejectValue("category", "error.user",
                            "There is already a category registered with the name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("user",user);
            modelAndView.setViewName("redirect:/error_page");
        } else {
            userService.saveCategory(category);
            userService.saveSubCategory(category, subcategories);
            //userRepository.saveUser(user);
            modelAndView.addObject("successMessage", "Cta has been registered successfully");
            modelAndView.addObject("user", user);
            //modelAndView.addObject("parent",parent);
            modelAndView.setViewName("redirect:/index");
        }


         //   modelAndView.setViewName("category_creat");
		return modelAndView;
	}*/

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView createNewUser(@ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("parent") @Valid ParentEntity parent, BindingResult bindingResult,RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists = userService.findByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult
					.rejectValue("username", "error.user",
							"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("user",user);
			modelAndView.setViewName("redirect:/error_page");
		} else {
			userService.saveUser(user,parent);
			//userRepository.saveUser(user);
            redirectAttributes.addFlashAttribute("success",true);
            modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user",user);
            modelAndView.addObject("parent",parent);
			modelAndView.setViewName("redirect:/register");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/register_prov", method = RequestMethod.POST)
	public ModelAndView createNewUserProvider(@ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("provider") @Valid ProviderEntity provider, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists = userService.findByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult
					.rejectValue("username", "error.user",
							"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("user",user);
			modelAndView.setViewName("redirect:/error_page");
		} else {
			userService.saveUserProvider(user,provider);
			//userRepository.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user",user);
			modelAndView.addObject("provider", provider);
			modelAndView.setViewName("redirect:/index");

		}
		return modelAndView;
	}



/*******************************************May be usefull***************************************************************/

    private List<SubcategoriesEntity> manageSubCategories(CategoriesEntity category) {
        // Store the categories which shouldn't be persisted
        List<SubcategoriesEntity> categories2remove = new ArrayList<SubcategoriesEntity>();
        if (category.getSubcategoriesByCatId() != null) {
            for (Iterator<SubcategoriesEntity> i = category.getSubcategoriesByCatId().iterator(); i.hasNext();) {
                SubcategoriesEntity subcat = i.next();
                // If the remove flag is true, remove the employee from the list
                if (subcat.getRemove() == 1) {
                    categories2remove.add(subcat);
                    i.remove();
                    // Otherwise, perform the links
                } else {
                    subcat.setCategoriesByCatId(category);
                    //subcat.setCategory_name(category.getName());
                }
            }
        }
        return categories2remove;
    }

    // -- Creating a new category ----------

    @RequestMapping(value = "/category_submit", method = RequestMethod.GET)
    public String create(@ModelAttribute CategoriesEntity category, Model model) {
		model.addAttribute("categories",userService.getCategories());
        // Should init the AutoPopulatingList
        category.setSubcategoriesByCatId(new AutoPopulatingList<SubcategoriesEntity>(SubcategoriesEntity.class));
        //return create(category, model, true);
        return "category_submit";
    }

    private String create(CategoriesEntity category, Model model, boolean init) {
        if (init) {
            // Init the AutoPopulatingList

        }
        model.addAttribute("type", "create");
        return "redirect:/category_submit";
    }

    @RequestMapping(value = "category_submit", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("categories") CategoriesEntity category,@Valid @ModelAttribute("subcategories") SubcategoriesEntity sub, BindingResult bindingResult) {
        System.out.println("bika edw");
        // Call the private method
        manageSubCategories(category);
        System.out.println("Kalesa tin manage");
        List<SubcategoriesEntity> subcat= category.getSubcategoriesByCatId();
        if(subcat==null) {
            subcat = new ArrayList<SubcategoriesEntity>();
            System.out.println("The sublist is null,just created one");
        }
        subcat.add(sub);
       /* for(String sub :mysubcategories)
        {
            SubcategoriesEntity subcategory=new SubcategoriesEntity();
            subcategory.setName(sub);
            subcategory.setCategoriesByCatId(category);
            subcat.add(subcategory);
        }*/
        userService.saveSubCategory(category,subcat);
        System.out.println("Kalesa tin saveSub");
        //return "redirect:/category_submit" + category.getName();
        return "redirect:/category_submit";
    }

    // -- Updating an existing employer ----------

    @RequestMapping(value = "update/{pk}", method = RequestMethod.GET)
    public String update(@PathVariable Integer pk, @ModelAttribute CategoriesEntity category, Model model) {
        // Add your own getEmployerById(pk)
        model.addAttribute("type", "update");
        return "category_submit";
    }

    @RequestMapping(value = "update/{pk}", method = RequestMethod.POST)
    public String update(@PathVariable Integer pk, @Valid @ModelAttribute CategoriesEntity category, BindingResult bindingResult, Model model) {
        // Add your own getEmployerById(pk)
        if (bindingResult.hasErrors()) {
            return update(pk, category, model);
        }
        List<SubcategoriesEntity> subcategories2remove = manageSubCategories(category);
        // First, save the employer
        userService.update(category);
        // Then, delete the previously linked employees which should be now removed
        for (SubcategoriesEntity subcat : subcategories2remove) {
            if (subcat.getName() != null) {
                userService.delete(subcat);
            }
        }
        return "redirect:category_submit" + category.getName();
    }

    // -- Show an existing employer ----------

    @RequestMapping(value = "show/{pk}", method = RequestMethod.GET)
    public String show(@PathVariable Integer pk, @ModelAttribute CategoriesEntity category) {
        // Add your own getEmployerById(pk)
        return "category_submit";
    }
}
