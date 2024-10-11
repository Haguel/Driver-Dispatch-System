package dev.haguel.dds.test_factory;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.DTO.DestinationDTO;

import java.util.Random;

public class TestCargoOrderDTOFactory {

    private static final String[] CARGO_TYPES = {"Electronics", "Furniture", "Clothing", "Food", "Machinery"};
    private static final Random random = new Random();

    public static CargoOrderDTO createRandomCargoOrderDTO() {
        String cargoType = CARGO_TYPES[random.nextInt(CARGO_TYPES.length)];
        int cargoAmount = random.nextInt(1, 10001);
        int payout = random.nextInt(100, 10001);
        short daysToComplete = (short) random.nextInt(1, 15);
        short minExperienceRequired = (short) random.nextInt(0, 70);
        DestinationDTO destinationDTO = TestDestinationDTOFactory.createRandomDestinationDTO();

        return new CargoOrderDTO(cargoType, cargoAmount, payout, daysToComplete, minExperienceRequired, destinationDTO);
    }
}
