package dev.haguel.dds.component;

import dev.haguel.dds.model.CargoOrder;
import dev.haguel.dds.model.CargoStatus;
import dev.haguel.dds.service.CargoOrderService;
import dev.haguel.dds.service.CargoStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CargoDeliverySimulator {
    private final CargoOrderService cargoOrderService;
    private final CargoStatusService cargoStatusService;

    // Executes every minute
    @Scheduled(fixedDelay = 60000)
    public void simulateDayPass() {
        CargoStatus inProgressStatus = cargoStatusService.getInProgressStatus();
        List<CargoOrder> cargoOrder = cargoOrderService.getOrdersByStatus(inProgressStatus);

        cargoOrder.forEach(cargoOrderService::passDay);
    }
}
