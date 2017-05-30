package kiddom.model;

import javax.persistence.*;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "cookies", schema = "mydb", catalog = "")
@IdClass(CookiesEntityPK.class)
public class CookiesEntity {
    private int parentId;
    private int cookieId;
    private String category;
    private String subcat1;
    private String subcat2;
    private String subcat3;
    private String price;
    private ParentEntity parentByParentId;

    @Id
    @Column(name = "parent_id")
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Id
    @Column(name = "cookie_id")
    public int getCookieId() {
        return cookieId;
    }

    public void setCookieId(int cookieId) {
        this.cookieId = cookieId;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "subcat1")
    public String getSubcat1() {
        return subcat1;
    }

    public void setSubcat1(String subcat1) {
        this.subcat1 = subcat1;
    }

    @Basic
    @Column(name = "subcat2")
    public String getSubcat2() {
        return subcat2;
    }

    public void setSubcat2(String subcat2) {
        this.subcat2 = subcat2;
    }

    @Basic
    @Column(name = "subcat3")
    public String getSubcat3() {
        return subcat3;
    }

    public void setSubcat3(String subcat3) {
        this.subcat3 = subcat3;
    }

    @Basic
    @Column(name = "price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CookiesEntity that = (CookiesEntity) o;

        if (parentId != that.parentId) return false;
        if (cookieId != that.cookieId) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (subcat1 != null ? !subcat1.equals(that.subcat1) : that.subcat1 != null) return false;
        if (subcat2 != null ? !subcat2.equals(that.subcat2) : that.subcat2 != null) return false;
        if (subcat3 != null ? !subcat3.equals(that.subcat3) : that.subcat3 != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parentId;
        result = 31 * result + cookieId;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (subcat1 != null ? subcat1.hashCode() : 0);
        result = 31 * result + (subcat2 != null ? subcat2.hashCode() : 0);
        result = 31 * result + (subcat3 != null ? subcat3.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "parent_id", nullable = false)
    public ParentEntity getParentByParentId() {
        return parentByParentId;
    }

    public void setParentByParentId(ParentEntity parentByParentId) {
        this.parentByParentId = parentByParentId;
    }
}
