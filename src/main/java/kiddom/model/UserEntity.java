package kiddom.model;
//import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "user", schema = "mydb")
public class UserEntity {

    @Id
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="type")
    private int type;


    /*--------------One to One relation, mapping username at 'parent' table--------------*/
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade= CascadeType.ALL)
    private ParentEntity parent;

    /*--------------One to One relation, mapping username at 'provider' table--------------*/
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade= CascadeType.ALL)
    private ProviderEntity provider;

    /*--------------Getters - Setters for table fields--------------*/

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public ParentEntity getParent() { return parent; }
    public void setParent(ParentEntity parent) { this.parent = parent; }

    public ProviderEntity getProvider() { return provider; }
    public void setProvider(ProviderEntity provider) { this.provider = provider; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (type != that.type) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

}
