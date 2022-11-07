package py.com.pegasus.test.persons.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import py.com.pegasus.test.persons.repositories.entities.PersonsEntity;

import java.util.UUID;

@Repository
public interface PersonRepository extends CrudRepository<PersonsEntity, UUID> {
}
