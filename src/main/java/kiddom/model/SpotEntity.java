package kiddom.model;

import javax.persistence.*;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "spot", schema = "mydb", catalog = "")
@IdClass(SpotEntityPK.class)
public class SpotEntity {
    private int providerId;
    private int spotId;
    private int dailyEventId;
    private String town;
    private String area;
    private String address;
    private int number;
    private String postcode;
    private Double rating;
    private String photos;
    private CommentsEntity commentsBySpotId;
    private SingleEventEntity singleEventBySpotId;
    private ProviderEntity providerByProviderId;
    private SpotHasSlotEntity spotHasSlotBySpotId;

    @Id
    @Column(name = "provider_id")
    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    @Id
    @Column(name = "spot_id")
    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    @Id
    @Column(name = "daily_event_id")
    public int getDailyEventId() {
        return dailyEventId;
    }

    public void setDailyEventId(int dailyEventId) {
        this.dailyEventId = dailyEventId;
    }

    @Basic
    @Column(name = "town")
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
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
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "photos")
    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpotEntity that = (SpotEntity) o;

        if (providerId != that.providerId) return false;
        if (spotId != that.spotId) return false;
        if (dailyEventId != that.dailyEventId) return false;
        if (number != that.number) return false;
        if (town != null ? !town.equals(that.town) : that.town != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (postcode != null ? !postcode.equals(that.postcode) : that.postcode != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        if (photos != null ? !photos.equals(that.photos) : that.photos != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = providerId;
        result = 31 * result + spotId;
        result = 31 * result + dailyEventId;
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (photos != null ? photos.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "spot_id", referencedColumnName = "spot_id", nullable = false)
    public CommentsEntity getCommentsBySpotId() {
        return commentsBySpotId;
    }

    public void setCommentsBySpotId(CommentsEntity commentsBySpotId) {
        this.commentsBySpotId = commentsBySpotId;
    }

    @ManyToOne
    @JoinColumn(name = "spot_id", referencedColumnName = "spot_id", nullable = false)
    public SingleEventEntity getSingleEventBySpotId() {
        return singleEventBySpotId;
    }

    public void setSingleEventBySpotId(SingleEventEntity singleEventBySpotId) {
        this.singleEventBySpotId = singleEventBySpotId;
    }

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id", nullable = false)
    public ProviderEntity getProviderByProviderId() {
        return providerByProviderId;
    }

    public void setProviderByProviderId(ProviderEntity providerByProviderId) {
        this.providerByProviderId = providerByProviderId;
    }

    @ManyToOne
    @JoinColumn(name = "spot_id", referencedColumnName = "spot_id", nullable = false)
    public SpotHasSlotEntity getSpotHasSlotBySpotId() {
        return spotHasSlotBySpotId;
    }

    public void setSpotHasSlotBySpotId(SpotHasSlotEntity spotHasSlotBySpotId) {
        this.spotHasSlotBySpotId = spotHasSlotBySpotId;
    }
}
