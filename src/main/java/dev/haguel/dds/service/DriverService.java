package dev.haguel.dds.service;

import dev.haguel.dds.DTO.DriverDTO;
import dev.haguel.dds.dao.DriverRepository;
import dev.haguel.dds.exception.DriverNotFoundException;
import dev.haguel.dds.model.Driver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class DriverService {
    private final DriverRepository driverRepository;

    public Driver createDriver(DriverDTO driverDTO) {
        Driver driver = new Driver(driverDTO.getName(), driverDTO.getSurname(),
                driverDTO.getDateOfBirth(), driverDTO.getExperience());

        return driverRepository.save(driver);
    }

    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriversById(Long id) throws DriverNotFoundException {
        return driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException("Driver with id " + id + " not found"));
    }

    public Driver getFreeDriverByExperience(short minExperienceRequired) {
        return driverRepository.findFreeDriverSuitableForExperience(minExperienceRequired);
    }
}
