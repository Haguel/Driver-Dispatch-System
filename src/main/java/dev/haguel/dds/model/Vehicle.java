package dev.haguel.dds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private double payload;

    @Column(nullable = false)
    private boolean isBroken;

    @OneToOne(mappedBy = "vehicle")
    private CargoOrder cargoOrder;

    public Vehicle(String manufacturer, String model, double payload) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.payload = payload;
        isBroken = false;
    }
}
