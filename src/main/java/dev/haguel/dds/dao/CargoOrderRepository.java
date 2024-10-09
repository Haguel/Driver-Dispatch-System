package dev.haguel.dds.dao;

import dev.haguel.dds.model.CargoOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CargoOrderRepository extends JpaRepository<CargoOrder, Long> {
    @Query(value = "SELECT * FROM cargo_order " +
            "WHERE cargo_status_id = (SELECT id FROM cargo_status WHERE status = :status)",
            nativeQuery = true)
    List<CargoOrder> findByCargoStatus(String status);
}
