package dev.haguel.dds.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
public class DriverDTO {
    private String name;
    private String surname;
    private Date dateOfBirth;
    private short experience;
}
