package dev.haguel.dds.service;

import dev.haguel.dds.dao.CargoOrderRepository;
import dev.haguel.dds.exception.CargoOrderNotFoundException;
import dev.haguel.dds.exception.DriverNotFoundException;
import dev.haguel.dds.exception.VehicleNotFoundException;
import dev.haguel.dds.model.CargoOrder;
import dev.haguel.dds.test_factory.TestCargoOrderDTOFactory;
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
        Mockito.when(cargoOrderRepository.save(Mockito.any(CargoOrder.class)))
                .thenReturn(new CargoOrder());

        int testAmount = 10;
        for(int i = 0; i < testAmount; i++) {
            assertDoesNotThrow(() -> cargoOrderService.createOrder(TestCargoOrderDTOFactory.createRandomCargoOrderDTO()));
        }

        Mockito.verify(cargoStatusService, Mockito.times(1 * testAmount))
                .getInProgressStatus();
        Mockito.verify(cargoOrderRepository, Mockito.times(4 * testAmount))
                .save(Mockito.any(CargoOrder.class));
    }

    @Test
    @DisplayName("Should set not started status when trying create order but no driver with minimum required experience found")
    void should_setNotStartedStatus_when_unableToFindDriver() throws DriverNotFoundException {
        Mockito.when(driverService.getFreeDriverByExperience(Mockito.anyShort()))
                .thenThrow(DriverNotFoundException.class);
        Mockito.when(cargoOrderRepository.save(Mockito.any(CargoOrder.class)))
                .thenReturn(new CargoOrder());

        assertDoesNotThrow(() -> cargoOrderService.createOrder(TestCargoOrderDTOFactory.createRandomCargoOrderDTO()));

        Mockito.verify(cargoStatusService, Mockito.times(0))
                .getInProgressStatus();
        Mockito.verify(cargoStatusService, Mockito.times(1))
                .getNotStartedStatus();
    }

    @Test
    @DisplayName("Should set not started status when trying create order but no vehicle with minimum required payload found")
    void should_setNotStartedStatus_when_unableToFindVehicle() throws VehicleNotFoundException, DriverNotFoundException {
        Mockito.when(driverService.getFreeDriverByExperience(Mockito.anyShort()))
                .thenReturn(null);
        Mockito.when(vehicleService.getFreeVehicleByPayload(Mockito.anyInt()))
                .thenThrow(VehicleNotFoundException.class);
        Mockito.when(cargoOrderRepository.save(Mockito.any(CargoOrder.class)))
                .thenReturn(new CargoOrder());

        assertDoesNotThrow(() -> cargoOrderService.createOrder(TestCargoOrderDTOFactory.createRandomCargoOrderDTO()));

        Mockito.verify(cargoStatusService, Mockito.times(0))
                .getInProgressStatus();
        Mockito.verify(cargoStatusService, Mockito.times(1))
                .getNotStartedStatus();
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