package kiddom.model;

/**
 * Created by eleni on 02-Jun-17.
**/

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "single_event", schema = "mydb")
public class SingleEventEntity implements Serializable {
    private String provider_username;
    private int eventId;
    private String name;
    private String description;
    private String photos;
    private String date;
    private String startTime;
    private String endTime;
    private int capacity;
    private int price;
    private int availability;
    private String category;
    private String sub1;
    private String sub2;
    private String sub3;
    private String town;
    private String area;
    private String address;
    private int number;
    private int postcode;
    private float rating;
    private ProviderEntity providerByProviderId;
    //private ReservationsEntity reservationsByEventId;
    private Collection<CommentsEntity> commentsByEventId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "event_id")
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "provider_username")
    public String getProvider_username() {
        return provider_username;
    }

    public void setProvider_username(String provider_username) {
        this.provider_username = provider_username;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "photos")
    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
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

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "sub1")
    public String getSub1() {
        return sub1;
    }

    public void setSub1(String sub1) {
        this.sub1 = sub1;
    }

    @Basic
    @Column(name = "sub2")
    public String getSub2() {
        return sub2;
    }

    public void setSub2(String sub2) {
        this.sub2 = sub2;
    }

    @Basic
    @Column(name = "sub3")
    public String getSub3() {
        return sub3;
    }

    public void setSub3(String sub3) {
        this.sub3 = sub3;
    }

    @Basic
    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column (name = "town")
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "postcode")
    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "rating")
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleEventEntity that = (SingleEventEntity) o;

        if (eventId != that.eventId) return false;
        if (capacity != that.capacity) return false;
        if (price != that.price) return false;
        if (availability != that.availability) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (photos != null ? !photos.equals(that.photos) : that.photos != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (sub1 != null ? !sub1.equals(that.sub1) : that.sub1 != null) return false;
        if (sub2 != null ? !sub2.equals(that.sub2) : that.sub2 != null) return false;
        if (sub3 != null ? !sub3.equals(that.sub3) : that.sub3 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photos != null ? photos.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + price;
        result = 31 * result + availability;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (sub1 != null ? sub1.hashCode() : 0);
        result = 31 * result + (sub2 != null ? sub2.hashCode() : 0);
        result = 31 * result + (sub3 != null ? sub3.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "provider_username", referencedColumnName = "username")
    public ProviderEntity getProviderByProviderId() {
        return providerByProviderId;
    }

    public void setProviderByProviderId(ProviderEntity providerByProviderId) {
        this.providerByProviderId = providerByProviderId;
    }


    /*@ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = false)
    public ReservationsEntity getReservationsByEventId() {
        return reservationsByEventId;
    }

    public void setReservationsByEventId(ReservationsEntity reservationsByEventId) {
        this.reservationsByEventId = reservationsByEventId;
    }*/

    @OneToMany(mappedBy = "eventByEventId")
    public Collection<CommentsEntity> getCommentsByEventId() {
        return commentsByEventId;
    }

    public void setCommentsByEventId(Collection<CommentsEntity> commentsByEventId) {
        this.commentsByEventId = commentsByEventId;
    }
}
