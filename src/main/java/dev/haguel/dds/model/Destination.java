package dev.haguel.dds.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"cargoOrders"})
@ToString(exclude = {"cargoOrders"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "destination")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "destination")
    private Set<CargoOrder> cargoOrders;

    public Destination(String country, String city, String address) {
        this.country = country;
        this.city = city;
        this.address = address;
    }
}
