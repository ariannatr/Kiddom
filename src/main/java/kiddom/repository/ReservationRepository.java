package kiddom.repository;

import kiddom.model.ReservationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Arianna on 4/7/2017.
 */

@Repository("reservationRepository")
public interface ReservationRepository  extends JpaRepository<ReservationsEntity,Long>{
}
