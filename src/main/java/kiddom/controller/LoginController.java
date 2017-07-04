package kiddom.controller;


import kiddom.authentication.IAuthenticationFacade;
import kiddom.model.*;
import kiddom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.security.pkcs11.wrapper.Constants;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument;
import javax.validation.Valid;
import java.awt.print.Pageable;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.text.SimpleDateFormat;


@Controller
public class LoginController {

    public class Show{
        private String date;
        private String startTime;
        private String endTime;
        private String owner;
        private Integer price;
        private Integer id;
        private String category;
        Show(String date,String startTime,String endTime,String owner,Integer price ,Integer id,String category){
            this.date=date;this.startTime=startTime;this.endTime=endTime;this.owner=owner;
            this.price=price;   this.id=id; this.category=category;
        }
        public Integer getId() {return id;}
        public Integer getPrice(){return price;}
        public String getDate(){return date;}
        public String getEndTime() {return endTime;}
        public String getOwner() {return owner;}
        public String getStartTime() {return startTime;}
        public String getCategory() {return category;}
    }

    public class profevent {
        private String name;
        private Integer id;
        private String description;
        private String photo;

        profevent(String name, Integer id, String description, String photo) {
            this.name = name;
            this.id = id;
            this.description = description;
            this.photo = photo;
        }

        public Integer getId() {return id; }

        public String getName() {return name;}

        public String getDescription() {return description;}

        public String getPhoto() {return photo;}

    }
    @Autowired
    private IAuthenticationFacade authenticationFacade;

	@Autowired
	private UserService userService;
    public static final String uploadingdir = System.getProperty("user.dir") + "/uploadingdir/";

    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET, produces= "application/javascript")
    public ModelAndView index(@ModelAttribute("user") UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is "+authentication.getName());
        modelAndView.setViewName("/index");
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
        }
        return modelAndView;
    }

	@RequestMapping(value={"/login"}, method = RequestMethod.POST)
	public ModelAndView login(HttpSession session, @ModelAttribute("user") @Valid UserEntity user, BindingResult bindingResult, Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		UserEntity userExists = userService.findByUsernamePassword(user.getUsername(),user.getPassword());
      //  System.out.println("geiaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		if (userExists != null) {  //found a user with username and password
			System.out.println("Found the user " + userExists.getUsername());
			String uname = userExists.getUsername();
			System.out.println("My name is "+uname);
            modelAndView.setViewName("redirect:/index");
		}
		else
        {//didn't find a user with username and password//need to check is user exists to print a "wrong password message"
			userExists = userService.findByUsername(user.getUsername());
			if(userExists != null)  //wrong password given
			{
				System.out.println("wrong password");
				modelAndView.setViewName("redirect:/error");
			}
			else
            {
                modelAndView.setViewName("/error");
                System.out.println("im here");
            }

        }
		return modelAndView;
	}



    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public ModelAndView profile(@ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("parent") @Valid ParentEntity parent){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is" + authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            System.out.println("username " + authentication.getName());
            ParentPK parentPk = new ParentPK(authentication.getName());
            System.out.println("username in PK is :" + parentPk.getUser().getUsername());
            ParentEntity useron = userService.findParent(parentPk);
            modelAndView.addObject("parent", parentPk.getUser());
            modelAndView.addObject("user",useron);
            System.out.println("Avail points are " + useron.getAvailPoints());
            modelAndView.addObject("total_points",useron.getTotalPoints());
            modelAndView.addObject("restr_points",useron.getRestrPoints());
            modelAndView.addObject("avail_points",useron.getAvailPoints());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
            Set<ProgramEntity> events =useron.getEvents();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.now();
            String currDate1 = dtf.format(localDate).toString();
            if(events!=null)
            {
                List<Show> currEvents = new ArrayList<>();
                List<SingleEventEntity> pastEvents = new ArrayList<>();
                for (ProgramEntity event : events)
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        Date currDate = sdf.parse(currDate1);
                        Date eventDate = sdf.parse(event.getDate());
                        if (eventDate.after(currDate)) {
                            Show show = new Show(event.getDate(),event.getStartTime(),event.getEndTime(),
                                    event.getEvent().getName(),event.getEvent().getPrice(),event.getEvent().getId(),event.getEvent().getCategory());
                            currEvents.add(show);
                        }
                        else {
                            SingleEventEntity singleEventEntity=event.getEvent();
                            pastEvents.add(singleEventEntity);
                        }
                    }
                    catch (ParseException e) {
                    }
                }
                if(pastEvents.size()>0)
                    modelAndView.addObject("items" , pastEvents);
                if(currEvents.size()>0)
                     modelAndView.addObject("curr", currEvents);
            }
        }

        modelAndView.setViewName("/profile");
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView uploading(Model model) {
        File file = new File(uploadingdir);
        model.addAttribute("files", file.listFiles());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/buypoints");
        return modelAndView;
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("parent") @Valid ParentEntity parent,@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is " + authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            ParentEntity parenton = userService.findParent(new ParentPK(authentication.getName()));
            UserEntity useron = userService.findByUsername(authentication.getName());
            userService.updateUserParent(parenton,parent,useron,user);
            if (!uploadingFiles[0].isEmpty()) {
                for (MultipartFile uploadedFile : uploadingFiles) {

                    File file = new File(uploadingdir + uploadedFile.getOriginalFilename());
                    userService.uploadPhoto(parenton, parent, useron, user, "/image/" + uploadedFile.getOriginalFilename());
                    uploadedFile.transferTo(file);
                }
            }
            ParentPK parentonPK = new ParentPK(authentication.getName());
            parenton = userService.findParent(parentonPK);
            modelAndView.addObject("parent", parentonPK.getUser());
            modelAndView.addObject("user",parenton);
            //modelAndView.addObject("user",parenton);
            System.out.println("Avail points are " + parenton.getAvailPoints());
            modelAndView.addObject("total_points",parenton.getTotalPoints());
            modelAndView.addObject("restr_points",parenton.getRestrPoints());
            modelAndView.addObject("avail_points",parenton.getAvailPoints());
            modelAndView.addObject("type",String.valueOf(useron.getType()));

        }
        //redirectAttributes.addFlashAttribute("tab","elements");
        modelAndView.setViewName("redirect:/profile?tab=elements");
     //   modelAndView.setViewName("profile");
        return modelAndView;
    }

    @RequestMapping(value="/pointsBuy", method = RequestMethod.POST)
    public ModelAndView pointsBuy(@ModelAttribute("user") @Valid UserEntity user, @ModelAttribute("parent") @Valid ParentEntity parent,RedirectAttributes redirectAttributes){
        System.out.println("zitise "+parent.getTotalPoints()+" ");
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is " + authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            ParentEntity parenton = userService.findParent(new ParentPK(authentication.getName()));
            UserEntity useron = userService.findByUsername(authentication.getName());
            userService.updateUserPoints(parenton,parent,useron,user);
            System.out.println("Tha agorasw "+parent.getTotalPoints());
            ParentPK parentonPK = new ParentPK(authentication.getName());
            parenton = userService.findParent(parentonPK);
            modelAndView.addObject("parent", parentonPK.getUser());
            modelAndView.addObject("user",parenton);
            System.out.println("Avail points are " + parenton.getAvailPoints());
            modelAndView.addObject("total_points",parenton.getTotalPoints());
            modelAndView.addObject("restr_points",parenton.getRestrPoints());
            modelAndView.addObject("avail_points",parenton.getAvailPoints());
            modelAndView.addObject("type",String.valueOf(useron.getType()));

        }
//        modelAndView.setViewName("redirect:/buypoints");
        redirectAttributes.addFlashAttribute("success","true");
//        modelAndView.setViewName("buypoints");
        modelAndView.setViewName("redirect:/buypoints");
        return modelAndView;
    }

    @RequestMapping(value="/profileProvider", method = RequestMethod.GET)
    public ModelAndView profileProvider(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name (provider) is "+authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            System.out.println("username " + authentication.getName());
            ProviderPK providerPk = new ProviderPK(authentication.getName());
            System.out.println("username in PK is :"+providerPk.getUser().getUsername());
            ProviderEntity useron = userService.findProvider(providerPk);
            modelAndView.addObject("provider", providerPk.getUser());
            modelAndView.addObject("user",useron);
            System.out.println("Avail points are"+useron.getTotalPoints());
            modelAndView.addObject("total_points", useron.getTotalPoints());
            modelAndView.addObject("owed_points", useron.getOwedPoints());
            modelAndView.addObject("gotten_points", useron.getGottenPoints());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type", String.valueOf(userS.getType()));
            Set events = useron.getEvents();
            List<SingleEventEntity> providerEvents = new ArrayList<>(events);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.now();
            System.out.println(dtf.format(localDate));
            String currDate1 = dtf.format(localDate).toString();
            if (providerEvents != null) {
                System.out.println("With events");
                List<profevent> currEvents = new ArrayList<>();
                List<profevent> pastEvents = new ArrayList<>();
                //if (currEvents == null) {
                //    System.out.println("eimai adeio akomaaa");
                //}
                //else {
                //    System.out.println("exw pragmata");
                //}
                for (SingleEventEntity event : providerEvents) {
                    if (event.getProgram() != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        for (ProgramEntity program : event.getProgram()) {
                            try {
                                Date currDate = sdf.parse(currDate1);
                                Date eventDate = sdf.parse(program.getDate());
                                String photo = "@{images/demo/event1.jpg}";
                                if (event.getPhotos() != null) {
                                    String[] photos = event.getPhotos().split(";");
                                    if (photos[0] != null) {
                                        System.out.println("exw pragmata" + photos[0]);
                                        photo = photos[0];
                                    }
                                }
                                profevent ev = new profevent(event.getName(),event.getId(),event.getDescription(),photo);
                                if (eventDate.after(currDate)) {

                                    currEvents.add(ev);
                                }
                                else {
                                    pastEvents.add(ev);
                                }
                            }
                            catch (ParseException e) {
                            }
                        }
                    }
                    else {
                        modelAndView.addObject("eventsStatus", 0);
                    }
                    //break;
                }
                modelAndView.addObject("eventsStatus", 1);
                if(pastEvents.size()>0)
                    modelAndView.addObject("pastEvents", pastEvents);
                if(currEvents.size()>0)
                    modelAndView.addObject("currEvents", currEvents);
            }
            else {
                System.out.println("Without events");
            }
        }
        modelAndView.setViewName("/profileProvider");
        return modelAndView;
    }

    /*--POST Edit Provider's Profile --*/
    @RequestMapping(value="/edit_prov", method = RequestMethod.POST)
    public ModelAndView edit_prov(@ModelAttribute("provider") @Valid ProviderEntity provider, @ModelAttribute("user") @Valid UserEntity user)
    {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println("Authentication name is " + authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            modelAndView.addObject("uname", authentication.getName());
            ProviderEntity provideron = userService.findProvider(new ProviderPK(authentication.getName()));
            UserEntity useron = userService.findByUsername(authentication.getName());
            userService.updateUserProvider(provideron,provider,useron,user);
            ProviderPK provideronPK = new ProviderPK(authentication.getName());
            provideron = userService.findProvider(provideronPK);
            modelAndView.addObject("provider", provideronPK.getUser());
            modelAndView.addObject("user",provideron);
            modelAndView.addObject("total_points",provideron.getTotalPoints());
            modelAndView.addObject("owed_points",provideron.getOwedPoints());
            modelAndView.addObject("gotten_points",provideron.getGottenPoints());
            UserEntity userS = userService.findByUsername(authentication.getName());
            modelAndView.addObject("type",String.valueOf(userS.getType()));
        }

        modelAndView.setViewName("redirect:/profileProvider?tab=elements");
        return modelAndView;
    }
}
