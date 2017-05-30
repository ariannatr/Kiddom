package kiddom.model;

import javax.persistence.*;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "areas", schema = "mydb")
@IdClass(AreasEntityPK.class)
public class AreasEntity {
    private int areaId;
    private String name;
    private int townId;
    private TownsEntity townsByTownId;

    @Id
    @Column(name = "area_id")
    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "town_id")
    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AreasEntity that = (AreasEntity) o;

        if (areaId != that.areaId) return false;
        if (townId != that.townId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = areaId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + townId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "town_id", nullable = false)
    public TownsEntity getTownsByTownId() {
        return townsByTownId;
    }

    public void setTownsByTownId(TownsEntity townsByTownId) {
        this.townsByTownId = townsByTownId;
    }
}
