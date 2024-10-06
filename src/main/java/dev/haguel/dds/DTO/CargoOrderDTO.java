package dev.haguel.dds.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CargoOrderDTO {
    @NotNull
    private String cargoType;

    @NotNull
    private int cargoAmount;

    @NotNull
    private int payout;

    @NotNull
    private short daysToComplete;

    @NotNull
    private short minExperienceRequired;

    @NotNull
    private DestinationDTO destinationDTO;
}
