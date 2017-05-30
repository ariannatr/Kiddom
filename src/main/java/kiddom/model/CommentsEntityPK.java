package kiddom.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Arianna on 26/5/2017.
 */
public class CommentsEntityPK implements Serializable {
    private int commentId;
    private int spotId;
    private int providerId;
    private int parentId;

    @Column(name = "comment_id")
    @Id
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Column(name = "spot_id")
    @Id
    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    @Column(name = "provider_id")
    @Id
    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    @Column(name = "parent_id")
    @Id
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentsEntityPK that = (CommentsEntityPK) o;

        if (commentId != that.commentId) return false;
        if (spotId != that.spotId) return false;
        if (providerId != that.providerId) return false;
        if (parentId != that.parentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + spotId;
        result = 31 * result + providerId;
        result = 31 * result + parentId;
        return result;
    }
}
