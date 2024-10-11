package dev.haguel.dds.component;

import dev.haguel.dds.model.CargoOrder;
import dev.haguel.dds.model.CargoStatus;
import dev.haguel.dds.service.CargoOrderService;
import dev.haguel.dds.service.CargoStatusService;
import dev.haguel.dds.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Component
public class CargoDeliverySimulator {
    private final CargoOrderService cargoOrderService;
    private final VehicleService vehicleService;
    private final CargoStatusService cargoStatusService;
    private final Random random = new Random();

    // Executes every minute
    @Scheduled(fixedDelay = 60000)
    public void simulateDayPass() {
        CargoStatus inProgressStatus = cargoStatusService.getInProgressStatus();
        CargoStatus pausedStatus = cargoStatusService.getPausedStatus();

        List<CargoOrder> inProgressOrders = cargoOrderService.getOrdersByStatus(inProgressStatus);
        List<CargoOrder> pausedOrders = cargoOrderService.getOrdersByStatus(pausedStatus);

        inProgressOrders.forEach(cargoOrder -> {
            if(random.nextInt(100) < 10) { // 10% chance of completion
                vehicleService.setBrokenStatus(cargoOrder.getVehicle());
                cargoOrderService.pauseOrder(cargoOrder);
            } else {
                cargoOrderService.passDay(cargoOrder);
            }
        });

        pausedOrders.forEach(cargoOrder -> {
            if(random.nextInt(100) < 70) { // 70% chance of completion
                vehicleService.resetBrokenStatus(cargoOrder.getVehicle());
                cargoOrderService.resumeOrder(cargoOrder);
            }
        });
    }
}
