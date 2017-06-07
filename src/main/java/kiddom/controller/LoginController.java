package kiddom.controller;


import kiddom.model.*;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.security.pkcs11.wrapper.Constants;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value={"/login"}, method = RequestMethod.POST)
	public ModelAndView login(HttpSession session, @ModelAttribute("user") @Valid UserEntity user, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists = userService.findByUsernamePassword(user.getUsername(),user.getPassword());

		if (userExists != null) {  //found a user with username and password
			System.out.println("Found the user "+userExists.getUsername());
			//redirectAttrs.addFlashAttribute("user",userExists);
            modelAndView.addObject("user",userExists);
            modelAndView.addObject("name",userExists.getUsername());
			//return "redirect:/profile";
			/*session.addAttribute(Constants.FOO, new Foo();
			//...
			Foo foo = (Foo) session.getAttribute(Constants.Foo);*/
            modelAndView.setViewName("redirect:/profile");
		}
		else
        {//didn't find a user with username and password//need to check is user exists to print a "wrong password message"
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
}
