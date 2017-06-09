package kiddom.model;

/**
 * Created by eleni on 10-Jun-17.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table (name = "program", schema = "mydb")
public class ProgramEntity implements Serializable{
    private int id;
    private int eventId;
    private String date;
    private String startTime;
    private String endTime;
    private int capacity;
    private int price;
    private int availability;
    private SingleEventEntity eventByEventId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    @Id
    @Column(name = "event_id")
    public int getEventId() {
        return  eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "availability")
    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgramEntity that = (ProgramEntity) o;

        if (eventId != that.eventId) return false;
        if (capacity != that.capacity) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (availability != that.availability) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (price != that.price) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result += 31*capacity;
        result += 31*availability;
        return result;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "event_id", referencedColumnName = "event_id")
    public SingleEventEntity getEventByEventId() {return eventByEventId;}

    public void setEventByEventId(SingleEventEntity eventByEventId) {
        this.eventByEventId = eventByEventId;
    }

}
