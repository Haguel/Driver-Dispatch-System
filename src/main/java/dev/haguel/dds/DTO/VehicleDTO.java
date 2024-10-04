package dev.haguel.dds.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class VehicleDTO {
    @NotNull
    private String manufacturer;

    @NotNull
    private String model;

    @NotNull
    private int payload;
}
