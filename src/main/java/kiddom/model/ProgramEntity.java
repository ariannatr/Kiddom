package kiddom.model;

/**
 * Created by eleni on 10-Jun-17.
 */

import org.springframework.security.access.method.P;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table (name = "program", schema = "mydb")
public class ProgramEntity implements Serializable{

    /*----------------------------Fields----------------------------*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "date")
    private String date;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "price")
    private int price;
    @Column(name = "availability")
    private int availability;


    /*--------------Relations with other tables--------------*/

    /*--------------Many to One relation from event to get event_id--------------*/
    @ManyToOne
    @JoinColumn(name="event_id")
    private SingleEventEntity event;

    public SingleEventEntity getEvent() {
        return event;
    }
    public void setEvent(SingleEventEntity event) {
        this.event = event;
    }


    /*--------------Getters - Setters for table fields--------------*/

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

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
        int result = capacity;
        result += 31*availability;
        return result;
    }
}
