package kiddom.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "slot", schema = "mydb", catalog = "")
public class SlotEntity {
    private int slotId;
    private int day;
    private int month;
    private int year;
    private int startHour;
    private int startMinute;
    private int price;
    private int capacity;
    private int availability;
    private String category;
    private Collection<ReservationsEntity> reservationsBySlotId;
    private Collection<SpotHasSlotEntity> spotHasSlotsBySlotId;

    @Id
    @Column(name = "slot_id")
    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    @Basic
    @Column(name = "day")
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Basic
    @Column(name = "month")
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "start_hour")
    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    @Basic
    @Column(name = "start_minute")
    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "availability")
    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlotEntity that = (SlotEntity) o;

        if (slotId != that.slotId) return false;
        if (day != that.day) return false;
        if (month != that.month) return false;
        if (year != that.year) return false;
        if (startHour != that.startHour) return false;
        if (startMinute != that.startMinute) return false;
        if (price != that.price) return false;
        if (capacity != that.capacity) return false;
        if (availability != that.availability) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = slotId;
        result = 31 * result + day;
        result = 31 * result + month;
        result = 31 * result + year;
        result = 31 * result + startHour;
        result = 31 * result + startMinute;
        result = 31 * result + price;
        result = 31 * result + capacity;
        result = 31 * result + availability;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "slotBySlotId")
    public Collection<ReservationsEntity> getReservationsBySlotId() {
        return reservationsBySlotId;
    }

    public void setReservationsBySlotId(Collection<ReservationsEntity> reservationsBySlotId) {
        this.reservationsBySlotId = reservationsBySlotId;
    }

    @OneToMany(mappedBy = "slotBySlotId")
    public Collection<SpotHasSlotEntity> getSpotHasSlotsBySlotId() {
        return spotHasSlotsBySlotId;
    }

    public void setSpotHasSlotsBySlotId(Collection<SpotHasSlotEntity> spotHasSlotsBySlotId) {
        this.spotHasSlotsBySlotId = spotHasSlotsBySlotId;
    }
}
