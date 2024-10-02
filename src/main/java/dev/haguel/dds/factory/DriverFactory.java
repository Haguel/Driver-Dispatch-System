package dev.haguel.dds.factory;

import dev.haguel.dds.DTO.DriverDTO;

import java.sql.Date;
import java.util.Random;

public class DriverFactory {
    private static final String[] NAMES = {"John", "Emily", "Michael", "Sophia", "James", "Olivia"};
    private static final String[] SURNAMES = {"Smith", "Johnson", "Brown", "Taylor", "Anderson", "White"};
    private static final Random random = new Random();

    public static DriverDTO createDriver() {
        String randomName = NAMES[random.nextInt(NAMES.length)];
        String randomSurname = SURNAMES[random.nextInt(SURNAMES.length)];

        long startMillis = new Date(60, 0, 1).getTime(); // Jan 1, 1960
        long endMillis = new Date(100, 0, 1).getTime();  // Jan 1, 2000
        long randomDateMillis = random.nextLong(startMillis, endMillis);
        Date randomDateOfBirth = new Date(randomDateMillis);

        short randomExperience = (short) random.nextInt(0, 21);

        return new DriverDTO(randomName, randomSurname, randomDateOfBirth, randomExperience);
    }
}
