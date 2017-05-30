package kiddom.model;

import javax.persistence.*;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "user", schema = "mydb")
public class UserEntity {
    private int userId;
    private String username;
    private String password;
    private int type;
    private ParentEntity parentByUserId;
    private ProviderEntity providerByUserId;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (type != that.type) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + type;
        return result;
    }

    @OneToOne(mappedBy = "userByParentId")
    public ParentEntity getParentByUserId() {
        return parentByUserId;
    }

    public void setParentByUserId(ParentEntity parentByUserId) {
        this.parentByUserId = parentByUserId;
    }

    @OneToOne(mappedBy = "userByProviderId")
    public ProviderEntity getProviderByUserId() {
        return providerByUserId;
    }

    public void setProviderByUserId(ProviderEntity providerByUserId) {
        this.providerByUserId = providerByUserId;
    }
}
