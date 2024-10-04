package dev.haguel.dds.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
public class DriverDTO {
    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private short experience;
}
