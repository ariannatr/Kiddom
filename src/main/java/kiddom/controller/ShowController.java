package kiddom.controller;

import kiddom.authentication.IAuthenticationFacade;
import kiddom.model.Pager;
import kiddom.model.ProviderEntity;
import kiddom.model.SingleEventEntity;
import kiddom.model.UserEntity;
import kiddom.service.AdminService;
import kiddom.service.CategoryService;
import kiddom.service.EventService;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by Arianna on 2/7/2017.
 */
@Controller
public class ShowController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10, 20 };
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Qualifier("adminService")
    @Autowired
    private AdminService adminService;

    @Qualifier("categoryService")
    @Autowired
    private CategoryService categoryService;

    @Qualifier("userService")
    @Autowired
    private UserService userService;

    @Qualifier("eventService")
    @Autowired
    private EventService eventService;


    @GetMapping("/admin")
   // @RequestMapping(value="/admin", method = RequestMethod.GET)
    public ModelAndView showPersonsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<ProviderEntity> persons = adminService.findAllPageable(new PageRequest(evalPage, evalPageSize));
        Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("url", "admin");
        modelAndView.addObject("items", persons);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/admin");
        return modelAndView;
    }


    @PostMapping("/search")
    public ModelAndView showevents(@ModelAttribute("user") @Valid UserEntity user,@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.addObject("categories",categoryService.getCategoriesNames());
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        List<SingleEventEntity> events = eventService.findALLEvents(/*new PageRequest(evalPage, evalPageSize)*/);
        Page<SingleEventEntity> page_events = new PageImpl<>(events,new PageRequest(evalPage, evalPageSize),events.size());
        Pager pager = new Pager(page_events.getTotalPages(), page_events.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("url", "search");
        modelAndView.addObject("items", page_events);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/search");
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView showevents2(@ModelAttribute("user") @Valid UserEntity user,@RequestParam("pageSize") Optional<Integer> pageSize,
                                   @RequestParam("page") Optional<Integer> page) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.addObject("categories",categoryService.getCategoriesNames());
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        List<SingleEventEntity> events = eventService.findALLEvents(/*new PageRequest(evalPage, evalPageSize)*/);
        Page<SingleEventEntity> page_events = new PageImpl<>(events,new PageRequest(evalPage, evalPageSize),events.size());
        System.out.println("Exw elements "+page_events.getTotalElements()+" kai selides "+page_events.getTotalPages()
                +" kai number of elements"+page_events.getNumberOfElements());
        System.out.println("evalPage "+evalPage+" evalPageSize "+evalPageSize);
        Pager pager = new Pager(page_events.getTotalPages(), page_events.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("url", "search");
        modelAndView.addObject("items", page_events);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/search");
        return modelAndView;
    }
}
