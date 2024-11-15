package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.DTO.DestinationDTO;
import dev.haguel.dds.exception.CargoOrderNotFoundException;
import dev.haguel.dds.exception.DriverNotFoundException;
import dev.haguel.dds.exception.VehicleNotFoundException;
import dev.haguel.dds.model.CargoOrder;
import dev.haguel.dds.service.CargoOrderService;
import dev.haguel.dds.util.EndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CargoController {
    private final CargoOrderService cargoOrderService;

    @GetMapping(value = EndPoints.GET_CARGO_ORDERS)
    public String listCargoOrders(Model model) {
        List<CargoOrder> orders = cargoOrderService.getOrders();

        EndPoints.setMainMenuEndpoints(model);
        model.addAttribute("orders", orders);
        model.addAttribute("getCargoOrderEndpoint", EndPoints.GET_CARGO_ORDER);

        return "cargoOrders";
    }

    @GetMapping(value = EndPoints.GET_CARGO_ORDER)
    public String viewCargoOrder(@PathVariable Long id, Model model) {
        EndPoints.setMainMenuEndpoints(model);
        try {
            CargoOrder order = cargoOrderService.getOrderById(id);
            model.addAttribute("order", order);
            model.addAttribute("getCargoOrdersEndpoint", EndPoints.GET_CARGO_ORDERS);

            return "cargoOrder";
        } catch (CargoOrderNotFoundException exception) {
            model.addAttribute("error", "Something went wrong while trying to get the order. Please try again later.");
        }

        return listCargoOrders(model);
    }

    @GetMapping(value = EndPoints.CREATE_CARGO_ORDER_FORM)
    public String createCargoOrderForm(Model model) {
        CargoOrderDTO cargoOrderDTO = new CargoOrderDTO();
        cargoOrderDTO.setDestinationDTO(new DestinationDTO());

        EndPoints.setMainMenuEndpoints(model);
        model.addAttribute("cargoOrderDTO", cargoOrderDTO);
        model.addAttribute("createOrderEndpoint", EndPoints.CREATE_CARGO_ORDER);

        return "createCargoOrder";
    }

    @PostMapping(value = EndPoints.CREATE_CARGO_ORDER)
    public String createCargoOrder(@ModelAttribute @Valid CargoOrderDTO cargoOrderDTO, Model model) {
        try {
            CargoOrder cargoOrder = cargoOrderService.createOrder(cargoOrderDTO);

            return "redirect:" + EndPoints.GET_CARGO_ORDER.replace("{id}", String.valueOf(cargoOrder.getId()));
        } catch (DriverNotFoundException exception) {
            model.addAttribute("error", "Appropriate driver not found. Please try again later or set another requirements.");
        } catch (VehicleNotFoundException exception) {
            model.addAttribute("error", "Appropriate vehicle not found. Please try again later or set another requirements..");
        }

        return createCargoOrderForm(model);
    }
}
