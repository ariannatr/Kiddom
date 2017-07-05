package kiddom.controller;

import kiddom.authentication.IAuthenticationFacade;
import kiddom.model.*;
import kiddom.service.*;
import org.apache.catalina.User;
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
import java.util.ArrayList;
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

    @Qualifier("parentReportsService")
    @Autowired
    private ParentReportsService reportsService;

    @GetMapping("/admin")
   // @RequestMapping(value="/admin", method = RequestMethod.GET)
    public ModelAndView showPersonsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }

//        // Evaluate page size. If requested parameter is null, return initial page size
//        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
//        // Evaluate page. If requested parameter is null or less than 0 (to prevent exception), return initial size. Otherwise, return value of param. decreased by 1.
//        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
//
//        Page<ProviderEntity> persons = adminService.findAllPageable(new PageRequest(evalPage, evalPageSize));
//        if(persons.getTotalPages()!=0) {
//            Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
//            modelAndView.addObject("items", persons);
//            modelAndView.addObject("selectedPageSize", evalPageSize);
//            modelAndView.addObject("pageSizes", PAGE_SIZES);
//            modelAndView.addObject("pager", pager);
//        }

        /*----------------------Parent view------------------------------*/

         /*Get list of all parents*/
        List<ParentEntity> parentsList = userService.getParents();

        /*Create list of parent reports lists, one for each parent*/
        ArrayList<List<ParentReportsEntity>> parentReportsList = new ArrayList<>(parentsList.size());
        for (ParentEntity parent : parentsList){
            parentReportsList.add(reportsService.getReportsByUser(parent));
        }

        modelAndView.addObject("parentsList", parentsList);
        modelAndView.addObject("parentReportsList", parentReportsList);

        /*----------------------Provider view------------------------------*/

        List<ProviderEntity> providersList = userService.getProviders();

        modelAndView.addObject("providersList", providersList);

        /*----------------------------------------------------------------*/
        modelAndView.addObject("url", "admin");
        modelAndView.setViewName("/admin");
        return modelAndView;

//        PagingAndSortingRepository findAll(Pageable page) https://stackoverflow.com/questions/14066039/what-caused-the-no-property-find-found-for-type-spring-data-jpa-error
    }


    @RequestMapping(value="/accept/{provID}", method = RequestMethod.POST)
    public ModelAndView approve(/*@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page,*/@PathVariable String provID) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        modelAndView.addObject("uname", authentication.getName());
        UserEntity userS = userService.findByUsername(authentication.getName());
        modelAndView.addObject("type", String.valueOf(userS.getType()));
        ProviderEntity provider = userService.findProvider(new ProviderPK(provID));
        userService.approveProvider(provider);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }


    @PostMapping("/search")
    public ModelAndView showevents(@ModelAttribute("user") @Valid UserEntity user,@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page,@RequestParam(value="date",required = false) String date,@RequestParam(value="town",required =false) String town,@RequestParam(value="places",required = false) Integer places) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.addObject("categories",categoryService.getCategoriesNames());
        modelAndView.addObject("subcategories",categoryService.getALLSubCategoryNamesByCategory());
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        System.out.println("pira poli "+town+" atoma "+places+" imerominia "+date);
        Page<SingleEventEntity> events=null;
        Pager pager=null;
        if(date.replaceAll(" ","").equals("") && town.replaceAll(" ","").equals("") && places==null) {
             events= eventService.findAllPageable(new PageRequest(evalPage, evalPageSize));
             pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if(!date.replaceAll(" ","").equals("") && !town.replaceAll(" ","").replaceAll(" ","").equals("") && places==null){
            events= eventService.findByTownOrAreaAndDate(town,town,date,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if(!date.replaceAll(" ","").equals("") && !town.replaceAll(" ","").equals("") && places!=null){
            events= eventService.findByTownOrAreaAndDateAndAvailability(town,town,date,places,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if(date.replaceAll(" ","").equals("") && !town.replaceAll(" ","").equals("") && places!=null){
            events= eventService.findByTownOrAreaAndAvailability(town,town,places,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if(!date.replaceAll(" ","").equals("") && town.replaceAll(" ","").equals("") && places!=null){
            events= eventService.findByDateAndAvailability(date,places,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if( date.replaceAll(" ","").equals("") &&  town.replaceAll(" ","").equals("") && places!=null)
        {
            events= eventService.findByAvailability(places,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if(!date.replaceAll(" ","").equals("") && town.replaceAll(" ","").equals("") && places==null)
        {
            System.out.println("psaxnw mono g tn imerominia ");
            events= eventService.findByDate(date,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if(date.replaceAll(" ","").equals("") && !town.replaceAll(" ","").equals("")  && places==null)
        {
            events= eventService.findByTownOrArea(town,town,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }

        //Page<SingleEventEntity> page_events = new PageImpl<>(events,new PageRequest(evalPage, evalPageSize),events.size());

        System.out.println("vrika "+events.getTotalElements());
        modelAndView.addObject("url", "search");
        modelAndView.addObject("items", events);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/search");
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView showevents2(@ModelAttribute("user") @Valid UserEntity user,@RequestParam("pageSize") Optional<Integer> pageSize,
                                   @RequestParam("page") Optional<Integer> page,@RequestParam(value="date",required = false) String date,@RequestParam(value="town",required =false) String town,@RequestParam(value="places",required = false) Integer places) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        modelAndView.addObject("categories",categoryService.getCategoriesNames());
        modelAndView.addObject("subcategories",categoryService.getALLSubCategoryNamesByCategory());
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        System.out.println("pira poli "+town+" atoma "+places+" imerominia "+date);
        Page<SingleEventEntity> events=null;
        Pager pager=null;
        if(date.replaceAll(" ","").equals("") && town.replaceAll(" ","").equals("") && places==null) {
            events= eventService.findAllPageable(new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if(!date.replaceAll(" ","").equals("") && !town.replaceAll(" ","").replaceAll(" ","").equals("") && places==null){
            events= eventService.findByTownOrAreaAndDate(town,town,date,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if(!date.replaceAll(" ","").equals("") && !town.replaceAll(" ","").equals("") && places!=null){
            events= eventService.findByTownOrAreaAndDateAndAvailability(town,town,date,places,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if(date.replaceAll(" ","").equals("") && !town.replaceAll(" ","").equals("") && places!=null){
            events= eventService.findByTownOrAreaAndAvailability(town,town,places,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if(!date.replaceAll(" ","").equals("") && town.replaceAll(" ","").equals("") && places!=null){
            events= eventService.findByDateAndAvailability(date,places,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if( date.replaceAll(" ","").equals("") &&  town.replaceAll(" ","").equals("") && places!=null)
        {
            events= eventService.findByAvailability(places,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if(!date.replaceAll(" ","").equals("") && town.replaceAll(" ","").equals("") && places==null)
        {
            events= eventService.findByDate(date,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        else if(date.replaceAll(" ","").equals("") && !town.replaceAll(" ","").equals("")  && places==null)
        {
            events= eventService.findByTownOrArea(town,town,new PageRequest(evalPage, evalPageSize));
            pager= new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);
        }
        System.out.println("vrika "+events.getTotalElements());

        for(SingleEventEntity singleEventEntity :events){

        }
        //Page<SingleEventEntity> page_events = new PageImpl<>(events,new PageRequest(evalPage, evalPageSize),events.size());
        modelAndView.addObject("url", "search");
        modelAndView.addObject("items", events);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/search");
        return modelAndView;
    }
}


/////////////THIS BEFORE THE IF ELSES/////////

//    List<SingleEventEntity> events2 = eventService.findALLEvents(/*new PageRequest(evalPage, evalPageSize)*/); THIS LINE NEEDS TO BE IMPLEMENTED
//        String[] backupphotos= new String[events2.size()];
//        int counter = 0;
//        for (SingleEventEntity event : events2) {
//            String photo = "@{images/demo/event1.jpg}";
//            if (event.getPhotos() != null) {
//                String[] photos = event.getPhotos().split(";");
//                if (photos[0] != null) {
//
//                    photo = photos[0];
//                }
//                backupphotos[counter] = event.getPhotos();
//                event.setPhotos(photo);
//                eventService.updateSingleEvent(null,event,event);
//                ++counter;
//            }
//        }
////////////////THIS AFTER THE IF ELSES////////
//        counter = 0;
//        for (SingleEventEntity event : events2 ) {
//            event.setPhotos(backupphotos[counter]);
//            eventService.updateSingleEvent(null,event,event);
//            ++counter;
//        }



//////////THIS IS JUST A LIST OF THE PHOTOS IN THE CORRECT ORDER.USE IT IF YOU FIND THE THYMELEAF WAY


// List<String> Photos= new ArrayList<>();
//
//        for(SingleEventEntity event : events){
//            String photo = "@{images/demo/event1.jpg}";
//            if (event.getPhotos() != null) {
//                String[] photos = event.getPhotos().split(";");
//                if (photos[0] != null) {
////                    System.out.println("exw pragmata" + photos[0]);
//                    photo = photos[0];
//                }
//            }
//
//            Photos.add(photo);
//
//        }

/////////// THIS IS JUST A HELPER CLASS.WONT BE NEEDED PROBABLY


//public class searchevent {
//        SingleEventEntity event;
//        String photo;
//
//        searchevent(SingleEventEntity event,String photo) {this.event = event;this.photo = photo;}
//
//        public SingleEventEntity getEvent() {return event;}
//        public String getPhoto() {return photo;}
//    }