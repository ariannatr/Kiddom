package kiddom.model;

import javax.persistence.*;

/**
 * Created by Arianna on 26/5/2017.
 */
@Entity
@Table(name = "parent_reports", schema = "mydb", catalog = "")
@IdClass(ParentReportsEntityPK.class)
public class ParentReportsEntity {
    private int reportId;
    private String report;
    private int provider;
    private int parentId;
    private ParentEntity parentByParentId;

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
    @Column(name = "provider")
    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    @Id
    @Column(name = "parent_id")
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

        ParentReportsEntity that = (ParentReportsEntity) o;

        if (reportId != that.reportId) return false;
        if (provider != that.provider) return false;
        if (parentId != that.parentId) return false;
        if (report != null ? !report.equals(that.report) : that.report != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reportId;
        result = 31 * result + (report != null ? report.hashCode() : 0);
        result = 31 * result + provider;
        result = 31 * result + parentId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "parent_id", nullable = false)
    public ParentEntity getParentByParentId() {
        return parentByParentId;
    }

    public void setParentByParentId(ParentEntity parentByParentId) {
        this.parentByParentId = parentByParentId;
    }
}
