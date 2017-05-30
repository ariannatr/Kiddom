package kiddom.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Arianna on 26/5/2017.
 */
public class CookiesEntityPK implements Serializable {
    private int parentId;
    private int cookieId;

    @Column(name = "parent_id")
    @Id
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Column(name = "cookie_id")
    @Id
    public int getCookieId() {
        return cookieId;
    }

    public void setCookieId(int cookieId) {
        this.cookieId = cookieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CookiesEntityPK that = (CookiesEntityPK) o;

        if (parentId != that.parentId) return false;
        if (cookieId != that.cookieId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parentId;
        result = 31 * result + cookieId;
        return result;
    }
}
