package dev.haguel.dds.service;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.dao.CargoOrderRepository;
import dev.haguel.dds.dao.CargoStatusRepository;
import dev.haguel.dds.exception.DriverNotFoundException;
import dev.haguel.dds.exception.VehicleNotFoundException;
import dev.haguel.dds.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CargoService {
    private final CargoOrderRepository cargoOrderRepository;
    private final CargoStatusRepository cargoStatusRepository;
    private final DestinationService destinationService;
    private final DriverService driverService;
    private final VehicleService vehicleService;

    public CargoOrder createOrder(CargoOrderDTO cargoOrderDTO)
            throws DriverNotFoundException, VehicleNotFoundException {
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
        if(driver == null) throw new DriverNotFoundException("No driver with required experience found");
        Vehicle vehicle = vehicleService.getFreeVehicleByPayload(cargoOrderDTO.getCargoAmount());
        if(vehicle == null) throw new VehicleNotFoundException("No vehicle with required payload found");
        CargoStatus inProgressStatus = cargoStatusRepository.findInProgressStatus();

        cargoOrder.setCargoStatus(inProgressStatus);
        cargoOrder.setDriver(driver);
        cargoOrder.setVehicle(vehicle);

        return cargoOrderRepository.save(cargoOrder);
    }
}
