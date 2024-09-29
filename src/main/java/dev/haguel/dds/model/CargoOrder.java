package dev.haguel.dds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private double cargoAmount;

    @Column(nullable = false)
    private double payout;

    @Column(nullable = false)
    private short daysTillComplete;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "cargo_status_id")
    private CargoStatus cargoStatus;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;
}
