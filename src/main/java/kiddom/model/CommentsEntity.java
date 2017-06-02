package kiddom.model;

/**
 * Created by eleni on 02-Jun-17.
**/

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comments", schema = "mydb")
public class CommentsEntity implements Serializable {
        private int commentId;
    private String comment;
    private String reply;
    private float rating;
    private int event_id;
    private String parent_username;
    private SingleEventEntity eventByEventId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "comment_id")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Id
    @Column(name = "event_id")
    public int getEventId() {
        return  event_id;
    }

    public void setEventId(int eventId) {
        this.event_id = eventId;
    }

    @Basic
    @Column(name = "parent_username")
    public String getParentUsername() {
        return parent_username;
    }

    public void setParentUsername(String parentUsername) {
        this.parent_username = parentUsername;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentsEntity that = (CommentsEntity) o;

        if (commentId != that.commentId) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (reply != null ? !reply.equals(that.reply) : that.reply != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (reply != null ? reply.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "event_id", referencedColumnName = "event_id")
    public SingleEventEntity getEventByEventId() {
        return eventByEventId;
    }

    public void setEventByEventId(SingleEventEntity eventByEventId) {
        this.eventByEventId = eventByEventId;
    }

}
