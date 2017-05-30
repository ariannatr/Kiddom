package kiddom.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "categories", schema = "mydb", catalog = "")
public class CategoriesEntity {
    private int catId;
    private String name;
    private Collection<SubcategoriesEntity> subcategoriesByCatId;

    @Id
    @Column(name = "cat_id")
    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
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

        CategoriesEntity that = (CategoriesEntity) o;

        if (catId != that.catId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = catId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "categoriesByCatId")
    public Collection<SubcategoriesEntity> getSubcategoriesByCatId() {
        return subcategoriesByCatId;
    }

    public void setSubcategoriesByCatId(Collection<SubcategoriesEntity> subcategoriesByCatId) {
        this.subcategoriesByCatId = subcategoriesByCatId;
    }
}
