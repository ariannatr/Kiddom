package kiddom.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Arianna on 26/5/2017.
 */
public class ProviderReportsEntityPK implements Serializable {
    private int reportId;
    private int providerId;

    @Column(name = "report_id")
    @Id
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    @Column(name = "provider_id")
    @Id
    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProviderReportsEntityPK that = (ProviderReportsEntityPK) o;

        if (reportId != that.reportId) return false;
        if (providerId != that.providerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reportId;
        result = 31 * result + providerId;
        return result;
    }
}
