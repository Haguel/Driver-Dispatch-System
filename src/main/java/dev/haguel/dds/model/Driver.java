package dev.haguel.dds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
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
    private double totalPayouts;

    @Column(nullable = false)
    private Date dateOfBirth;

    // months
    @Column(nullable = false)
    private short experience;

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
