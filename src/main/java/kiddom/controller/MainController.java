package kiddom.controller;

import kiddom.authentication.IAuthenticationFacade;
import kiddom.model.*;
import kiddom.service.EventService;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Arianna on 6/6/2017.
 */

@Controller
public class MainController {
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Qualifier("userService")
    @Autowired
    private UserService userService;

    @Qualifier("eventService")
    @Autowired
    private EventService eventService;

    @RequestMapping(value="/activity", method = RequestMethod.GET)
    public ModelAndView activity_show(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("single_event") SingleEventEntity singleEvent, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        SingleEventEntity  single_event= eventService.findSingleEvent(singleEvent);
        System.out.println("Vrika t event "+single_event.getName());
        modelAndView.addObject("single_event", single_event);
        Set<ProgramEntity> programEntity= single_event.getProgram();
        if(programEntity.isEmpty()) {
            System.out.println("To programma einai keno");
            redirectAttributes.addFlashAttribute("define", "false");
        }
        else {
            System.out.println("To programma    den  einai keno");
            redirectAttributes.addFlashAttribute("define", "true");
        }
        modelAndView.setViewName("/activity");
        return modelAndView;
    }

    @RequestMapping(value="/error_page", method = RequestMethod.GET)
    public ModelAndView error(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.setViewName("error_page");
        return modelAndView;
    }

    @RequestMapping(value="/faq", method = RequestMethod.GET)
    public ModelAndView faq(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.setViewName("faq");
        return modelAndView;
    }

    @RequestMapping(value="/google_map", method = RequestMethod.GET)
    public ModelAndView google_map(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.setViewName("google_map");
        return modelAndView;
    }

    @RequestMapping(value="/about", method = RequestMethod.GET)
    public ModelAndView about(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @RequestMapping(value="/freetext_form", method = RequestMethod.POST)
    public ModelAndView freetext_form(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.setViewName("error_page");
        return modelAndView;
    }

    @RequestMapping(value="/search", method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @RequestMapping(value="/buypoints", method = RequestMethod.GET)
    public ModelAndView buypoints(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        UserEntity user = new UserEntity("");
        ParentEntity parent = new ParentEntity();
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.setViewName("buypoints");
        return modelAndView;
    }

    @RequestMapping(value="/categories_form", method = RequestMethod.GET)
    public ModelAndView categories(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.setViewName("categories_form");
        return modelAndView;
    }
}
