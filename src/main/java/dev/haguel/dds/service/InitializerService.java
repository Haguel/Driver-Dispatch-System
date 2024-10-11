package dev.haguel.dds.service;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.DTO.DriverDTO;
import dev.haguel.dds.DTO.VehicleDTO;
import dev.haguel.dds.exception.DriverNotFoundException;
import dev.haguel.dds.exception.VehicleNotFoundException;
import dev.haguel.dds.factory.CargoOrderDTOFactory;
import dev.haguel.dds.factory.DriverDTOFactory;
import dev.haguel.dds.factory.VehicleDTOFactory;
import dev.haguel.dds.model.CargoOrder;
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
    private final CargoOrderService cargoOrderService;

    public void clearDb() {
        entityManager.createNativeQuery("TRUNCATE TABLE cargo_order, destination, vehicle, driver CASCADE").executeUpdate();
    }

    public void initDrivers() {
        for(int i = 0; i < 100; i++) {
            DriverDTO driverDTO = DriverDTOFactory.createDriver();

            driverService.createDriver(driverDTO);
        }
    }

    public void initVehicles() {
        for(int i = 0; i < 100; i++) {
            VehicleDTO vehicleDTO = VehicleDTOFactory.createVehicle();

            vehicleService.createVehicle(vehicleDTO);
        }
    }

    public void initCargoOrders() {
        for(int i = 0; i < 100; i++) {
            CargoOrderDTO cargoOrderDTO = CargoOrderDTOFactory.createCargoOrder();

            CargoOrder cargoOrder = new CargoOrder();
            try {
                cargoOrder = cargoOrderService.createOrder(cargoOrderDTO);
            } catch (VehicleNotFoundException | DriverNotFoundException exception) {
                cargoOrderService.deleteOrder(cargoOrder);
            }
        }
    }
}
