package dev.haguel.dds.dao;

import dev.haguel.dds.model.CargoOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoOrderRepository extends JpaRepository<CargoOrder, Long> {
}
