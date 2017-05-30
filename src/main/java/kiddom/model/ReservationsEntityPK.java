package kiddom.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Arianna on 26/5/2017.
 */
public class ReservationsEntityPK implements Serializable {
    private int reservationId;
    private int parentId;

    @Column(name = "reservation_id")
    @Id
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Column(name = "parent_id")
    @Id
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationsEntityPK that = (ReservationsEntityPK) o;

        if (reservationId != that.reservationId) return false;
        if (parentId != that.parentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reservationId;
        result = 31 * result + parentId;
        return result;
    }
}
