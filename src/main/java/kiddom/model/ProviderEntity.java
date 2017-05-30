package kiddom.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "provider", schema = "mydb", catalog = "")
public class ProviderEntity {
    private int providerId;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private String tr;
    private int owedPoints;
    private int gottenPoints;
    private int totalPoints;
    private UserEntity userByProviderId;
    private Collection<ProviderReportsEntity> providerReportsByProviderId;
    private Collection<SpotEntity> spotsByProviderId;

    @Id
    @Column(name = "provider_id")
    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
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
    @Column(name = "TR")
    public String getTr() {
        return tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }

    @Basic
    @Column(name = "owed_points")
    public int getOwedPoints() {
        return owedPoints;
    }

    public void setOwedPoints(int owedPoints) {
        this.owedPoints = owedPoints;
    }

    @Basic
    @Column(name = "gotten_points")
    public int getGottenPoints() {
        return gottenPoints;
    }

    public void setGottenPoints(int gottenPoints) {
        this.gottenPoints = gottenPoints;
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

        ProviderEntity that = (ProviderEntity) o;

        if (providerId != that.providerId) return false;
        if (owedPoints != that.owedPoints) return false;
        if (gottenPoints != that.gottenPoints) return false;
        if (totalPoints != that.totalPoints) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (tr != null ? !tr.equals(that.tr) : that.tr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = providerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (tr != null ? tr.hashCode() : 0);
        result = 31 * result + owedPoints;
        result = 31 * result + gottenPoints;
        result = 31 * result + totalPoints;
        return result;
    }

    @OneToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByProviderId() {
        return userByProviderId;
    }

    public void setUserByProviderId(UserEntity userByProviderId) {
        this.userByProviderId = userByProviderId;
    }

    @OneToMany(mappedBy = "providerByProviderId")
    public Collection<ProviderReportsEntity> getProviderReportsByProviderId() {
        return providerReportsByProviderId;
    }

    public void setProviderReportsByProviderId(Collection<ProviderReportsEntity> providerReportsByProviderId) {
        this.providerReportsByProviderId = providerReportsByProviderId;
    }

    @OneToMany(mappedBy = "providerByProviderId")
    public Collection<SpotEntity> getSpotsByProviderId() {
        return spotsByProviderId;
    }

    public void setSpotsByProviderId(Collection<SpotEntity> spotsByProviderId) {
        this.spotsByProviderId = spotsByProviderId;
    }
}
