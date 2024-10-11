package dev.haguel.dds.test_factory;

import dev.haguel.dds.DTO.DestinationDTO;

import java.util.Map;
import java.util.Random;

import static java.util.Map.entry;

public class TestDestinationDTOFactory {
    private static final Map<String, String[]> COUNTRY_CITY_MAP = Map.ofEntries(
            entry("Germany", new String[]{"Berlin", "Frankfurt", "Munich"}),
            entry("France", new String[]{"Paris", "Lyon", "Marseille"}),
            entry("USA", new String[]{"New York", "Los Angeles", "Chicago"}),
            entry("Canada", new String[]{"Toronto", "Vancouver", "Montreal"}),
            entry("Japan", new String[]{"Tokyo", "Osaka", "Kyoto"})
    );

    private static final Map<String, String[]> CITY_ADDRESS_MAP = Map.ofEntries(
            entry("Berlin", new String[]{"123 Main St", "456 Elm St", "789 Oak St"}),
            entry("Frankfurt", new String[]{"75 Schmidtstraße", "12 Müllerstraße", "34 Schulstraße"}),
            entry("Munich", new String[]{"98 Hauptstraße", "56 Bahnhofstraße", "23 Kirchplatz"}),
            entry("Paris", new String[]{"1 Rue de Rivoli", "2 Place Vendôme", "3 Rue de la Paix"}),
            entry("Lyon", new String[]{"4 Place Bellecour", "5 Rue de la République", "6 Rue Victor Hugo"}),
            entry("Marseille", new String[]{"7 Rue de la Tour", "8 Rue de la Paix", "9 Rue de la Liberté"}),
            entry("New York", new String[]{"10 5th Ave", "11 Broadway", "12 Wall St"}),
            entry("Los Angeles", new String[]{"13 Hollywood Blvd", "14 Sunset Blvd", "15 Santa Monica Blvd"}),
            entry("Chicago", new String[]{"16 Michigan Ave", "17 Wacker Dr", "18 State St"}),
            entry("Toronto", new String[]{"19 Yonge St", "20 Queen St", "21 King St"}),
            entry("Vancouver", new String[]{"22 Granville St", "23 Robson St", "24 Main St"}),
            entry("Montreal", new String[]{"25 Saint Catherine St", "26 Saint Laurent Blvd", "27 Crescent St"}),
            entry("Tokyo", new String[]{"28 Ginza", "29 Shibuya", "30 Shinjuku"}),
            entry("Osaka", new String[]{"31 Umeda", "32 Namba", "33 Tennoji"}),
            entry("Kyoto", new String[]{"34 Gion", "35 Arashiyama", "36 Fushimi Inari"})
    );

    private static final Random random = new Random();

    public static DestinationDTO createRandomDestinationDTO() {
        String country = COUNTRY_CITY_MAP.keySet().toArray(new String[0])[random.nextInt(COUNTRY_CITY_MAP.size())];
        String[] cities = COUNTRY_CITY_MAP.get(country);
        String city = cities[random.nextInt(cities.length)];
        String[] addresses = CITY_ADDRESS_MAP.get(city);
        String address = addresses[random.nextInt(addresses.length)];

        return new DestinationDTO(country, city, address);
    }
}
