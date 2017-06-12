package kiddom.model;

/**
 * Created by eleni on 02-Jun-17.
 **/

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subcategories", schema = "mydb")
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
}