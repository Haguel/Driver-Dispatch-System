package dev.haguel.dds.service;

import dev.haguel.dds.DTO.DriverDTO;
import dev.haguel.dds.DTO.VehicleDTO;
import dev.haguel.dds.factory.DriverFactory;
import dev.haguel.dds.factory.VehicleFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class InitializerService {
    @PersistenceContext
    private final EntityManager entityManager;
    private final DriverService driverService;
    private final VehicleService vehicleService;

    public void clearDb() {
        entityManager.createNativeQuery("TRUNCATE TABLE cargo_order, destination, vehicle, driver CASCADE").executeUpdate();
    }

    public void initDrivers() {
        for(int i = 0; i < 100; i++) {
            DriverDTO driverDTO = DriverFactory.createDriver();

            driverService.createDriver(driverDTO);
        }
    }

    public void initVehicles() {
        for(int i = 0; i < 100; i++) {
            VehicleDTO vehicleDTO = VehicleFactory.createVehicle();

            vehicleService.createVehicle(vehicleDTO);
        }
    }
}
