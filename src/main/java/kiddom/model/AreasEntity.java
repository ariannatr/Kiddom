package kiddom.model;

/**
  Created by eleni on 02-Jun-17.
**/

import javax.persistence.*;

@Entity
@Table(name = "areas", schema = "mydb")
public class AreasEntity {
    private int areaId;
    private String name;
    private TownsEntity townsByTownId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreasEntity that = (AreasEntity) o;
        if (areaId != that.areaId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "towns_name", referencedColumnName = "name")
    public TownsEntity getTownsByTownId() {
        return townsByTownId;
    }

    public void setTownsByTownId(TownsEntity townsByTownId) {
        this.townsByTownId = townsByTownId;
    }


}
