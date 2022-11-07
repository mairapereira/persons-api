package py.com.pegasus.test.persons.services;

import py.com.pegasus.test.persons.exceptions.ApiException;
import py.com.pegasus.test.persons.models.Person;
import py.com.pegasus.test.persons.models.request.PatchPersonData;
import py.com.pegasus.test.persons.models.request.PersonData;

public interface PersonsService {
    Person findById(String id) throws ApiException;

    Person create(PersonData data) throws ApiException;

    Person update(String id, PersonData data) throws ApiException;

    Person partialUpdate(String id, PatchPersonData data) throws ApiException;

    void delete(String id) throws ApiException;
}
