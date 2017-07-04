package kiddom.service;

import kiddom.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Arianna on 1/7/2017.
 */
@Service("eventService")
public interface EventService {
   // public Set<CommentsEntity> findAllCommentsByEvent(Integer eventID_);
    public void addComment(ParentEntity parentEntity,CommentsEntity commentsEntity,SingleEventEntity singleEventEntity);
    public Set<ProgramEntity> findProgram(int eventID);
    public SingleEventEntity findSingleEvent(SingleEventEntity singleEventEntity);
    public SingleEventEntity findSingleEventById(int eventID);
    public List<SingleEventEntity> findALLEvents();
    //public Page<SingleEventEntity> getAllEvents(Pageable pageable);
    public void saveActivity(UserEntity user, ProviderEntity provider, SingleEventEntity event, HashSet<ProgramEntity> program,String[] photos);
    public void updateSingleEvent(ProviderEntity provider, SingleEventEntity event, SingleEventEntity eventEdit);
    public void cancelSingleEvent(ProviderEntity provider, SingleEventEntity eventEdit);
}
