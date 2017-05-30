package kiddom.model;

import javax.persistence.*;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "reservations", schema = "mydb", catalog = "")
@IdClass(ReservationsEntityPK.class)
public class ReservationsEntity {
    private int reservationId;
    private int parentId;
    private Integer slotId;
    private Integer eventId;
    private SlotEntity slotBySlotId;

    @Id
    @Column(name = "reservation_id")
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Id
    @Column(name = "parent_id")
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "slot_id")
    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    @Basic
    @Column(name = "event_id")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationsEntity that = (ReservationsEntity) o;

        if (reservationId != that.reservationId) return false;
        if (parentId != that.parentId) return false;
        if (slotId != null ? !slotId.equals(that.slotId) : that.slotId != null) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reservationId;
        result = 31 * result + parentId;
        result = 31 * result + (slotId != null ? slotId.hashCode() : 0);
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "slot_id", referencedColumnName = "slot_id")
    public SlotEntity getSlotBySlotId() {
        return slotBySlotId;
    }

    public void setSlotBySlotId(SlotEntity slotBySlotId) {
        this.slotBySlotId = slotBySlotId;
    }
}
