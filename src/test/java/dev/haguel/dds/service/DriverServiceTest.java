package dev.haguel.dds.service;

import dev.haguel.dds.dao.DriverRepository;
import dev.haguel.dds.exception.DriverNotFoundException;
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
class  DriverServiceTest {
    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    @Test
    @DisplayName("Should throw exception when unable to find driver by id")
    void should_throwException_whenUnableToFindById() {
        Random random = new Random();
        long id = random.nextLong();
        Mockito.when(driverRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DriverNotFoundException.class, () -> driverService.getDriversById(id));
    }

    @Test
    @DisplayName("Should throw exception when unable to find driver by experience")
    void should_throwException_whenUnableToFindDriverByExperience() {
        Random random = new Random();
        short id = (short) random.nextInt();

        Mockito.when(driverRepository.findFreeDriverSuitableForExperience(id)).thenReturn(Optional.empty());

        assertThrows(DriverNotFoundException.class, () -> driverService.getFreeDriverByExperience(id));
    }
}