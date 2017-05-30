package kiddom.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "towns", schema = "mydb", catalog = "")
public class TownsEntity {
    private int townId;
    private String name;
    private Collection<AreasEntity> areasByTownId;

    @Id
    @Column(name = "town_id")
    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TownsEntity that = (TownsEntity) o;

        if (townId != that.townId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = townId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "townsByTownId")
    public Collection<AreasEntity> getAreasByTownId() {
        return areasByTownId;
    }

    public void setAreasByTownId(Collection<AreasEntity> areasByTownId) {
        this.areasByTownId = areasByTownId;
    }
}
