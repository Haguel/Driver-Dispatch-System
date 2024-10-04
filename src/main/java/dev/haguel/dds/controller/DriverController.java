package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.DriverDTO;
import dev.haguel.dds.exception.DriverNotFoundException;
import dev.haguel.dds.model.Driver;
import dev.haguel.dds.service.DriverService;
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
public class DriverController {
    private final DriverService driverService;

    @RequestMapping(value = EndPoints.CREATE_DRIVER, method = RequestMethod.POST)
    public ResponseEntity<Driver> createDriver(@Valid @RequestBody DriverDTO driverDTO) {
        return new ResponseEntity<>(driverService.createDriver(driverDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = {EndPoints.GET_ALL_DRIVERS, EndPoints.GET_DRIVER}, method = RequestMethod.GET)
    public ResponseEntity<?> getDrivers(@PathVariable(name = "id") Optional<Long> id) {
        try {
            if(id.isPresent()) {
                return new ResponseEntity<>(driverService.getDriversById(id.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(driverService.getDrivers(), HttpStatus.OK);
            }
        } catch (DriverNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
