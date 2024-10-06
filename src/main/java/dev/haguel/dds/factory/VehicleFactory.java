package dev.haguel.dds.factory;

import dev.haguel.dds.DTO.VehicleDTO;

import java.util.Random;

public class VehicleFactory {
    private static final String[][] VEHICLES = {
            {"Volvo", "FH16", "FMX"},
            {"Scania", "R-Series", "S-Series"},
            {"Mercedes-Benz", "Actros", "Arocs"},
            {"MAN", "TGX", "TGS"},
            {"DAF", "XF", "CF"},
            {"Iveco", "Stralis", "Trakker"}
    };

    private static final Random random = new Random();

    public static VehicleDTO createVehicle() {
        int randomManufacturerIndex = random.nextInt(VEHICLES.length);
        String manufacturer = VEHICLES[randomManufacturerIndex][0];
        String model = VEHICLES[randomManufacturerIndex][random.nextInt(1, VEHICLES[randomManufacturerIndex].length)];

        int payload = 10000 + random.nextInt(30000);

        return new VehicleDTO(manufacturer, model, payload);
    }
}
