package py.com.pegasus.test.persons.services.mappers;

import lombok.experimental.UtilityClass;
import py.com.pegasus.test.persons.models.Person;
import py.com.pegasus.test.persons.models.request.PatchPersonData;
import py.com.pegasus.test.persons.models.request.PersonData;
import py.com.pegasus.test.persons.repositories.entities.PersonsEntity;

import java.time.OffsetDateTime;
import java.util.Optional;

@UtilityClass
public class PersonMapper {
    public static Person mapPersonEntityToDomain(PersonsEntity entity) {
        return Optional.ofNullable(entity)
                .map(ent -> Person.builder()
                        .id(ent.getId())
                        .document(ent.getDocument())
                        .name(ent.getName())
                        .lastName(ent.getLastName())
                        .birthDate(ent.getBirthDate())
                        .createdAt(ent.getCreatedAt())
                        .createdBy(ent.getCreatedBy())
                        .modifiedAt(ent.getModifiedAt())
                        .modifiedBy(ent.getModifiedBy())
                        .build())
                .orElse(null);

    }

    public static PersonsEntity buildPersonEntityToCreate(PersonData data, String user) {
        OffsetDateTime now = OffsetDateTime.now();
        return Optional.ofNullable(data)
                .map(req -> PersonsEntity.builder()
                        .name(data.getName())
                        .lastName(data.getLastName())
                        .document(data.getDocument())
                        .birthDate(data.getBirthDate())
                        .createdAt(now)
                        .createdBy(user)
                        .modifiedAt(now)
                        .modifiedBy(user)
                        .build())
                .orElse(null);
    }

    public static PersonsEntity buildPersonEntityToUpdate(PersonData data, PersonsEntity originalPerson, String user) {
        OffsetDateTime now = OffsetDateTime.now();
        return Optional.ofNullable(data)
                .map(req -> PersonsEntity.builder()
                        .id(originalPerson.getId())
                        .name(data.getName())
                        .lastName(data.getLastName())
                        .document(data.getDocument())
                        .birthDate(data.getBirthDate())
                        .createdAt(originalPerson.getCreatedAt())
                        .createdBy(originalPerson.getCreatedBy())
                        .modifiedAt(now)
                        .modifiedBy(user)
                        .build())
                .orElse(null);
    }

    public static PersonsEntity buildPersonEntityToUpdate(PatchPersonData data, PersonsEntity originalPerson, String user) {
        OffsetDateTime now = OffsetDateTime.now();
        return Optional.ofNullable(data)
                .map(req -> PersonsEntity.builder()
                        .id(originalPerson.getId())
                        .name(data.getName())
                        .lastName(data.getLastName())
                        .document(originalPerson.getDocument())
                        .birthDate(data.getBirthDate())
                        .createdAt(originalPerson.getCreatedAt())
                        .createdBy(originalPerson.getCreatedBy())
                        .modifiedAt(now)
                        .modifiedBy(user)
                        .build())
                .orElse(null);
    }
}
