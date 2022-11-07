package py.com.pegasus.test.persons.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import py.com.pegasus.test.persons.exceptions.ApiException;
import py.com.pegasus.test.persons.exceptions.ApiExceptionBuilder;
import py.com.pegasus.test.persons.exceptions.ApiExceptionType;
import py.com.pegasus.test.persons.models.Person;
import py.com.pegasus.test.persons.models.request.PatchPersonData;
import py.com.pegasus.test.persons.models.request.PersonData;
import py.com.pegasus.test.persons.repositories.PersonRepository;
import py.com.pegasus.test.persons.repositories.entities.PersonsEntity;
import py.com.pegasus.test.persons.services.mappers.PersonMapper;

import java.util.UUID;

import static py.com.pegasus.test.persons.constants.ApiErrorCodes.PERSON_NOT_FOUND;
import static py.com.pegasus.test.persons.services.mappers.PersonMapper.buildPersonEntityToCreate;
import static py.com.pegasus.test.persons.services.mappers.PersonMapper.buildPersonEntityToUpdate;
import static py.com.pegasus.test.persons.services.validators.PersonValidator.validatDeletePersonData;
import static py.com.pegasus.test.persons.services.validators.PersonValidator.validatFindPersonData;
import static py.com.pegasus.test.persons.services.validators.PersonValidator.validatePatchPersonData;
import static py.com.pegasus.test.persons.services.validators.PersonValidator.validatePersonData;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonsServiceImpl implements PersonsService {
    private final PersonRepository personsRepository;
    private final SecurityAuthentication securityAuthentication;

    @Override
    public Person findById(String id) throws ApiException {
        log.info("Intentaremos obtener los datos de la persona");

        //validamos datos de entrada
        validatFindPersonData(id);

        //obtenemos los datos de la persona
        Person person = personsRepository.findById(UUID.fromString(id))
                .map(PersonMapper::mapPersonEntityToDomain)
                .orElseThrow(() -> ApiExceptionBuilder.buildApiExceptionFrom(PERSON_NOT_FOUND,
                        ApiExceptionType.VALIDATION));

        log.info("Se obtuvo los datos de la persona exitosamente");
        return person;
    }
    @Override
    public Person create(PersonData data) throws ApiException {
        log.info("Intentaremos crear persona");

        //validamos datos de entrada
        validatePersonData(data);

        //creamos persona
        String user = securityAuthentication.getAuthentication().getName();
        PersonsEntity entity = buildPersonEntityToCreate(data, user);
        PersonsEntity personCreated = personsRepository.save(entity);

        //retornamos persona creada
        Person person = PersonMapper.mapPersonEntityToDomain(personCreated);

        log.info("Persona creada exitosamente");
        return person;
    }

    @Override
    public Person update(String id, PersonData data) throws ApiException {
        log.info("Intentaremos actualizar los datos de la persona");

        //validamos datos de entrada
        validatePersonData(data);
        PersonsEntity originalPerson = personsRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> ApiExceptionBuilder.buildApiExceptionFrom(PERSON_NOT_FOUND,
                        ApiExceptionType.VALIDATION));

        //actualizamos todos los datos de la persona
        String user = securityAuthentication.getAuthentication().getName();
        PersonsEntity entity = buildPersonEntityToUpdate(data, originalPerson, user);
        PersonsEntity personUpdated = personsRepository.save(entity);

        //retornamos persona actualizada
        Person person = PersonMapper.mapPersonEntityToDomain(personUpdated);

        log.info("Persona actualizada exitosamente");
        return person;
    }

    @Override
    public Person partialUpdate(String id, PatchPersonData data) throws ApiException {
        log.info("Intentaremos actualizar algunos datos de la persona");

        //validamos datos de entrada
        validatePatchPersonData(data);
        PersonsEntity originalPerson = personsRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> ApiExceptionBuilder.buildApiExceptionFrom(PERSON_NOT_FOUND,
                        ApiExceptionType.VALIDATION));

        //actualiamos solo algunos datos de la persona
        String user = securityAuthentication.getAuthentication().getName();
        PersonsEntity entity = buildPersonEntityToUpdate(data, originalPerson, user);
        PersonsEntity personUpdated = personsRepository.save(entity);

        //retornamos persona actualizada
        Person person = PersonMapper.mapPersonEntityToDomain(personUpdated);

        log.info("Persona actualizada exitosamente");
        return person;
    }

    @Override
    public void delete(String id) throws ApiException {
        log.info("Intentaremos actualizar algunos datos de la persona");

        //validamos datos de entrada
        validatDeletePersonData(id);
        PersonsEntity originalPerson = personsRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> ApiExceptionBuilder.buildApiExceptionFrom(PERSON_NOT_FOUND,
                        ApiExceptionType.VALIDATION));

        //eliminamos a la persona
        personsRepository.delete(originalPerson);

        log.info("Persona eliminada exitosamente");
    }

}
