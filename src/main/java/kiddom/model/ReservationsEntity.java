package kiddom.model;

/**
 * Created by eleni on 02-Jun-17.
**/

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reservations", schema = "mydb")
public class ReservationsEntity implements Serializable {

    /*----------------------------Fields----------------------------*/
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "reservation_id", nullable = false)
    private Integer reservation_id;

    /*--------------Getters - Setters for table fields--------------*/
    public int getReservationId() {
        return reservation_id;
    }
    public void setReservationId(int reservationId) {
        this.reservation_id = reservationId;
    }
}
