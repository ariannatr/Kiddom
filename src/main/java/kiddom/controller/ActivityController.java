package kiddom.controller;

import kiddom.authentication.IAuthenticationFacade;
import kiddom.model.*;
import kiddom.service.EventService;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ActivityController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;


    @RequestMapping(value="/activity/{eventID}", method = RequestMethod.GET)
    public ModelAndView activityshow(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, @PathVariable String eventID){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        Integer eventID_ = Integer.parseInt(eventID);
        SingleEventEntity event = eventService.findSingleEventById(eventID_);
        Set<ProgramEntity> program= eventService.findProgram(eventID_);
        modelAndView.addObject("event", event);
        modelAndView.addObject("program", program);
        modelAndView.setViewName("/activity");
        return modelAndView;
    }

    /*@RequestMapping(value="/reserve/{programID}", method = RequestMethod.POST)
    public ModelAndView reservation(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, @PathVariable String programID){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        Integer eventID_ = Integer.parseInt(eventID);
        SingleEventEntity event = eventService.findSingleEventById(eventID_);
        Set<ProgramEntity> program= eventService.findProgram(eventID_);
        modelAndView.addObject("event", event);
        modelAndView.addObject("program", program);
        modelAndView.setViewName("redirect:/activity");
        return modelAndView;
    }*/


    @RequestMapping(value="/activity_reg", method = RequestMethod.GET)
    public ModelAndView activity_register(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        UserEntity userExists = userService.findByUsername(user.getUsername());
        SingleEventEntity event = new SingleEventEntity();
        ProgramEntity daily_program = new ProgramEntity();
        HashSet<ProgramEntity> program = new HashSet<>();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is" + authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
            redirectAttributes.addFlashAttribute("success","true");
        }
        else {
            modelAndView.setViewName("redirect:/error?error_code=anon");
            return modelAndView;
        }
        modelAndView.setViewName("activity_reg");
        return modelAndView;
    }

    @RequestMapping(value="/activity_reg", method = RequestMethod.POST)
    public ModelAndView activity_creation(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("single_event") @Valid SingleEventEntity event, @ModelAttribute("program") @Valid ProgramEntity daily_program, HashSet<ProgramEntity> program, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name (provider) is " + authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            System.out.println("To event exei id " + event.getId());
            System.out.println("To date einai: " + daily_program.getDate());
            program.add(daily_program);
            //TODO for many event times!
            modelAndView.addObject("uname", authentication.getName());
            ProviderEntity provideron = userService.findProvider(new ProviderPK(authentication.getName()));
            UserEntity useron = userService.findByUsername(authentication.getName());
            ProviderPK provideronPK = new ProviderPK(authentication.getName());
            provideron = userService.findProvider(provideronPK);
            modelAndView.addObject("user", provideronPK.getUser());
            modelAndView.addObject("provider", provideron);
            if (useron.getType() == 2) {
                System.out.println("I'm a provider");
                eventService.saveActivity(useron, provideron, event, program);
            }
            else {
                modelAndView.setViewName("redirect:/error?error_code=not_prov");
                return modelAndView;
            }
        }
        else {
            modelAndView.setViewName("redirect:/error?error_code=anon");
            return modelAndView;
        }
        redirectAttributes.addFlashAttribute("success","true");
        modelAndView.setViewName("redirect:/activity_reg");
        return modelAndView;
    }

}
