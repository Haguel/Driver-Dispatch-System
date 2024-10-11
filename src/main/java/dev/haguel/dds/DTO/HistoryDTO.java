package dev.haguel.dds.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class HistoryDTO {
    private String driverName;
    private String driverSurname;
    private String vehicleManufacturer;
    private String vehicleModel;
    private double cargoAmount;
    private double payout;
    private String cargoType;
    private String destinationCity;
    private String destinationCountry;
    private String destinationAddress;
}
