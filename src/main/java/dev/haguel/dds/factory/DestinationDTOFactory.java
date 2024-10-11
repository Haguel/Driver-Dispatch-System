package dev.haguel.dds.factory;

import dev.haguel.dds.DTO.DestinationDTO;

import java.util.Random;

public class DestinationDTOFactory {
    private static final String[][] DESTINATIONS = {
            {"USA", "New York"},
            {"Germany", "Berlin"},
            {"Japan", "Tokyo"},
            {"Canada", "Toronto"},
            {"Brazil", "São Paulo"}
    };
    private static final Random random = new Random();

    public static DestinationDTO createDestination() {
        int destIndex = random.nextInt(DESTINATIONS.length);
        String country = DESTINATIONS[destIndex][0];
        String city = DESTINATIONS[destIndex][1];
        String address = "Street " + (random.nextInt(100) + 1);

        return new DestinationDTO(country, city, address);
    }
}
