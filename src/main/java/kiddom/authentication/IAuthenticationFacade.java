package kiddom.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Created by Arianna on 8/6/2017.
 */

public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
