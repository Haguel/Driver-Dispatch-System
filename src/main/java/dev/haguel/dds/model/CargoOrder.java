package dev.haguel.dds.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cargo_order")
public class CargoOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cargoType;

    @Column(nullable = false)
    private int cargoAmount;

    @Column(nullable = false)
    private int payout;

    @Column(nullable = false)
    private short daysTillComplete;

    @Column
    private short minExperienceRequired;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "cargo_status_id", nullable = false)
    private CargoStatus cargoStatus;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    public CargoOrder(String cargoType, int cargoAmount, int payout, short daysTillComplete, short minExperienceRequired,
                      CargoStatus cargoStatus, Destination destination) {
        this.cargoType = cargoType;
        this.cargoAmount = cargoAmount;
        this.payout = payout;
        this.daysTillComplete = daysTillComplete;
        this.minExperienceRequired = minExperienceRequired;
        this.cargoStatus = cargoStatus;
        this.destination = destination;
    }
}
