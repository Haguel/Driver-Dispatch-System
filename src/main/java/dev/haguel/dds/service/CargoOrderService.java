package dev.haguel.dds.service;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.DTO.HistoryDTO;
import dev.haguel.dds.dao.CargoOrderRepository;
import dev.haguel.dds.dao.CargoStatusRepository;
import dev.haguel.dds.exception.CargoOrderNotFoundException;
import dev.haguel.dds.exception.DriverNotFoundException;
import dev.haguel.dds.exception.VehicleNotFoundException;
import dev.haguel.dds.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CargoOrderService {
    private final CargoOrderRepository cargoOrderRepository;
    private final CargoStatusService cargoStatusService;
    private final DestinationService destinationService;
    private final DriverService driverService;
    private final VehicleService vehicleService;
    private final HistoryService historyService;

    private void completeOrder(CargoOrder cargoOrder) {
        CargoStatus completedStatus = cargoStatusService.getCompletedStatus();

        Driver driver = cargoOrder.getDriver();
        Vehicle vehicle = cargoOrder.getVehicle();
        Destination destination = cargoOrder.getDestination();

        driverService.payout(driver, cargoOrder.getPayout());

        HistoryDTO historyDTO = HistoryDTO.builder()
                .driverName(driver.getName())
                .driverSurname(driver.getSurname())
                .vehicleManufacturer(vehicle.getManufacturer())
                .vehicleModel(vehicle.getModel())
                .cargoAmount(cargoOrder.getCargoAmount())
                .payout(cargoOrder.getPayout())
                .cargoType(cargoOrder.getCargoType())
                .destinationCity(destination.getCity())
                .destinationCountry(destination.getCountry())
                .destinationAddress(destination.getAddress())
                .build();

        cargoOrder.setCargoStatus(completedStatus);
        cargoOrder.setDriver(null);
        cargoOrder.setVehicle(null);

        deleteOrder(cargoOrder);
        historyService.save(historyDTO);
    }

    public CargoOrder createOrder(CargoOrderDTO cargoOrderDTO)
            throws DriverNotFoundException, VehicleNotFoundException {
        CargoStatus notStartedStatus = cargoStatusService.getNotStartedStatus();
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
        CargoStatus inProgressStatus = cargoStatusService.getInProgressStatus();

        cargoOrder.setCargoStatus(inProgressStatus);
        cargoOrder.setDriver(driver);
        cargoOrder.setVehicle(vehicle);



        return cargoOrderRepository.save(cargoOrder);
    }

    public List<CargoOrder> getOrders() {
        return cargoOrderRepository.findAll();
    }

    public List<CargoOrder> getOrdersByStatus(CargoStatus status) {
        return cargoOrderRepository.findByCargoStatus(status.getStatus());
    }

    public CargoOrder getOrderById(Long id) throws CargoOrderNotFoundException {
        return cargoOrderRepository.findById(id)
                .orElseThrow(() -> new CargoOrderNotFoundException("Cargo order with id " + id + " not found"));
    }

    public void deleteOrder(CargoOrder cargoOrder) {
        cargoOrderRepository.delete(cargoOrder);
    }


    public void pauseOrder(CargoOrder cargoOrder) {
        CargoStatus pausedStatus = cargoStatusService.getPausedStatus();
        cargoOrder.setCargoStatus(pausedStatus);

        cargoOrderRepository.save(cargoOrder);
    }

    public void resumeOrder(CargoOrder cargoOrder) {
        CargoStatus inProgressStatus = cargoStatusService.getInProgressStatus();
        cargoOrder.setCargoStatus(inProgressStatus);

        cargoOrderRepository.save(cargoOrder);
    }

    public void passDay(CargoOrder cargoOrder) {
        short daysToComplete = cargoOrder.getDaysTillComplete();
        daysToComplete--;
        cargoOrder.setDaysTillComplete(daysToComplete);

        if(daysToComplete == 0) {
            completeOrder(cargoOrder);
        } else {
            cargoOrderRepository.save(cargoOrder);
        }
    }
}
