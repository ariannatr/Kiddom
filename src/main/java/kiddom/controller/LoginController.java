package kiddom.controller;


import kiddom.model.ParentEntity;
import kiddom.model.ProviderEntity;
import kiddom.model.UserEntity;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value={"/login"}, method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute @Valid UserEntity user, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists = userService.findByUsernamePassword(user.getUsername(),user.getPassword());

		if (userExists != null) {  //found a user with username and password
            modelAndView.addObject("user",userExists);
            modelAndView.setViewName("index");
		}
		else
        {//didn't find a user with username and password
			//need to check is user exists to print a "wrong password message"
			userExists = userService.findByUsername(user.getUsername());
			if(userExists != null)  //wrong password given
			{
				System.out.println("wrong password");
				modelAndView.setViewName("error_page");
			}
			else
				modelAndView.setViewName("error_page");
        }
		/*if (bindingResult.hasErrors()) {
			modelAndView.setViewName("error_page");
		}*/
//		modelAndView.setViewName("register");
		return modelAndView;
	}


	@RequestMapping(value={"/", "/index"}, method = RequestMethod.GET, produces= "application/javascript")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
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

	@RequestMapping(value="/register_prov", method = RequestMethod.GET)
	public ModelAndView register_prov(){
		ModelAndView modelAndView = new ModelAndView();
		UserEntity user = new UserEntity();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register_prov");
		return modelAndView;
	}

    @RequestMapping(value="/error_page", method = RequestMethod.GET)
    public ModelAndView error(){
        ModelAndView modelAndView = new ModelAndView();
        //UserEntity user = new UserEntity();
        //modelAndView.addObject("user", user);
        modelAndView.setViewName("error_page");
        return modelAndView;
    }

    @RequestMapping(value="/about", method = RequestMethod.GET)
    public ModelAndView about(){
        ModelAndView modelAndView = new ModelAndView();
        UserEntity user = new UserEntity();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @RequestMapping(value="/activity", method = RequestMethod.GET)
    public ModelAndView activity(){
        ModelAndView modelAndView = new ModelAndView();
        UserEntity user = new UserEntity();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("activity");
        return modelAndView;
    }

    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public ModelAndView profile(){
        ModelAndView modelAndView = new ModelAndView();
        UserEntity user = new UserEntity();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @RequestMapping(value="/profileProvider", method = RequestMethod.GET)
    public ModelAndView profileProvider(){
        ModelAndView modelAndView = new ModelAndView();
        UserEntity user = new UserEntity();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("profileProvider");
        return modelAndView;
    }


	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView createNewUser(@ModelAttribute @Valid UserEntity user, @ModelAttribute @Valid ParentEntity parent, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists = userService.findByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult
					.rejectValue("username", "error.user",
							"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
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
	public ModelAndView createNewUserProvider(@ModelAttribute @Valid UserEntity user, @ModelAttribute @Valid ProviderEntity provider, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists = userService.findByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult
					.rejectValue("username", "error.user",
							"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("error_page");
		} else {
			userService.saveUserProvider(user,provider);
			//userRepository.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new UserEntity());
			modelAndView.addObject("provider", new ProviderEntity());
			modelAndView.setViewName("index");

		}
		return modelAndView;
	}
}
