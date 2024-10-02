package dev.haguel.dds.service;

import dev.haguel.dds.DTO.VehicleDTO;
import dev.haguel.dds.dao.VehicleRepository;
import dev.haguel.dds.model.Driver;
import dev.haguel.dds.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public Vehicle getFreeVehicleByPayload(int cargoAmount) {
        return vehicleRepository.findVehicleSuitableForPayload(cargoAmount);
    }

    public Vehicle createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle(vehicleDTO.getManufacturer(), vehicleDTO.getModel(), vehicleDTO.getPayload());

        vehicleRepository.save(vehicle);

        return vehicle;
    }
}
