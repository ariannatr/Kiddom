package kiddom.model;

/**
 * Created by eleni on 02-Jun-17.
**/

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "parent_reports", schema = "mydb")
public class ParentReportsEntity implements Serializable {
    private int reportId;
    private String report;
    private String provider;
    private String parent_username;
    private ParentEntity parentByParentId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Id
    @Column(name = "parent_username")
    public String getParentUsername() {
        return parent_username;
    }

    public void setParentUsername(String parent_username) {
        this.parent_username = parent_username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParentReportsEntity that = (ParentReportsEntity) o;

        if (reportId != that.reportId) return false;
        if (provider != null ? !provider.equals(that.provider) : that.provider != null) return false;
        if (report != null ? !report.equals(that.report) : that.report != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reportId;
        result = 31 * result + (report != null ? report.hashCode() : 0);
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "parent_username", referencedColumnName = "username")
    public ParentEntity getParentByParentId() {
        return parentByParentId;
    }

    public void setParentByParentId(ParentEntity parentByParentId) {
        this.parentByParentId = parentByParentId;
    }
}
