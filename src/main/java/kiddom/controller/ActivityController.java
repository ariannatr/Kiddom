package kiddom.controller;

import kiddom.authentication.IAuthenticationFacade;
import kiddom.model.*;
import kiddom.service.CategoryService;
import kiddom.service.EventService;
import kiddom.service.ProgramService;
import kiddom.service.UserService;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Arianna on 6/6/2017.
 */
@Controller
public class ActivityController {

    public class ShowComment
    {
        private String username;
        private String comment;
        private Float rate;
        public String getUsername() {return username;}
        public String getComment() {return comment;}
        public Float getRate() {return rate;}
        public ShowComment(String username,String comment,Float rate) {this.username = username;
        this.rate=rate;this.comment=comment;}
    }

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ProgramService programService;

    @Qualifier("categoryService")
    @Autowired
    private CategoryService categoryService;

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
        Set<CommentsEntity> comment =event.getComment_parent();
        modelAndView.addObject("event", event);
        Set<ShowComment> comments=new HashSet<>();
        for (CommentsEntity com:comment){
            ShowComment showComment=new ShowComment(com.getParent_username().getParentUsername(),com.getComment(),com.getRating());
            comments.add(showComment);
        }
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("program", program);
        modelAndView.setViewName("/activity");
        return modelAndView;
    }

    @RequestMapping(value="/comment/{eventID}", method = RequestMethod.POST)
    public ModelAndView comment(@ModelAttribute("user") @Valid UserEntity user, @PathVariable String eventID,@RequestParam("comment") String Comment){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        modelAndView.addObject("uname", authentication.getName());
        UserEntity userS = userService.findByUsername(authentication.getName());
        modelAndView.addObject("type", String.valueOf(userS.getType()));
        System.out.println("Eimai o gonios "+ userS.getUsername()+"thelw n prosthesw ena sxolio ,to"+Comment);
        Integer eventID_ = Integer.parseInt(eventID);
        SingleEventEntity event = eventService.findSingleEventById(eventID_);
        ParentEntity parenton = userService.findParent(new ParentPK(authentication.getName()));
        CommentsEntity commentsEntity=new CommentsEntity();
        commentsEntity.setComment(Comment);
        eventService.addComment(parenton,commentsEntity,event);
        System.out.println("to prosthesa");
        modelAndView.setViewName("redirect:/activity/"+eventID);
        return modelAndView;
    }

    @RequestMapping(value="/reserve/{programID}", method = RequestMethod.POST)
    public ModelAndView reservation(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, @PathVariable("programID") String programID, @RequestParam("num") int places){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("bika st reservation");
        Authentication authentication = authenticationFacade.getAuthentication();
        modelAndView.addObject("uname", authentication.getName());
        UserEntity userS = userService.findByUsername(authentication.getName());
        modelAndView.addObject("type", String.valueOf(userS.getType()));
        ParentEntity parenton = userService.findParent(new ParentPK(authentication.getName()));
        ProgramEntity programEntity=programService.getProgramById(Integer.parseInt(programID)); //slot of event
        ProviderEntity providerEntity=  programEntity.getEvent().getProviders(); //provider of event
        programService.makeReservation(parenton,places,programEntity,providerEntity);
        modelAndView.setViewName("redirect:/activity/"+programEntity.getEvent().getId());
        System.out.println("vgainw apo to st reservation");
        return modelAndView;
    }


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
        modelAndView.addObject("is_approved", provider.getApproved());
        modelAndView.addObject("categories",categoryService.getCategoriesNames());
        modelAndView.addObject("subcategories",categoryService.getALLSubCategoryNamesByCategory());
        modelAndView.setViewName("/activity_reg");
        return modelAndView;
    }

    @RequestMapping(value="/activity_reg", method = RequestMethod.POST)
    public ModelAndView activity_creation(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("single_event") @Valid SingleEventEntity event, @ModelAttribute("program") @Valid ProgramEntity daily_program, HashSet<ProgramEntity> program, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name (provider) is " + authentication.getName());
        event.setCategory("Temp Category");
        if (!authentication.getName().equals("anonymousUser")) {
            if (event.getNumOfSlots() == 0) {
                System.out.println("No slots");
                event.setNumOfSlots(1);
                program.add(daily_program);
            }
            else if (event.getNumOfSlots() > 1) {
                String startTime = daily_program.getStartTime();
                int numOfSlots = event.getNumOfSlots();
                int slotDuration = event.getSlotDuration();
                int price = daily_program.getPrice();
                int availability = daily_program.getAvailability();
                int capacity = daily_program.getCapacity();

                String timeValue = startTime;
                System.out.println("Time value is " + timeValue);
                DateTimeFormatter parseFormat = new DateTimeFormatterBuilder().appendPattern("HH:mm").toFormatter();
                LocalTime startTimeL = LocalTime.parse(timeValue, parseFormat);
                System.out.println(startTimeL);

                int hours = slotDuration/60;
                int minutes = slotDuration%60;

                LocalTime lastEndTime = startTimeL;

                for (int i = 0; i < numOfSlots; ++i) {
                    ProgramEntity newProgram = new ProgramEntity();
                    newProgram.setCanceled(0);
                    newProgram.setAvailability(availability);
                    newProgram.setCapacity(capacity);
                    newProgram.setPrice(price);
                    newProgram.setDate(daily_program.getDate());
                    System.out.println("Last end time is " + lastEndTime);
                    String finalStartTime = lastEndTime.toString();
                    String finalST = finalStartTime.substring(0, 5);
                    newProgram.setStartTime(finalST);
                    LocalTime endTime = lastEndTime.plusMinutes(minutes);
                    endTime = endTime.plusHours(hours);
                    String finalEndTime = endTime.toString();
                    String finalET = finalEndTime.substring(0, 5);
                    newProgram.setEndTime(finalET);
                    program.add(newProgram);
                    lastEndTime = endTime;
                }
                for (ProgramEntity p : program) {
                    System.out.println("Program with start " + p.getStartTime() + " end " + p.getEndTime());
                }
            }
            else {
                event.setNumOfSlots(1);
                program.add(daily_program);
            }

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
