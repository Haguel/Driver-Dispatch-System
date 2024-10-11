package dev.haguel.dds.service;

import dev.haguel.dds.DTO.HistoryDTO;
import dev.haguel.dds.dao.HistoryRepository;
import dev.haguel.dds.model.History;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class HistoryService {
    private final HistoryRepository historyRepository;

    public void save(HistoryDTO historyDTO) {
        History history = new History(
                historyDTO.getDriverName(),
                historyDTO.getDriverSurname(),
                historyDTO.getVehicleManufacturer(),
                historyDTO.getVehicleModel(),
                historyDTO.getCargoAmount(),
                historyDTO.getPayout(),
                historyDTO.getCargoType(),
                historyDTO.getDestinationCity(),
                historyDTO.getDestinationCountry(),
                historyDTO.getDestinationAddress()
        );

        historyRepository.save(history);
    }

    public List<History> getAll() {
        return historyRepository.findAll();
    }
}
