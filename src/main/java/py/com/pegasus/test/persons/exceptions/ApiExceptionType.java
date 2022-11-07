package py.com.pegasus.test.persons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiExceptionType {
    APPLICATION,
    DATABASE,
    VALIDATION,
    SECURITY
}