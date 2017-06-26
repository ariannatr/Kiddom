package kiddom.controller;

import kiddom.authentication.IAuthenticationFacade;
import kiddom.model.ProviderEntity;
import kiddom.model.ProviderPK;
import kiddom.model.SingleEventEntity;
import kiddom.model.UserEntity;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Arianna on 6/6/2017.
 */
@Controller
public class ActivityController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/activity_reg", method = RequestMethod.GET)
    public ModelAndView activity_register(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        UserEntity userExists = userService.findByUsername(user.getUsername());
        SingleEventEntity event = new SingleEventEntity();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is" + authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.setViewName("redirect:/profileProvider");
        return modelAndView;
    }

    @RequestMapping(value="/activity_reg", method = RequestMethod.POST)
    public ModelAndView activity_creation(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("single_event") @Valid SingleEventEntity event, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name (provider) is " + authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            ProviderEntity provideron = userService.findProvider(new ProviderPK(authentication.getName()));
            UserEntity useron = userService.findByUsername(authentication.getName());
            ProviderPK provideronPK = new ProviderPK(authentication.getName());
            provideron = userService.findProvider(provideronPK);
            modelAndView.addObject("user", provideronPK.getUser());
            modelAndView.addObject("provider", provideron);
            if (useron.getType() == 2) {
                System.out.println("I'm a provider");
                userService.saveActivity(useron, provideron, event);
            }
            else {
                modelAndView.setViewName("redirect:/error_page?error_code=not_prov");
            }
        }
        else {
            modelAndView.setViewName("redirect:/error_page?error_code=anon");
        }
        return modelAndView;
    }
}
