package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.VehicleDTO;
import dev.haguel.dds.model.Vehicle;
import dev.haguel.dds.service.VehicleService;
import dev.haguel.dds.util.EndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoints.VEHICLES_ENDPOINT)
public class VehicleController {
    private final VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        return new ResponseEntity<>(vehicleService.createVehicle(vehicleDTO), HttpStatus.CREATED);
    }
}
