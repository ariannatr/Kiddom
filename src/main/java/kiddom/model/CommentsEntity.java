package kiddom.model;

import javax.persistence.*;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "comments", schema = "mydb", catalog = "")
@IdClass(CommentsEntityPK.class)
public class CommentsEntity {
    private int commentId;
    private String comment;
    private int spotId;
    private int providerId;
    private int parentId;
    private String reply;
    private Double rating;

    @Id
    @Column(name = "comment_id")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Id
    @Column(name = "spot_id")
    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    @Id
    @Column(name = "provider_id")
    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    @Id
    @Column(name = "parent_id")
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "reply")
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Basic
    @Column(name = "rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentsEntity that = (CommentsEntity) o;

        if (commentId != that.commentId) return false;
        if (spotId != that.spotId) return false;
        if (providerId != that.providerId) return false;
        if (parentId != that.parentId) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (reply != null ? !reply.equals(that.reply) : that.reply != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + spotId;
        result = 31 * result + providerId;
        result = 31 * result + parentId;
        result = 31 * result + (reply != null ? reply.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }
}
