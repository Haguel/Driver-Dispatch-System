package dev.haguel.dds.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CargoOrderDTO {
    private String cargoType;
    private int cargoAmount;
    private int payout;
    private short daysToComplete;
    private short minExperienceRequired;
    private DestinationDTO destinationDTO;
}
