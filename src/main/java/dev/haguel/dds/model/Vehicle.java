package dev.haguel.dds.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"cargoOrder"})
@ToString(exclude = {"cargoOrder"})
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
    private int payload;

    @Column(nullable = false)
    private boolean isBroken;

    @OneToOne(mappedBy = "vehicle")
    private CargoOrder cargoOrder;

    public Vehicle(String manufacturer, String model, int payload) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.payload = payload;
        isBroken = false;
    }
}
