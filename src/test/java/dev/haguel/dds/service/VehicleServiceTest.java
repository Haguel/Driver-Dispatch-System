package dev.haguel.dds.service;

import dev.haguel.dds.dao.VehicleRepository;
import dev.haguel.dds.exception.VehicleNotFoundException;
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
class VehicleServiceTest {
    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    @DisplayName("Should throw exception when unable to find vehicle by id")
    void should_throwException_whenUnableToFindById() {
        Random random = new Random();
        long id = random.nextLong();
        Mockito.when(vehicleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class, () -> vehicleService.getVehicleById(id));
    }

    @Test
    @DisplayName("Should throw exception when unable to find vehicle by payload")
    void should_throwException_whenUnableToFindVehicleByPayload() {
        Random random = new Random();
        int payload = random.nextInt();

        Mockito.when(vehicleRepository.findVehicleSuitableForPayload(payload)).thenReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class, () -> vehicleService.getFreeVehicleByPayload(payload));
    }
}