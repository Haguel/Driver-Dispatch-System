package dev.haguel.dds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_name", nullable = false)
    private String driverName;

    @Column(name = "driver_surname", nullable = false)
    private String driverSurname;

    @Column(name = "vehicle_manufacturer", nullable = false)
    private String vehicleManufacturer;

    @Column(name = "vehicle_model", nullable = false)
    private String vehicleModel;

    @Column(name = "cargo_amount", nullable = false)
    private double cargoAmount;

    @Column(name = "payout", nullable = false)
    private double payout;

    @Column(name = "cargo_type", nullable = false)
    private String cargoType;

    @Column(name = "destination_city", nullable = false)
    private String destinationCity;

    @Column(name = "destination_country", nullable = false)
    private String destinationCountry;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;
}
