package dev.haguel.dds.service;

import dev.haguel.dds.dao.CargoStatusRepository;
import dev.haguel.dds.model.CargoStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CargoStatusService {
    private final CargoStatusRepository cargoStatusRepository;

    public CargoStatus getNotStartedStatus() {
        return cargoStatusRepository.findNotStartedStatus();
    }

    public CargoStatus getInProgressStatus() {
        return cargoStatusRepository.findInProgressStatus();
    }

    public CargoStatus getPausedStatus() {
        return cargoStatusRepository.findPausedStatus();
    }

    public CargoStatus getCompletedStatus() {
        return cargoStatusRepository.findCompletedStatus();
    }
}
