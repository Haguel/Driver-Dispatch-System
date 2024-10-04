package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.DriverDTO;
import dev.haguel.dds.model.Driver;
import dev.haguel.dds.service.DriverService;
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
@RequestMapping(EndPoints.DRIVERS_ENDPOINT)
public class DriverController {
    private final DriverService driverService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Driver> createDriver(@Valid @RequestBody DriverDTO driverDTO) {
        return new ResponseEntity<>(driverService.createDriver(driverDTO), HttpStatus.CREATED);
    }
}
