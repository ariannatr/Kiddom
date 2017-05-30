package kiddom.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Arianna on 26/5/2017.
 */
public class AreasEntityPK implements Serializable {
    private int areaId;
    private int townId;

    @Column(name = "area_id")
    @Id
    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    @Column(name = "town_id")
    @Id
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

        AreasEntityPK that = (AreasEntityPK) o;

        if (areaId != that.areaId) return false;
        if (townId != that.townId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = areaId;
        result = 31 * result + townId;
        return result;
    }
}
