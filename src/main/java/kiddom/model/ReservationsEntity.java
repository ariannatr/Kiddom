package kiddom.model;

/**
 * Created by eleni on 02-Jun-17.
**/

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reservations", schema = "mydb")
public class ReservationsEntity implements Serializable {
    private int reservationId;
    private String parent_username;
    private int eventId;
    private ParentEntity parentByParentId;
    private SingleEventEntity singleEventBySingleEventId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Id
    @Column(name = "parent_username")
    public String getParentUsername() {
        return parent_username;
    }

    public void setParentUsername(String parent_username) {
        this.parent_username = parent_username;
    }

    @Id
    @Column(name = "event_id")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "event_id", referencedColumnName = "event_id")
    public SingleEventEntity getSingleEventBySingleEventId() {
        return singleEventBySingleEventId;
    }

    public void setSingleEventBySingleEventId(SingleEventEntity singleEventBySingleEventId) {
        this.singleEventBySingleEventId = singleEventBySingleEventId;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "parent_username", referencedColumnName = "username")
    public ParentEntity getParentByParentId() {
        return parentByParentId;
    }

    public void setParentByParentId(ParentEntity parentByParentId) {
        this.parentByParentId = parentByParentId;
    }

}
