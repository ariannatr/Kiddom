package kiddom.model;

import javax.persistence.*;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "spot_has_slot", schema = "mydb", catalog = "")
@IdClass(SpotHasSlotEntityPK.class)
public class SpotHasSlotEntity {
    private int spotId;
    private int spotProviderId;
    private int slotId;
    private SlotEntity slotBySlotId;

    @Id
    @Column(name = "spot_id")
    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    @Id
    @Column(name = "spot_provider_id")
    public int getSpotProviderId() {
        return spotProviderId;
    }

    public void setSpotProviderId(int spotProviderId) {
        this.spotProviderId = spotProviderId;
    }

    @Id
    @Column(name = "slot_id")
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

        SpotHasSlotEntity that = (SpotHasSlotEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "slot_id", referencedColumnName = "slot_id", nullable = false)
    public SlotEntity getSlotBySlotId() {
        return slotBySlotId;
    }

    public void setSlotBySlotId(SlotEntity slotBySlotId) {
        this.slotBySlotId = slotBySlotId;
    }
}
