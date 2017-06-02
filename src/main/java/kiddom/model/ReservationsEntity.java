package kiddom.model;

/**
 * Created by eleni on 02-Jun-17.
 */

import javax.persistence.*;

@Entity
@Table(name = "reservations", schema = "mydb", catalog = "")
public class ReservationsEntity {
    private int reservationId;
    private String parent_username;
    private int eventId;

    @Id
    @Column(name = "reservation_id")
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Basic
    @Column(name = "parent_username")
    public String getParentUsername() {
        return parent_username;
    }

    public void setParentUsername(String parent_username) {
        this.parent_username = parent_username;
    }

    @Basic
    @Column(name = "event_id")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

}
