package kiddom.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Arianna on 26/5/2017.
 */
public class SpotHasSlotEntityPK implements Serializable {
    private int spotId;
    private int spotProviderId;
    private int slotId;

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

    @Column(name = "slot_id")
    @Id
    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpotHasSlotEntityPK that = (SpotHasSlotEntityPK) o;

        if (spotId != that.spotId) return false;
        if (spotProviderId != that.spotProviderId) return false;
        if (slotId != that.slotId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = spotId;
        result = 31 * result + spotProviderId;
        result = 31 * result + slotId;
        return result;
    }
}
