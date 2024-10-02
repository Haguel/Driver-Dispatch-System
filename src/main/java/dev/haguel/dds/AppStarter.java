package dev.haguel.dds;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.factory.CargoOrderFactory;
import dev.haguel.dds.service.CargoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppStarter {
    @Autowired
    private DDSInitializer ddsInitializer;
    @Autowired
    private CargoOrderService cargoOrderService;

    @Bean
    public ApplicationRunner init() {
        return args -> {
            ddsInitializer.clearDb();
            ddsInitializer.initDrivers();
            ddsInitializer.initVehicles();

            CargoOrderDTO cargoOrderDTO = CargoOrderFactory.createCargoOrder();
            System.out.println(cargoOrderService.createOrder(cargoOrderDTO));
        };
    }
}
