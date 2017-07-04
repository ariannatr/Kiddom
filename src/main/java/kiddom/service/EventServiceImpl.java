package kiddom.service;

import kiddom.model.*;
import kiddom.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OneToOne;
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
    public List<SingleEventEntity> findALLEvents(){
        return activityRepository.findAll();
    }

   /* @Override
    public Set<CommentsEntity> findAllCommentsByEvent(Integer eventID_)
    {
        return commentRepository.findAllByEventId(eventID_);
    }*/

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
    public void saveActivity(UserEntity user, ProviderEntity provider, SingleEventEntity event, HashSet<ProgramEntity> program)
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
        event.setProgram(program);
        activityRepository.saveAndFlush(event);
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
        activityRepository.save(eventEdit);
    }
}
