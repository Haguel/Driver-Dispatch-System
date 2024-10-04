package dev.haguel.dds.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.haguel.dds.serializer.CargoOrderSerializer;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@EqualsAndHashCode(exclude = {"cargoOrder"})
@ToString(exclude = {"cargoOrder"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private int totalPayouts;

    @Column(nullable = false)
    private Date dateOfBirth;

    // months
    @Column(nullable = false)
    private short experience;

    @JsonSerialize(using = CargoOrderSerializer.class)
    @OneToOne(mappedBy = "driver")
    private CargoOrder cargoOrder;

    public Driver(String name, String surname, Date dateOfBirth, short experience) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.experience = experience;
        this.totalPayouts = 0;
    }
}
