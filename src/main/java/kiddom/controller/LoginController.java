package kiddom.controller;


import kiddom.authentication.IAuthenticationFacade;
import kiddom.model.*;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.security.pkcs11.wrapper.Constants;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;


@Controller
public class LoginController {
    @Autowired
    private IAuthenticationFacade authenticationFacade;

	@Autowired
	private UserService userService;

	@RequestMapping(value={"/login"}, method = RequestMethod.POST)
	public ModelAndView login(HttpSession session, @ModelAttribute("user") @Valid UserEntity user, BindingResult bindingResult,Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists = userService.findByUsernamePassword(user.getUsername(),user.getPassword());

		if (userExists != null) {  //found a user with username and password
			System.out.println("Found the user "+userExists.getUsername());
			//redirectAttrs.addFlashAttribute("user",userExists);
			String uname=userExists.getUsername();
            //modelAndView.addObject("user",uname);
            //modelAndView.addObject("name",userExists.getUsername());

            modelAndView.setViewName("redirect:/index");
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
		return modelAndView;
	}


	@RequestMapping(value={"/", "/index"}, method = RequestMethod.GET, produces= "application/javascript")
	public ModelAndView index(@ModelAttribute("user") UserEntity user){
		ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser"))
		    modelAndView.addObject("uname",authentication.getName());
        return modelAndView;
	}


    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public ModelAndView profile(@ModelAttribute("user") @Valid UserEntity user,@ModelAttribute("parent") @Valid ParentEntity parent){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            System.out.println("username " + authentication.getName());
            ParentPK parentPk = new ParentPK(authentication.getName());
            System.out.println("username in PK is :"+parentPk.getUser().getUsername());
            ParentEntity useron=userService.findParent(parentPk);
            modelAndView.addObject("parent", parentPk.getUser());
            modelAndView.addObject("user",useron);
            System.out.println("Avail points are"+useron.getAvailPoints());
            modelAndView.addObject("total_points",useron.getTotalPoints());
            modelAndView.addObject("restr_points",useron.getRestrPoints());
            modelAndView.addObject("avail_points",useron.getAvailPoints());
        }
            modelAndView.setViewName("profile");
        return modelAndView;
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute("user") @Valid UserEntity user,@ModelAttribute("parent") @Valid ParentEntity parent){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            ParentEntity parenton=userService.findParent(new ParentPK(authentication.getName()));
            UserEntity useron=userService.findByUsername(authentication.getName());
            userService.updateUserParent(parenton,parent,useron,user);
            parenton=userService.findParent(new ParentPK(authentication.getName()));
            modelAndView.addObject("user",parenton);
            System.out.println("Avail points are"+parenton.getAvailPoints());
            modelAndView.addObject("total_points",parenton.getTotalPoints());
            modelAndView.addObject("restr_points",parenton.getRestrPoints());
            modelAndView.addObject("avail_points",parenton.getAvailPoints());
        }
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @RequestMapping(value="/profileProvider", method = RequestMethod.GET)
    public ModelAndView profileProvider(@ModelAttribute("provider") @Valid ProviderEntity provider,@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        //UserEntity user = new UserEntity();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser"))
            modelAndView.addObject("uname",authentication.getName());
        modelAndView.setViewName("profileProvider");
        return modelAndView;
    }
}
