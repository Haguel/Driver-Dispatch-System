package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.exception.CargoOrderNotFoundException;
import dev.haguel.dds.exception.DriverNotFoundException;
import dev.haguel.dds.exception.VehicleNotFoundException;
import dev.haguel.dds.model.CargoOrder;
import dev.haguel.dds.service.CargoService;
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
public class CargoController {
    private final CargoService cargoService;

    @RequestMapping(value = EndPoints.CREATE_CARGO, method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@Valid @RequestBody CargoOrderDTO cargoOrderDTO) {
        try {
            CargoOrder cargoOrder = cargoService.createOrder(cargoOrderDTO);

            return new ResponseEntity<>(cargoOrder, HttpStatus.CREATED);
        } catch (DriverNotFoundException | VehicleNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = {EndPoints.GET_ALL_ORDERS, EndPoints.GET_ORDER}, method = RequestMethod.GET)
    public ResponseEntity<?> getOrders(@PathVariable(name = "id") Optional<Long> id) {
        try {
            if(id.isPresent()) {
                return new ResponseEntity<>(cargoService.getOrderById(id.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(cargoService.getOrders(), HttpStatus.OK);
            }
        } catch (CargoOrderNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
