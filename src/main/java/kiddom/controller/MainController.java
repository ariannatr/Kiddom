package kiddom.controller;

import kiddom.authentication.IAuthenticationFacade;
import kiddom.model.ProviderEntity;
import kiddom.model.UserEntity;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Arianna on 6/6/2017.
 */
@Controller
public class MainController {
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/activity", method = RequestMethod.GET)
    public ModelAndView activity_show(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser"))
            modelAndView.addObject("uname",authentication.getName());
        modelAndView.setViewName("/activity");
        return modelAndView;
    }

    @RequestMapping(value="/error_page", method = RequestMethod.GET)
    public ModelAndView error(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser"))
            modelAndView.addObject("uname",authentication.getName());
        modelAndView.setViewName("error_page");
        return modelAndView;
    }

    @RequestMapping(value="/faq", method = RequestMethod.GET)
    public ModelAndView faq(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser"))
            modelAndView.addObject("uname",authentication.getName());
        modelAndView.setViewName("faq");
        return modelAndView;
    }

    @RequestMapping(value="/google_map", method = RequestMethod.GET)
    public ModelAndView google_map(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser"))
            modelAndView.addObject("uname",authentication.getName());
        modelAndView.setViewName("google_map");
        return modelAndView;
    }

    @RequestMapping(value="/about", method = RequestMethod.GET)
    public ModelAndView about(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser"))
            modelAndView.addObject("uname",authentication.getName());
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @RequestMapping(value="/freetext_form", method = RequestMethod.POST)
    public ModelAndView freetext_form(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser"))
            modelAndView.addObject("uname",authentication.getName());
        modelAndView.setViewName("error_page");
        return modelAndView;
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ModelAndView search(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser"))
            modelAndView.addObject("uname",authentication.getName());
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @RequestMapping(value="/buypoints", method = RequestMethod.GET)
    public ModelAndView buypoints(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser"))
            modelAndView.addObject("uname",authentication.getName());
        modelAndView.setViewName("buypoints");
        return modelAndView;
    }

    @RequestMapping(value="/categories_form", method = RequestMethod.GET)
    public ModelAndView categories(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser"))
            modelAndView.addObject("uname",authentication.getName());
        modelAndView.setViewName("categories_form");
        return modelAndView;
    }
}
