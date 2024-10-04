package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.VehicleDTO;
import dev.haguel.dds.exception.VehicleNotFoundException;
import dev.haguel.dds.model.CargoOrder;
import dev.haguel.dds.model.Vehicle;
import dev.haguel.dds.service.CargoService;
import dev.haguel.dds.service.VehicleService;
import dev.haguel.dds.util.EndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoints.VERSION_1)
public class VehicleController {
    private final VehicleService vehicleService;
    public final CargoService cargoService;

    @RequestMapping(value = EndPoints.CREATE_VEHICLE, method = RequestMethod.POST)
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        return new ResponseEntity<>(vehicleService.createVehicle(vehicleDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = {EndPoints.GET_ALL_VEHICLES, EndPoints.GET_VEHICLE}, method = RequestMethod.GET)
    public ResponseEntity<?> getVehicles(@PathVariable(name = "id") Optional<Long> id) {
        try {
            if(id.isPresent()) {
                return new ResponseEntity<>(vehicleService.getVehicleById(id.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(vehicleService.getVehicles(), HttpStatus.OK);
            }
        } catch (VehicleNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = EndPoints.VEHICLE_BROKEN_STATUS, method = RequestMethod.POST)
    public ResponseEntity<?> handleBrokenStatus(@PathVariable(name = "id") Long id) {
        try {
            Vehicle vehicle = vehicleService.getVehicleById(id);
            CargoOrder cargoOrder = vehicle.getCargoOrder();

            if(vehicle.isBroken()) {
                vehicleService.resetBreakStatus(vehicle);
                if(cargoOrder != null) cargoService.resumeOrder(cargoOrder);
            } else {
                vehicleService.setBrokenStatus(vehicle);
                if(cargoOrder != null) cargoService.pauseOrder(cargoOrder);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (VehicleNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
