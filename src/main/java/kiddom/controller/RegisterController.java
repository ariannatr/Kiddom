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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Arianna on 6/6/2017.
 */
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;


    /********************   PARENT REGISTER ********************************/
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView register(RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        UserEntity user = new UserEntity();
        ParentEntity parent=new ParentEntity();
        redirectAttributes.addFlashAttribute("success",false);
        modelAndView.addObject("user", user);
        modelAndView.addObject("parent",parent);
        modelAndView.setViewName("register");
        return modelAndView;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createNewUser(@ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("parent") @Valid ParentEntity parent, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        UserEntity userExists = userService.findByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("user",user);
            modelAndView.setViewName("redirect:/error_page");
        } else {
            userService.saveUser(user,parent);
            //redirectAttributes.addFlashAttribute("success",true);
            //modelAndView.addObject("successMessage", "User has been registered successfully");
            //modelAndView.addObject("user",user);
            //modelAndView.addObject("parent",parent);
            modelAndView.setViewName("redirect:/register?success=true");
        }
        return modelAndView;
    }


    /***************************** PROVIDER REGISTER ********************************************************/
    @RequestMapping(value="/register_prov", method = RequestMethod.GET)
    public ModelAndView register_prov(@ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("provider") @Valid ProviderEntity provider){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("provider", provider);
        modelAndView.setViewName("register_prov");
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
            modelAndView.setViewName("redirect:/error_page");
        } else {
            userService.saveUserProvider(user,provider);
            //userRepository.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user",user);
            modelAndView.addObject("provider", provider);
            //modelAndView.setViewName("redirect:/index");
            modelAndView.setViewName("redirect:/register_prov?success=true");
        }
        return modelAndView;
    }

}
