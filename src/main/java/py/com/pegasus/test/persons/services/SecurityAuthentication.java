package py.com.pegasus.test.persons.services;

import org.springframework.security.core.Authentication;

public interface SecurityAuthentication {
    Authentication getAuthentication();
}