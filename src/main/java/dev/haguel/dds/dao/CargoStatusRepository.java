package dev.haguel.dds.dao;

import dev.haguel.dds.model.CargoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CargoStatusRepository extends JpaRepository<CargoStatus, Long> {
    Optional<CargoStatus> findByStatus(String status);

    @Override
    default <S extends CargoStatus> S save(S entity) {
        throw new UnsupportedOperationException("Save operation is not supported for CargoStatus");
    }

    @Override
    default <S extends CargoStatus> List<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Save operation is not supported for CargoStatus");
    }

    @Override
    default void deleteById(Long id) {
        throw new UnsupportedOperationException("Delete operation is not supported for CargoStatus");
    }

    @Override
    default void delete(CargoStatus entity) {
        throw new UnsupportedOperationException("Delete operation is not supported for CargoStatus");
    }
}
