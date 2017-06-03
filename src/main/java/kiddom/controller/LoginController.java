package kiddom.controller;


import kiddom.model.ParentEntity;
import kiddom.model.ProviderEntity;
import kiddom.model.SingleEventEntity;
import kiddom.model.UserEntity;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;

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
	public ModelAndView index(@ModelAttribute("user") UserEntity user){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user",user);
		System.out.println("The one logged in is "+user.getUsername());
		modelAndView.setViewName("index");
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
		modelAndView.setViewName("/activity");
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


	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView createNewUser(@ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("parent") @Valid ParentEntity parent, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists = userService.findByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult
					.rejectValue("username", "error.user",
							"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("user",user);
			modelAndView.setViewName("error_page");
		} else {
			userService.saveUser(user,parent);
			//userRepository.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user",user);
            modelAndView.addObject("parent",parent);
			modelAndView.setViewName("index");
			
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
			modelAndView.setViewName("error_page");
		} else {
			userService.saveUserProvider(user,provider);
			//userRepository.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user",user);
			modelAndView.addObject("provider", provider);
			modelAndView.setViewName("index");

		}
		return modelAndView;
	}
}
