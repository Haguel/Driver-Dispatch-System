package dev.haguel.dds.factory;

import dev.haguel.dds.DTO.VehicleDTO;

import java.util.Random;

public class VehicleFactory {
    private static final String[][] VEHICLES = {
            {"Toyota", "Corolla", "Camry", "Highlander"},
            {"Ford", "F-150", "Mustang", "Explorer"},
            {"Tesla", "Model S", "Model X", "Model 3"},
            {"BMW", "X5", "M3", "i8"}
    };

    private static final Random random = new Random();

    public static VehicleDTO createVehicle() {
        int randomManufacturerIndex = random.nextInt(VEHICLES.length);
        String manufacturer = VEHICLES[randomManufacturerIndex][0];
        String model = VEHICLES[randomManufacturerIndex][random.nextInt(1, VEHICLES[randomManufacturerIndex].length)];

        int payload = 500 + random.nextInt(9500);

        return new VehicleDTO(manufacturer, model, payload);
    }
}
