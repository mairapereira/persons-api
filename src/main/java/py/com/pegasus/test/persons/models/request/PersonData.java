package py.com.pegasus.test.persons.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Schema
public class PersonData {
    @Schema(description = "Documento de identidad", required = true)
    private String document;

    @Schema(description = "Nombre de la persona", required = true)
    private String name;

    @Schema(description = "Apellido de la persona", required = true)
    private String lastName;

    @Schema(description = "Fecha de nacimiento", required = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
}