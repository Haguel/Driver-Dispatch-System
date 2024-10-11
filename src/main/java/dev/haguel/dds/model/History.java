package dev.haguel.dds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(nullable = false)
    private String driverName;

    @Column(nullable = false)
    private String driverSurname;

    @Column(nullable = false)
    private String vehicleManufacturer;

    @Column(nullable = false)
    private String vehicleModel;

    @Column(nullable = false)
    private double cargoAmount;

    @Column(nullable = false)
    private double payout;

    @Column(nullable = false)
    private String cargoType;

    @Column(nullable = false)
    private String destinationCity;

    @Column(nullable = false)
    private String destinationCountry;

    @Column(nullable = false)
    private String destinationAddress;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public History(String driverName, String driverSurname, String vehicleManufacturer, String vehicleModel,
                   double cargoAmount, double payout, String cargoType, String destinationCity, String destinationCountry, String destinationAddress) {
        this.driverName = driverName;
        this.driverSurname = driverSurname;
        this.vehicleManufacturer = vehicleManufacturer;
        this.vehicleModel = vehicleModel;
        this.cargoAmount = cargoAmount;
        this.payout = payout;
        this.cargoType = cargoType;
        this.destinationCity = destinationCity;
        this.destinationCountry = destinationCountry;
        this.destinationAddress = destinationAddress;
    }
}
