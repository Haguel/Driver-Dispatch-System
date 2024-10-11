package dev.haguel.dds.service;

import dev.haguel.dds.dao.CargoOrderRepository;
import dev.haguel.dds.exception.CargoOrderNotFoundException;
import dev.haguel.dds.exception.DriverNotFoundException;
import dev.haguel.dds.exception.VehicleNotFoundException;
import dev.haguel.dds.model.CargoOrder;
import dev.haguel.dds.model.Driver;
import dev.haguel.dds.model.Vehicle;
import dev.haguel.dds.test_factory.CargoOrderDTOFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CargoOrderServiceTest {
    @Mock
    private CargoOrderRepository cargoOrderRepository;

    @Mock
    private CargoStatusService cargoStatusService;

    @Mock
    private DestinationService destinationService;

    @Mock
    private DriverService driverService;

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private CargoOrderService cargoOrderService;

    @Test
    @DisplayName("Should create and return order with given valid DTO")
    void should_creteOrder_when_validDto() {
        assertDoesNotThrow(() -> cargoOrderService.createOrder(CargoOrderDTOFactory.createRandomCargoOrderDTO()));
        assertDoesNotThrow(() -> cargoOrderService.createOrder(CargoOrderDTOFactory.createRandomCargoOrderDTO()));
        assertDoesNotThrow(() -> cargoOrderService.createOrder(CargoOrderDTOFactory.createRandomCargoOrderDTO()));

        Mockito.verify(cargoOrderRepository, Mockito.times(6))
                .save(Mockito.any(CargoOrder.class));
    }

    @Test
    @DisplayName("Should throw exception when trying create order but no driver with minimum required experience found")
    void should_throwException_when_unableToFindDriver() throws DriverNotFoundException {
        Mockito.when(driverService.getFreeDriverByExperience(Mockito.anyShort()))
                .thenThrow(DriverNotFoundException.class);

        assertThrows(DriverNotFoundException.class,
                () -> cargoOrderService.createOrder(CargoOrderDTOFactory.createRandomCargoOrderDTO()));
        assertThrows(DriverNotFoundException.class,
                () -> cargoOrderService.createOrder(CargoOrderDTOFactory.createRandomCargoOrderDTO()));
        assertThrows(DriverNotFoundException.class,
                () -> cargoOrderService.createOrder(CargoOrderDTOFactory.createRandomCargoOrderDTO()));

        Mockito.verify(cargoOrderRepository, Mockito.times(3))
                .save(Mockito.any(CargoOrder.class));
    }

    @Test
    @DisplayName("Should throw exception when trying create order but no vehicle with minimum required payload found")
    void should_throwException_when_unableToFindVehicle() throws VehicleNotFoundException {
        Mockito.when(vehicleService.getFreeVehicleByPayload(Mockito.anyInt()))
                .thenThrow(VehicleNotFoundException.class);
        Mockito.verify(cargoOrderRepository, Mockito.times(0))
                .save(Mockito.any(CargoOrder.class));

        assertThrows(VehicleNotFoundException.class,
                () -> cargoOrderService.createOrder(CargoOrderDTOFactory.createRandomCargoOrderDTO()));
        assertThrows(VehicleNotFoundException.class,
                () -> cargoOrderService.createOrder(CargoOrderDTOFactory.createRandomCargoOrderDTO()));
        assertThrows(VehicleNotFoundException.class,
                () -> cargoOrderService.createOrder(CargoOrderDTOFactory.createRandomCargoOrderDTO()));

        Mockito.verify(cargoOrderRepository, Mockito.times(3))
                .save(Mockito.any(CargoOrder.class));
    }

    @Test
    @DisplayName("Should throw exception when finding order by id but unable to find it")
    void should_throwException_when__unableToFindById() {
        Random random = new Random();
        Long id = random.nextLong();

        Mockito.when(cargoOrderRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CargoOrderNotFoundException.class, () -> cargoOrderService.getOrderById(id));
    }
}