package kiddom.model;

/**
 * Created by eleni on 02-Jun-17.
 */

import javax.persistence.*;

@Entity
@Table(name = "subcategories", schema = "mydb", catalog = "")
public class SubcategoriesEntity {
    private int subId;
    private String name;
    private int catId;
    private CategoriesEntity categoriesByCatId;

    @Id
    @Column(name = "sub_id")
    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
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
    @Column(name = "cat_id")
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

        SubcategoriesEntity that = (SubcategoriesEntity) o;

        if (subId != that.subId) return false;
        if (catId != that.catId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "cat_id", nullable = false)
    public CategoriesEntity getCategoriesByCatId() {
        return categoriesByCatId;
    }

    public void setCategoriesByCatId(CategoriesEntity categoriesByCatId) {
        this.categoriesByCatId = categoriesByCatId;
    }
}