package kiddom.service;

import kiddom.model.ParentEntity;
import kiddom.model.ProgramEntity;
import kiddom.model.ProviderEntity;
import org.springframework.stereotype.Service;

/**
 * Created by Arianna on 4/7/2017.
 */
@Service("programService")
public interface ProgramService {
    public ProgramEntity getProgramById(Integer id);
    public void makeReservation(ParentEntity parentEntity, Integer num, ProgramEntity programEntity, ProviderEntity providerEntity);
}
