package dev.haguel.dds.dao;

import dev.haguel.dds.model.CargoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CargoStatusRepository extends JpaRepository<CargoStatus, Long> {
    @Query("SELECT cs FROM CargoStatus cs WHERE cs.status = 'not started'")
    CargoStatus findNotStartedStatus();

    @Query("SELECT cs FROM CargoStatus cs WHERE cs.status = 'in progress'")
    CargoStatus findInProgressStatus();

    @Query("SELECT cs FROM CargoStatus cs WHERE cs.status = 'paused'")
    CargoStatus findPausedStatus();

    @Query("SELECT cs FROM CargoStatus cs WHERE cs.status = 'completed'")
    CargoStatus findCompleted();

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
