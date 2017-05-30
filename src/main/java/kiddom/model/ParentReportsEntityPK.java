package kiddom.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Arianna on 26/5/2017.
 */
public class ParentReportsEntityPK implements Serializable {
    private int reportId;
    private int parentId;

    @Column(name = "report_id")
    @Id
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    @Column(name = "parent_id")
    @Id
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

        ParentReportsEntityPK that = (ParentReportsEntityPK) o;

        if (reportId != that.reportId) return false;
        if (parentId != that.parentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reportId;
        result = 31 * result + parentId;
        return result;
    }
}
