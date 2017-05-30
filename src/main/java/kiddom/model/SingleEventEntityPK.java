package kiddom.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Arianna on 26/5/2017.
 */
public class SingleEventEntityPK implements Serializable {
    private int eventId;
    private int spotId;
    private int spotProviderId;

    @Column(name = "event_id")
    @Id
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Column(name = "spot_id")
    @Id
    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    @Column(name = "spot_provider_id")
    @Id
    public int getSpotProviderId() {
        return spotProviderId;
    }

    public void setSpotProviderId(int spotProviderId) {
        this.spotProviderId = spotProviderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleEventEntityPK that = (SingleEventEntityPK) o;

        if (eventId != that.eventId) return false;
        if (spotId != that.spotId) return false;
        if (spotProviderId != that.spotProviderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + spotId;
        result = 31 * result + spotProviderId;
        return result;
    }
}
