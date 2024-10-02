package dev.haguel.dds.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class VehicleDTO {
    private String manufacturer;
    private String model;
    private int payload;
}
