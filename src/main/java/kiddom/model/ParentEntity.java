package kiddom.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "parent", schema = "mydb")
public class ParentEntity {
    private int parentId;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private String town;
    private String area;
    private String photo;
    private int availPoints;
    private int restrPoints;
    private int totalPoints;
   // private Collection<CookiesEntity> cookiesByParentId;
    private UserEntity userByParentId;
   // private Collection<ParentReportsEntity> parentReportsByParentId;

    @Id
    @Column(name = "parent_id")
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "avail_points")
    public int getAvailPoints() {
        return availPoints;
    }

    public void setAvailPoints(int availPoints) {
        this.availPoints = availPoints;
    }

    @Basic
    @Column(name = "restr_points")
    public int getRestrPoints() {
        return restrPoints;
    }

    public void setRestrPoints(int restrPoints) {
        this.restrPoints = restrPoints;
    }

    @Basic
    @Column(name = "total_points")
    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParentEntity that = (ParentEntity) o;

        if (parentId != that.parentId) return false;
        if (availPoints != that.availPoints) return false;
        if (restrPoints != that.restrPoints) return false;
        if (totalPoints != that.totalPoints) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (town != null ? !town.equals(that.town) : that.town != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parentId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + availPoints;
        result = 31 * result + restrPoints;
        result = 31 * result + totalPoints;
        return result;
    }

    //@OneToMany(mappedBy = "parentByParentId")
    //public Collection<CookiesEntity> getCookiesByParentId() {
    //    return cookiesByParentId;
    //}

   // public void setCookiesByParentId(Collection<CookiesEntity> cookiesByParentId) {
    //    this.cookiesByParentId = cookiesByParentId;
   // }

    @OneToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByParentId() {
        return userByParentId;
    }

    public void setUserByParentId(UserEntity userByParentId) {
        this.userByParentId = userByParentId;
    }

   // @OneToMany(mappedBy = "parentByParentId")
    //public Collection<ParentReportsEntity> getParentReportsByParentId() {
    //    return parentReportsByParentId;
    //}

    //public void setParentReportsByParentId(Collection<ParentReportsEntity> parentReportsByParentId) {
    //    this.parentReportsByParentId = parentReportsByParentId;
   // }
}