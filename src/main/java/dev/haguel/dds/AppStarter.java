package dev.haguel.dds;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.factory.CargoOrderFactory;
import dev.haguel.dds.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppStarter {
    @Autowired
    private DDSInitializer ddsInitializer;
    @Autowired
    private CargoService cargoService;

    @Bean
    public ApplicationRunner init() {
        return args -> {
//            ddsInitializer.clearDb();
//            ddsInitializer.initDrivers();
//            ddsInitializer.initVehicles();
//
//            CargoOrderDTO cargoOrderDTO = CargoOrderFactory.createCargoOrder();
//            System.out.println(cargoService.createOrder(cargoOrderDTO));
        };
    }
}
