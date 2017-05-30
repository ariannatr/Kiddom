package kiddom.model;

import javax.persistence.*;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "daily_event", schema = "mydb", catalog = "")
public class DailyEventEntity {
    private int deventId;
    private String name;
    private String town;
    private String area;
    private String address;
    private int number;
    private String postcode;
    private int capacity;
    private String sunday;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private Double duration;
    private String startTime;
    private String endTime;
    private String price;
    private Integer availabilitySun;
    private Integer availabilityMon;
    private Integer availabilityTue;
    private Integer availabilityWed;
    private Integer availabilityThu;
    private Integer availabilityFri;

    @Id
    @Column(name = "devent_id")
    public int getDeventId() {
        return deventId;
    }

    public void setDeventId(int deventId) {
        this.deventId = deventId;
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
    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "sunday")
    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    @Basic
    @Column(name = "monday")
    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    @Basic
    @Column(name = "tuesday")
    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    @Basic
    @Column(name = "wednesday")
    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    @Basic
    @Column(name = "thursday")
    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    @Basic
    @Column(name = "friday")
    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    @Basic
    @Column(name = "saturday")
    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    @Basic
    @Column(name = "duration")
    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
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
    @Column(name = "price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Basic
    @Column(name = "availability_sun")
    public Integer getAvailabilitySun() {
        return availabilitySun;
    }

    public void setAvailabilitySun(Integer availabilitySun) {
        this.availabilitySun = availabilitySun;
    }

    @Basic
    @Column(name = "availability_mon")
    public Integer getAvailabilityMon() {
        return availabilityMon;
    }

    public void setAvailabilityMon(Integer availabilityMon) {
        this.availabilityMon = availabilityMon;
    }

    @Basic
    @Column(name = "availability_tue")
    public Integer getAvailabilityTue() {
        return availabilityTue;
    }

    public void setAvailabilityTue(Integer availabilityTue) {
        this.availabilityTue = availabilityTue;
    }

    @Basic
    @Column(name = "availability_wed")
    public Integer getAvailabilityWed() {
        return availabilityWed;
    }

    public void setAvailabilityWed(Integer availabilityWed) {
        this.availabilityWed = availabilityWed;
    }

    @Basic
    @Column(name = "availability_thu")
    public Integer getAvailabilityThu() {
        return availabilityThu;
    }

    public void setAvailabilityThu(Integer availabilityThu) {
        this.availabilityThu = availabilityThu;
    }

    @Basic
    @Column(name = "availability_fri")
    public Integer getAvailabilityFri() {
        return availabilityFri;
    }

    public void setAvailabilityFri(Integer availabilityFri) {
        this.availabilityFri = availabilityFri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyEventEntity that = (DailyEventEntity) o;

        if (deventId != that.deventId) return false;
        if (number != that.number) return false;
        if (capacity != that.capacity) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (town != null ? !town.equals(that.town) : that.town != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (postcode != null ? !postcode.equals(that.postcode) : that.postcode != null) return false;
        if (sunday != null ? !sunday.equals(that.sunday) : that.sunday != null) return false;
        if (monday != null ? !monday.equals(that.monday) : that.monday != null) return false;
        if (tuesday != null ? !tuesday.equals(that.tuesday) : that.tuesday != null) return false;
        if (wednesday != null ? !wednesday.equals(that.wednesday) : that.wednesday != null) return false;
        if (thursday != null ? !thursday.equals(that.thursday) : that.thursday != null) return false;
        if (friday != null ? !friday.equals(that.friday) : that.friday != null) return false;
        if (saturday != null ? !saturday.equals(that.saturday) : that.saturday != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (availabilitySun != null ? !availabilitySun.equals(that.availabilitySun) : that.availabilitySun != null)
            return false;
        if (availabilityMon != null ? !availabilityMon.equals(that.availabilityMon) : that.availabilityMon != null)
            return false;
        if (availabilityTue != null ? !availabilityTue.equals(that.availabilityTue) : that.availabilityTue != null)
            return false;
        if (availabilityWed != null ? !availabilityWed.equals(that.availabilityWed) : that.availabilityWed != null)
            return false;
        if (availabilityThu != null ? !availabilityThu.equals(that.availabilityThu) : that.availabilityThu != null)
            return false;
        if (availabilityFri != null ? !availabilityFri.equals(that.availabilityFri) : that.availabilityFri != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deventId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + (sunday != null ? sunday.hashCode() : 0);
        result = 31 * result + (monday != null ? monday.hashCode() : 0);
        result = 31 * result + (tuesday != null ? tuesday.hashCode() : 0);
        result = 31 * result + (wednesday != null ? wednesday.hashCode() : 0);
        result = 31 * result + (thursday != null ? thursday.hashCode() : 0);
        result = 31 * result + (friday != null ? friday.hashCode() : 0);
        result = 31 * result + (saturday != null ? saturday.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (availabilitySun != null ? availabilitySun.hashCode() : 0);
        result = 31 * result + (availabilityMon != null ? availabilityMon.hashCode() : 0);
        result = 31 * result + (availabilityTue != null ? availabilityTue.hashCode() : 0);
        result = 31 * result + (availabilityWed != null ? availabilityWed.hashCode() : 0);
        result = 31 * result + (availabilityThu != null ? availabilityThu.hashCode() : 0);
        result = 31 * result + (availabilityFri != null ? availabilityFri.hashCode() : 0);
        return result;
    }
}
