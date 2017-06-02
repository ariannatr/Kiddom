package kiddom.model;

/**
 * Created by eleni on 02-Jun-17.
**/

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "categories", schema = "mydb")
public class CategoriesEntity {
    private String name;
    private Collection<SubcategoriesEntity> subcategoriesByCatId;

    @Id
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
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return true;
    }

    @OneToMany(mappedBy = "categoriesByCatId")
    public Collection<SubcategoriesEntity> getSubcategoriesByCatId() {
        return subcategoriesByCatId;
    }

    public void setSubcategoriesByCatId(Collection<SubcategoriesEntity> subcategoriesByCatId) {
        this.subcategoriesByCatId = subcategoriesByCatId;
    }
}
