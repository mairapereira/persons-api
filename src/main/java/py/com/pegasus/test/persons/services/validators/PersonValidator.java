package py.com.pegasus.test.persons.services.validators;

import lombok.experimental.UtilityClass;
import py.com.pegasus.test.persons.exceptions.ApiException;
import py.com.pegasus.test.persons.exceptions.ApiExceptionBuilder;
import py.com.pegasus.test.persons.exceptions.ApiExceptionType;
import py.com.pegasus.test.persons.models.request.PatchPersonData;
import py.com.pegasus.test.persons.models.request.PersonData;

import java.time.LocalDate;
import java.util.Optional;

import static py.com.pegasus.test.persons.constants.ApiErrorCodes.INVALID_REQUEST;

@UtilityClass
public class PersonValidator {
    public static final String INVALID_DOCUMENT = "Documento nulo o vacío";
    public static final String INVALID_UPDATE_DATA = "Los datos a utilizar en la actualización son nulos o vacíos";

    public static void validatePersonData(PersonData data) throws ApiException {
        validateDocument(data.getDocument());
        validateName(data.getName());
        validateLastName(data.getLastName());
        validateBirthDate(data.getBirthDate());
    }
    public static void validatePatchPersonData(PatchPersonData data) throws ApiException {
        Optional.ofNullable(data)
        .filter(personData -> personData.getName()!=null || personData.getLastName()!=null || personData.getBirthDate()!=null)
        .orElseThrow(() -> ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(),
                INVALID_REQUEST.getUsrMsj(),
                INVALID_UPDATE_DATA,
                ApiExceptionType.VALIDATION));
        validateName(data.getName());
        validateLastName(data.getLastName());
        validateBirthDate(data.getBirthDate());
    }
    public static void validatDeletePersonData(String id) throws ApiException {
        if (id == null || id.trim().isEmpty())
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    "Identificador de persona nulo o vacío", ApiExceptionType.VALIDATION);
    }

    public static void validatFindPersonData(String id) throws ApiException {
        if (id == null || id.trim().isEmpty())
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    "Identificador de persona nulo o vacío", ApiExceptionType.VALIDATION);
    }

    public static void validateDocument(String document) throws ApiException {
        if (document == null || document.trim().isEmpty())
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    INVALID_DOCUMENT, ApiExceptionType.VALIDATION);
    }

    public static void validateName(String name) throws ApiException {
        if (name == null || name.trim().isEmpty())
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    "El nombre es nulo o vacío", ApiExceptionType.VALIDATION);
    }

    public static void validateLastName(String lastName) throws ApiException {
        if (lastName == null || lastName.trim().isEmpty())
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    "El apellido es nulo o vacío", ApiExceptionType.VALIDATION);
    }

    public static void validateBirthDate(LocalDate dateTime) throws ApiException {
        if (dateTime == null || LocalDate.now().isBefore(dateTime))
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    "La fecha de nacimiento no es válida", ApiExceptionType.VALIDATION);
    }
}
