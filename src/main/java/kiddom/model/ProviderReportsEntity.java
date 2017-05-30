package kiddom.model;

import javax.persistence.*;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "provider_reports", schema = "mydb", catalog = "")
@IdClass(ProviderReportsEntityPK.class)
public class ProviderReportsEntity {
    private int reportId;
    private String report;
    private int userId;
    private int providerId;
    private ProviderEntity providerByProviderId;

    @Id
    @Column(name = "report_id")
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    @Basic
    @Column(name = "report")
    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "provider_id")
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

        ProviderReportsEntity that = (ProviderReportsEntity) o;

        if (reportId != that.reportId) return false;
        if (userId != that.userId) return false;
        if (providerId != that.providerId) return false;
        if (report != null ? !report.equals(that.report) : that.report != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reportId;
        result = 31 * result + (report != null ? report.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + providerId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id", nullable = false)
    public ProviderEntity getProviderByProviderId() {
        return providerByProviderId;
    }

    public void setProviderByProviderId(ProviderEntity providerByProviderId) {
        this.providerByProviderId = providerByProviderId;
    }
}
