package dev.haguel.dds.dao;

import dev.haguel.dds.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT * FROM vehicle v " +
            "WHERE v.id NOT IN (SELECT co.vehicle_id FROM cargo_order co WHERE co.vehicle_id IS NOT NULL)" +
            "AND v.is_broken = false " +
            "AND v.payload > :payload " +
            "ORDER BY v.payload " +
            "LIMIT 1",
            nativeQuery = true)
    Vehicle findVehicleSuitableForPayload(@Param("payload") int payload);
}
