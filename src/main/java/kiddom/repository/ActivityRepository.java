package kiddom.repository;

import kiddom.model.SingleEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Arianna on 3/6/2017.
 */
@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<SingleEventEntity, Long> {
}


