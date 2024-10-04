package dev.haguel.dds.dao;

import dev.haguel.dds.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query(value = "SELECT * FROM driver d " +
            "WHERE d.id NOT IN (SELECT co.driver_id FROM cargo_order co WHERE co.driver_id IS NOT NULL) " +
            "AND d.experience >= :experience " +
            "ORDER BY d.experience " +
            "LIMIT 1",
            nativeQuery = true)
    Driver findFreeDriverSuitableForExperience(@Param("experience") short experience);
}
