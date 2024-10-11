package dev.haguel.dds.service;

import dev.haguel.dds.DTO.VehicleDTO;
import dev.haguel.dds.dao.VehicleRepository;
import dev.haguel.dds.exception.VehicleNotFoundException;
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

    public Vehicle createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle(vehicleDTO.getManufacturer(), vehicleDTO.getModel(), vehicleDTO.getPayload());

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) throws VehicleNotFoundException {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with id " + id + " not found"));
    }

    public void setBrokenStatus(Vehicle vehicle) {
        vehicle.setBroken(true);
        vehicleRepository.save(vehicle);
    }

    public void resetBrokenStatus(Vehicle vehicle) {
        vehicle.setBroken(false);
        vehicleRepository.save(vehicle);
    }

    public Vehicle getFreeVehicleByPayload(int cargoAmount) throws VehicleNotFoundException {
        return vehicleRepository.findVehicleSuitableForPayload(cargoAmount)
                .orElseThrow(() -> new VehicleNotFoundException("No vehicle with required payload found"));
    }
}
