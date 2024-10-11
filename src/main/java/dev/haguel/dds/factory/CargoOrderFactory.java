package dev.haguel.dds.factory;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.DTO.DestinationDTO;

import java.util.Random;

public class CargoOrderFactory {
    private static final String[] CARGO_TYPES = {"Food", "Electronics", "Furniture", "Clothing", "Raw Materials"};
    private static final Random random = new Random();

    public static CargoOrderDTO createCargoOrder() {
        Random random = new Random();

        String cargoType = CARGO_TYPES[random.nextInt(CARGO_TYPES.length)];
        int cargoAmount = 100 + random.nextInt(901);
        int payout = 500 + random.nextInt(501);
        short daysToComplete = (short) (1 + random.nextInt(10));
        short minExperienceRequired = (short) (random.nextInt(80));
        DestinationDTO destinationDTO = DestinationFactory.createDestination();

        return new CargoOrderDTO(cargoType, cargoAmount, payout,
                daysToComplete, minExperienceRequired, destinationDTO);
    }
}
