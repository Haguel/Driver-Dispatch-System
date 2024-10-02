package dev.haguel.dds.service;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.dao.CargoOrderRepository;
import dev.haguel.dds.dao.CargoStatusRepository;
import dev.haguel.dds.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CargoOrderService {
    private final CargoOrderRepository cargoOrderRepository;
    private final CargoStatusRepository cargoStatusRepository;
    private final DestinationService destinationService;
    private final DriverService driverService;
    private final VehicleService vehicleService;

    public CargoOrder createOrder(CargoOrderDTO cargoOrderDTO) throws Exception {
        CargoStatus notStartedStatus = cargoStatusRepository.findNotStartedStatus();
        Destination destination = destinationService.createDestination(cargoOrderDTO.getDestinationDTO());
        CargoOrder cargoOrder = new CargoOrder(
                cargoOrderDTO.getCargoType(),
                cargoOrderDTO.getCargoAmount(),
                cargoOrderDTO.getPayout(),
                cargoOrderDTO.getDaysToComplete(),
                cargoOrderDTO.getMinExperienceRequired(),
                notStartedStatus,
                destination
        );

        cargoOrderRepository.save(cargoOrder);

        Driver driver = driverService.getFreeDriverByExperience(cargoOrderDTO.getMinExperienceRequired());
        Vehicle vehicle = vehicleService.getFreeVehicleByPayload(cargoOrderDTO.getCargoAmount());
        CargoStatus inProgressStatus = cargoStatusRepository.findInProgressStatus();

        if(driver == null || vehicle == null) {
            throw new Exception("Can't find free driver or vehicle for this order.");
        }

        cargoOrder.setCargoStatus(inProgressStatus);
        cargoOrder.setDriver(driver);
        cargoOrder.setVehicle(vehicle);

        cargoOrderRepository.save(cargoOrder);

        return cargoOrder;
    }
}
