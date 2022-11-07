package py.com.pegasus.test.persons.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import py.com.pegasus.test.persons.exceptions.ApiException;
import py.com.pegasus.test.persons.exceptions.ApiExceptionType;
import py.com.pegasus.test.persons.models.Person;
import py.com.pegasus.test.persons.models.request.PatchPersonData;
import py.com.pegasus.test.persons.models.request.PersonData;
import py.com.pegasus.test.persons.services.PersonsService;

import static py.com.pegasus.test.persons.constants.ApiSettings.API_VERSION;
import static py.com.pegasus.test.persons.constants.ApiErrorCodes.GENERIC_ERROR;
import static py.com.pegasus.test.persons.exceptions.ApiExceptionBuilder.buildApiExceptionFrom;

@RestController
@RequestMapping(value = API_VERSION + "/persons")
@RequiredArgsConstructor
@Slf4j
public class PersonsController {
    public static final String ERROR_INESPERADO_CHECKOUT = "Ocurrió un error inesperado crear la persona";
    private final PersonsService personService;

    @Operation(summary = "Obtiene los datos personales dado un identificador de persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Person.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error inesperado")})

    @GetMapping("/{person-id}")
    @ResponseStatus(code = HttpStatus.OK)
    private Person updatePerson(@PathVariable(name = "person-id") String personId) throws ApiException {
        try {
            return personService.findById(personId);
        } catch (ApiException ae) {
            log.error("Ocurrio un error obtener los datos de la persona", ae);
            throw ae;
        } catch (Exception e) {
            log.error(ERROR_INESPERADO_CHECKOUT, e);
            throw buildApiExceptionFrom(GENERIC_ERROR, ApiExceptionType.APPLICATION);
        }
    }

    @Operation(summary = "Crea una persona con sus datos personales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Person.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error inesperado")})

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    private Person createPerson(@RequestBody PersonData request) throws ApiException {
        try {
            return personService.create(request);
        } catch (ApiException ae) {
            log.error("Ocurrio un error al crear la persona", ae);
            throw ae;
        } catch (Exception e) {
            log.error(ERROR_INESPERADO_CHECKOUT, e);
            throw buildApiExceptionFrom(GENERIC_ERROR, ApiExceptionType.APPLICATION);
        }
    }

    @Operation(summary = "Actualiza los datos personales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Person.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error inesperado")})

    @PutMapping("/{person-id}")
    @ResponseStatus(code = HttpStatus.OK)
    private Person updatePerson(
            @PathVariable(name = "person-id") String personId,
            @RequestBody PersonData request) throws ApiException {
        try {
            return personService.update(personId, request);
        } catch (ApiException ae) {
            log.error("Ocurrio un error al actualizar la persona", ae);
            throw ae;
        } catch (Exception e) {
            log.error(ERROR_INESPERADO_CHECKOUT, e);
            throw buildApiExceptionFrom(GENERIC_ERROR, ApiExceptionType.APPLICATION);
        }
    }

    @Operation(summary = "Actualiza los datos personales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Person.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error inesperado")})

    @PatchMapping("/{person-id}")
    @ResponseStatus(code = HttpStatus.OK)
    private Person partialUpdatePerson(
            @PathVariable(name = "person-id") String personId,
            @RequestBody PatchPersonData request) throws ApiException {
        try {
            return personService.partialUpdate(personId, request);
        } catch (ApiException ae) {
            log.error("Ocurrio un error al actualizar la persona", ae);
            throw ae;
        } catch (Exception e) {
            log.error(ERROR_INESPERADO_CHECKOUT, e);
            throw buildApiExceptionFrom(GENERIC_ERROR, ApiExceptionType.APPLICATION);
        }
    }

    @Operation(summary = "Elimina una persona dado su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error inesperado")})

    @DeleteMapping("/{person-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    private void partialUpdatePerson(@PathVariable(name = "person-id") String personId) throws ApiException {
        try {
            personService.delete(personId);
        } catch (ApiException ae) {
            log.error("Ocurrio un error al eliminar la persona", ae);
            throw ae;
        } catch (Exception e) {
            log.error(ERROR_INESPERADO_CHECKOUT, e);
            throw buildApiExceptionFrom(GENERIC_ERROR, ApiExceptionType.APPLICATION);
        }
    }
}