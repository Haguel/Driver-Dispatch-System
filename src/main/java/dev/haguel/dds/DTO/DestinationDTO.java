package dev.haguel.dds.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DestinationDTO {
    private String country;
    private String city;
    private String address;
}
