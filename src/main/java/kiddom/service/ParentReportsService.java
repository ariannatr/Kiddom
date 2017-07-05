package kiddom.service;

import kiddom.model.ParentEntity;
import kiddom.model.ParentReportsEntity;
import kiddom.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Stathis on 7/5/2017.
 */
@Service("parentReportsService")
public interface ParentReportsService {
    public void saveParentReport(ParentReportsEntity report);
    public ArrayList<ParentReportsEntity> getReportsByUser(ParentEntity user);
}
