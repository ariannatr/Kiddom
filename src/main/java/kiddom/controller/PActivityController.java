package kiddom.controller;

/**
 * Created by eleni on 04-Jul-17.
 */

import kiddom.authentication.IAuthenticationFacade;
import kiddom.model.*;
import kiddom.service.EventService;
import kiddom.service.ProgramService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class PActivityController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ProgramService programService;

    @RequestMapping(value="/activityProvider/{eventID}", method = RequestMethod.GET)
    public ModelAndView activity_show(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, @PathVariable("eventID") String eventID){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is"+authentication.getName());
        if(!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        System.out.println("Phra " + eventID);
        Integer eventID_ = Integer.parseInt(eventID);
        System.out.println("Diavasa " + eventID_);
        SingleEventEntity event = eventService.findSingleEventById(eventID_);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now();
        System.out.println(dtf.format(localDate));
        String currDate1 = dtf.format(localDate).toString();
        List<String> photos1 = new ArrayList<>();
        List<String> photos2 = new ArrayList<>();
        List<String> photos3 = new ArrayList<>();
        String[] photos = null;
        if (event.getPhotos() != null) {
            photos = event.getPhotos().split(";");
            int i = 0;
            for (String photo : photos) {
                if (i < 2) {
                    photos1.add(photos[i]);
                }
                else if (i < 5) {
                    photos2.add(photos[i]);
                }
                else {
                    photos3.add(photos[i]);
                }
                i +=1;
                System.out.println("exw pragmata" + photo);
            }


            modelAndView.addObject("photos", photos);
            modelAndView.addObject("photos1", photos1);
            modelAndView.addObject("photos2", photos2);
            modelAndView.addObject("photos3", photos3);
        }
        if (event.getProgram() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            for (ProgramEntity program : event.getProgram()) {
                try {
                    Date currDate = sdf.parse(currDate1);
                    Date eventDate = sdf.parse(program.getDate());
                    if (eventDate.after(currDate)) {
                    //    currEvent
                        modelAndView.addObject("eventStatus", 1);
                    }
                    else {
                    //    pastEvent
                        modelAndView.addObject("eventStatus", 2);
                    }
                }
                catch (ParseException e) {
                }
                break;
            }
        }
        else {
            modelAndView.addObject("eventStatus", 0);
        }
        if (event.getProgram() == null) {
            System.out.println("TA PIAME");
            modelAndView.addObject("hasProgram", 0);
        }
        else {
            System.out.println("DEN TA PIAME");
            modelAndView.addObject("hasProgram", 1);
            modelAndView.addObject("program", event.getProgram());
        }
        modelAndView.addObject("event", event);
        modelAndView.setViewName("activityProvider");
        return modelAndView;
    }

    @RequestMapping(value="/edit_event/{eventID}", method = RequestMethod.POST)
    public ModelAndView edit_event(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("single_event") @Valid SingleEventEntity event, @PathVariable("eventID") String eventID)
    {
        System.out.println("Provider name: " + provider.getName());
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is " + authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            Integer eventID_ = Integer.parseInt(eventID);
            SingleEventEntity eventEdit = eventService.findSingleEventById(eventID_);
            System.out.println("Kanw update sto " + eventID_);
            eventService.updateSingleEvent(provider, event, eventEdit);
            modelAndView.addObject("event", eventEdit);
            modelAndView.addObject("provider", provider);
            modelAndView.addObject("user", user);
        }
        modelAndView.setViewName("activityProvider");
        return modelAndView;
    }

    @RequestMapping(value="/event_cancelation/{eventID}", method = RequestMethod.POST)
    public  ModelAndView event_cancelation(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("single_event") @Valid SingleEventEntity event, @PathVariable("eventID") String eventID) {
        System.out.println("Provider name: " + provider.getName());
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is " + authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            Integer eventID_ = Integer.parseInt(eventID);
            SingleEventEntity eventEdit = eventService.findSingleEventById(eventID_);
            System.out.println("Kanw update sto " + eventID_);
            eventService.cancelSingleEvent(provider, eventEdit);
            modelAndView.addObject("event", eventEdit);
            modelAndView.addObject("provider", provider);
            modelAndView.addObject("user", user);
        }
        modelAndView.setViewName("activityProvider");
        return modelAndView;
    }

    @RequestMapping(value="/slot_cancelation/{slotID}", method = RequestMethod.POST)
    public ModelAndView slot_cancelation(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("single_event") @Valid SingleEventEntity event, @PathVariable("slotID") String slotID) {
        System.out.println("Provider name: " + provider.getName());
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is " + authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            System.out.println("Slot is " + slotID);
            Integer slotID_ = Integer.parseInt(slotID);
            ProgramEntity programEntity = programService.getProgramById(slotID_);
            SingleEventEntity eventEdit = eventService.findSingleEventById(programEntity.getEvent().getId());
            Set<ProgramEntity> program = eventEdit.getProgram();
            if (program != null) {
                eventService.cancelSlot(slotID_, eventEdit);
            }
            System.out.println("fw sto " + programEntity.getEvent().getId());
            modelAndView.setViewName("activityProvider");
        }
        return modelAndView;
    }
}
