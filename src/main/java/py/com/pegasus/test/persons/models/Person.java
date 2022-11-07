package py.com.pegasus.test.persons.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Schema(description = "Identificador único de la persona")
    private UUID id;

    @Schema(description = "Nombre de la persona")
    private String name;

    @Schema(description = "Apellido de la persona")
    private String lastName;

    @Schema(description = "Documento de identidad")
    private String document;

    @Schema(description = "Fecha de nacimiento")
    private LocalDate birthDate;

    @Schema(description = "Fecha de creación de la persona")
    private OffsetDateTime createdAt;

    @Schema(description = "Usuario que creó la persona")
    private String createdBy;

    @Schema(description = "Fecha de última modificación de datos de la persona")
    private OffsetDateTime modifiedAt;

    @Schema(description = "Usuario que realizó la última modificación de datos de la persona")
    private String modifiedBy;
}