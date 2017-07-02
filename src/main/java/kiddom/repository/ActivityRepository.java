package kiddom.repository;

import kiddom.model.SingleEventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Arianna on 3/6/2017.
 */
@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<SingleEventEntity, Long> {
    SingleEventEntity findSingleEventById(Integer Id);
    //Page<SingleEventEntity> findAllEvents(Pageable pageable);

}
