package kiddom.model;

/**
 * Created by eleni on 02-Jun-17.
 **/

import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.print.attribute.SupportedValuesAttribute;
import java.io.Serializable;

@Entity
@Table(name = "subcategories", schema = "mydb")
//@AssociationOverrides({
//        @AssociationOverride(name="name", joinColumns = @JoinColumn(name="category_name"))
//})

public class SubcategoriesEntity implements Serializable{



    @EmbeddedId
    private SubcategoriesPK pk = new SubcategoriesPK();
    public SubcategoriesPK getPk(){ return this.pk; }

    public void setPk(SubcategoriesPK pk){ this.pk = pk; }

    @Transient
    public String getSubName(){ return getPk().getSubname(); }

    public void setSubName(String subname){ getPk().setSubname(subname); }

    @Transient
    public CategoriesEntity getCategory(){ return this.pk.getCategoryName();}

    public void setCategory(CategoriesEntity category){ getPk().setCategoryName(category);}
//    @Transient
//    public String getCatName(){ return getPk().getCategoryName();}
//
//    public void setCatName(String subname) { getPk().setCategoryName(subname);}

//    @ManyToOne
//    @JoinColumn(name = "category_name")
//    private CategoriesEntity category_name;



//    @Transient
//    public CategoriesEntity getCatName(){ return getPk().getCategoryName();}
//
//    public void set(CategoriesEntity category){ getPk().setCategoryName(category);}

//    private String subname;
//    private CategoriesEntity categoriesByCatId;
//
//    @EmbeddedId
//    private SubcategoriesPK categs;
//    @Transient // means "not a DB field"
//    private Integer remove; // boolean flag
//    public Integer getRemove()
//    {
//        return remove;
//    }
//    public void setRemove(Integer set){this.remove=set;}
//
//
//    @Id
//    @Column(name = "subname")
//    public String getName() {
//        return subname;
//    }
//
//    public void setName(String subname) {
//        this.subname = subname;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SubcategoriesEntity that = (SubcategoriesEntity) o;
//        if (subname != null ? !subname.equals(that.subname) : that.subname != null) return false;
//
//        return true;
//    }
//
//    @ManyToOne
//    @PrimaryKeyJoinColumn(name = "category_name", referencedColumnName = "name")
//    public CategoriesEntity getCategoriesByCatId() {
//        return categoriesByCatId;
//    }
//
//    public void setCategoriesByCatId(CategoriesEntity categoriesByCatId) {
//        this.categoriesByCatId = categoriesByCatId;
//    }
}