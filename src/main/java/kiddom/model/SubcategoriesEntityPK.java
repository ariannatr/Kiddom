package kiddom.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Arianna on 26/5/2017.
 */
public class SubcategoriesEntityPK implements Serializable {
    private int subId;
    private int catId;

    @Column(name = "sub_id")
    @Id
    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    @Column(name = "cat_id")
    @Id
    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubcategoriesEntityPK that = (SubcategoriesEntityPK) o;

        if (subId != that.subId) return false;
        if (catId != that.catId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subId;
        result = 31 * result + catId;
        return result;
    }
}
