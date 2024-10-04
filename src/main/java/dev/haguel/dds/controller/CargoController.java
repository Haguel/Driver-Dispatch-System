package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.exception.DriverNotFoundException;
import dev.haguel.dds.exception.VehicleNotFoundException;
import dev.haguel.dds.model.CargoOrder;
import dev.haguel.dds.service.CargoService;
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
@RequestMapping(EndPoints.CARGO_ENDPOINT)
public class CargoController {
    private final CargoService cargoService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@Valid @RequestBody CargoOrderDTO cargoOrderDTO) {
        try {
            CargoOrder cargoOrder = cargoService.createOrder(cargoOrderDTO);

            return new ResponseEntity<>(cargoOrder, HttpStatus.CREATED);
        } catch (DriverNotFoundException | VehicleNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
