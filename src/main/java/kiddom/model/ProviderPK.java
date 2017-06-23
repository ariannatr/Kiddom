package kiddom.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Stathis on 6/11/2017.
 */
@Embeddable
public class ProviderPK implements Serializable {

    /*--------------Primary foreign key: username, from user table--------------*/
    @OneToOne
    private UserEntity user;
    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParentPK parentPK = (ParentPK) o;

        //if (username != parentPK.username) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 31;
        return result;
    }
}
