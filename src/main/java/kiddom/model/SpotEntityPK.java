package kiddom.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Arianna on 26/5/2017.
 */
public class SpotEntityPK implements Serializable {
    private int providerId;
    private int spotId;
    private int dailyEventId;

    @Column(name = "provider_id")
    @Id
    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    @Column(name = "spot_id")
    @Id
    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    @Column(name = "daily_event_id")
    @Id
    public int getDailyEventId() {
        return dailyEventId;
    }

    public void setDailyEventId(int dailyEventId) {
        this.dailyEventId = dailyEventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpotEntityPK that = (SpotEntityPK) o;

        if (providerId != that.providerId) return false;
        if (spotId != that.spotId) return false;
        if (dailyEventId != that.dailyEventId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = providerId;
        result = 31 * result + spotId;
        result = 31 * result + dailyEventId;
        return result;
    }
}
