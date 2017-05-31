package kiddom.controller;


import kiddom.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.awt.*;
import kiddom.model.ParentEntity;
import kiddom.model.UserEntity;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userService;

	/*@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}*/


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
		modelAndView.addObject("user", user);
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
	public ModelAndView createNewUser(@ModelAttribute @Valid UserEntity user,@ModelAttribute @Valid ParentEntity parent, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists = userService.findByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult
					.rejectValue("username", "error.user",
							"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user,parent);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new UserEntity());
            modelAndView.addObject("parent", new ParentEntity());
			modelAndView.setViewName("index");
			
		}
		return modelAndView;
	}

}
