package kiddom.service;

import kiddom.model.ProgramEntity;
import kiddom.model.ProviderEntity;
import kiddom.model.SingleEventEntity;
import kiddom.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Arianna on 1/7/2017.
 */
@Service("eventService")
public interface EventService {

    public SingleEventEntity findSingleEvent(SingleEventEntity singleEventEntity);
    public List<SingleEventEntity> findALLEvents();
   // public Page<SingleEventEntity> getAllEvents(Pageable pageable);
    public void saveActivity(UserEntity user, ProviderEntity provider, SingleEventEntity event, HashSet<ProgramEntity> program);
}
