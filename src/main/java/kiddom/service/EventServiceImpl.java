package kiddom.service;

import kiddom.model.*;
import kiddom.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OneToOne;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Arianna on 1/7/2017.
 */
@Service("eventService")
public class EventServiceImpl implements EventService {

    @Qualifier("programRepository")
    @Autowired
    private ProgramRepository programRepository;

    @Qualifier("commentRepository")
    @Autowired
    private CommentRepository commentRepository;

    @Qualifier("activityRepository")
    @Autowired
    private ActivityRepository activityRepository;

    @Qualifier("providerRepository")
    @Autowired
    private ProviderRepository providerRepository;

    @Qualifier("parentRepository")
    @Autowired
    private ParentRepository parentRepository;

    @Override
    public SingleEventEntity findSingleEventById(int eventID) {
        return  activityRepository.findSingleEventById(eventID);
    }

    @Override
    public SingleEventEntity findSingleEvent(SingleEventEntity singleEventEntity) {
        return activityRepository.findSingleEventById(1/*singleEventEntity.getId()*/);
    }

    @Override
    public void addComment(ParentEntity parentEntity, CommentsEntity commentsEntity, SingleEventEntity singleEventEntity)
    {
         /*Save the comment*/
        commentsEntity.setParent_username(parentEntity);
        commentsEntity.setEvent_id(singleEventEntity);
        commentRepository.save(commentsEntity);

        /*
        /*Update Comment of Parents at event
       Set<ParentEntity> parents=singleEventEntity.getComment_parent();
       parents.add(parentEntity);
       singleEventEntity.setComment_parent(parents);
       activityRepository.save(singleEventEntity);

       /*Update Comment  of events to Parents
       Set<SingleEventEntity> events =parentEntity.getComment_event();
       events.add(singleEventEntity);
       parentEntity.setComment_event(events);
       parentRepository.save(parentEntity);*/
        System.out.println("Paw n kanw save t comment "+commentsEntity.getComment());
    }

    @Override
    public Set<ProgramEntity> findProgram(int eventID)
    {
        return findSingleEventById(eventID).getProgram();
    }

    @Override
    public void saveActivity(UserEntity user, ProviderEntity provider, SingleEventEntity event, HashSet<ProgramEntity> program,String[] photos)
    {
        /*
        String photos = null;
        if (request.getParameter("image1") != null) {
			photos += request.getParameter("image1");
			photos += "\n";
		}
        if (request.getParameter("image2") != null) {
			photos += request.getParameter("image2");
			photos += "\n";
		}
        if (request.getParameter("image3") != null) {
			photos += request.getParameter("image3");
			photos += "\n";
		}
        if (request.getParameter("image4") != null) {
			photos += request.getParameter("image4");
			photos += "\n";
		}
        if (request.getParameter("image5") != null) {
			photos += request.getParameter("image5");
			photos += "\n";
		}
		event.setPhotos(photos);*/
        event.setProviders(provider);
        System.out.println("Event id is " + event.getId());
        System.out.println("Event by " + provider.getPk().getUser().getUsername());
        activityRepository.save(event);
        for (ProgramEntity daily_program : program) {
            daily_program.setEvent(event);
            programRepository.save(daily_program);
        }
        String photo = "";
        for (String file : photos) {
            photo = photo + file +";";
        }
        event.setPhotos(photo);
        event.setProgram(program);
        activityRepository.save(event);
        System.out.println("Done.");
    }

    @Override
    public void updateSingleEvent(ProviderEntity provider, SingleEventEntity event, SingleEventEntity eventEdit) {
        if (!event.getName().replaceAll(" ", "").equals("")) {
            eventEdit.setName(event.getName());
        }
        if (!event.getDescription().replaceAll(" ", "").equals("")) {
            eventEdit.setDescription(event.getDescription());
        }
        activityRepository.save(eventEdit);
    }

    @Override
    public void cancelSingleEvent(ProviderEntity provider, SingleEventEntity eventEdit) {
        eventEdit.setCanceled(1);
        Set<ProgramEntity> program =eventEdit.getProgram(); //new HashSet<ProgramEntity>(eventEdit.getProgram());
        for (ProgramEntity p : program) {
            System.out.println("program event id " + p.getEvent().getId());
            p.setCanceled(1);
            programRepository.save(p);
        }
        //HashSet<ProgramEntity> newProgram = new HashSet<>();
       /* for (ProgramEntity p : program) {
            p.setEvent(eventEdit);
            newProgram.add(p);
        }*/
        //eventEdit.setProgram(newProgram);
        System.out.println("Event id is " + eventEdit.getId());
       // activityRepository.save(eventEdit);
    }

    /*@Override
    public void cancelSlot(int slotID, SingleEventEntity eventEdit) {
        Set<ProgramEntity> program = eventEdit.getProgram();
        for (ProgramEntity p : program) {
            if (p.getId() == slotID) {
                p.setCanceled(1);
                programRepository.save(p);
                break;
            }
        }
    }*/

    @Transactional
    @Override
    public Page<SingleEventEntity> findAllPageable(Pageable pageable)
    {
        return activityRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Page<SingleEventEntity> findByTownOrAreaAndDate(String Town,String Area,String Date,Pageable pageable)
    {
        return activityRepository.findByTownOrAreaAndDate(Town,Area,Date,pageable);
    }


    /*@Transactional
    @Override
    public Page<SingleEventEntity> freetext(String text,Pageable pageable)
    {
        return activityRepository.findAllByDateContainingOrAddressContainingOrAreaContainingOrCategoryContainingOrDescriptionContainingOrComment_parentContainingOrNameContainingOrProviderContaining(text,pageable);
    }*/


    @Transactional
    @Override
    public Page<SingleEventEntity> findByDate(String Date,Pageable pageable)
    {
        return activityRepository.findAllByDateContaining(Date,pageable);
    }
    @Transactional
    @Override
    public Page<SingleEventEntity> findByTownOrArea(String Town, String Area, Pageable pageable)
    {
        return activityRepository.findByTownOrArea(Town,Area,pageable);
    }
    @Transactional
    @Override
    public Page<SingleEventEntity> findByAvailability(Integer max,Pageable pageable){
        return activityRepository.findByAvailability(max,pageable);
    }

    @Transactional
    @Override
    public Page<SingleEventEntity> findByTownOrAreaAndDateAndAvailability(String Town,String Area,String Date,Integer max,Pageable pageable){
        return activityRepository.findByTownOrAreaAndDateAndAvailability(Town,Area,Date,max,pageable);
    }

    @Transactional
    @Override
    public Page<SingleEventEntity> findByTownOrAreaAndAvailability(String Town,String Area,Integer max,Pageable pageable){
        return activityRepository.findByTownOrAreaAndAvailability(Town,Area,max,pageable);
    }

    @Transactional
    @Override
    public Page<SingleEventEntity> findByDateAndAvailability(String Date,Integer max,Pageable pageable){
        return activityRepository.findByDateAndAvailability(Date,max,pageable);
    }

    @Transactional
    @Override
    public Page<SingleEventEntity> findByCategoryAndSub1OrSub2OrSub3(String cat,String subcat,String subcat2,String subcat3,Pageable pageable){
        return activityRepository.findByCategoryAndSub1OrSub2OrSub3(cat,subcat,subcat2,subcat3,pageable);
    }
    @Transactional
    @Override
    public Page<SingleEventEntity> findByCategory(String cat,Pageable pageable){
        return activityRepository.findByCategory(cat,pageable);
    }
    @Transactional
    @Override
    public Page<SingleEventEntity> findBySub1OrSub2OrSub3(String subcat,String subcat2,String subcat3,Pageable pageable){
        return activityRepository.findBySub1OrSub2OrSub3(subcat,subcat2,subcat3,pageable);
    }
}
