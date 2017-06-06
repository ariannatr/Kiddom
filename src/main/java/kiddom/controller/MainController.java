package kiddom.controller;

import kiddom.model.ProviderEntity;
import kiddom.model.UserEntity;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

    @RequestMapping(value="/activity", method = RequestMethod.GET)
    public ModelAndView activity_show(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("provider",provider);
        modelAndView.setViewName("/activity");
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

    @RequestMapping(value="/freetext_form", method = RequestMethod.POST)
    public ModelAndView freetext_form(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("error_page");
        return modelAndView;
    }

    @RequestMapping(value="/search", method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("search");
        return modelAndView;
    }

}
