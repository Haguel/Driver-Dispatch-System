package dev.haguel.dds.util;

public class EndPoints {
    public static final String VERSION_1 = "/api/v1";

    public static final String DRIVERS_ENDPOINT = "/drivers";
    public static final String CREATE_DRIVER = DRIVERS_ENDPOINT;
    public static final String GET_ALL_DRIVERS = DRIVERS_ENDPOINT;
    public static final String GET_DRIVER = DRIVERS_ENDPOINT + "/{id}";

    public static final String VEHICLES_ENDPOINT = "/vehicles";
    public static final String CREATE_VEHICLE = VEHICLES_ENDPOINT;
    public static final String GET_ALL_VEHICLES = VEHICLES_ENDPOINT;
    public static final String GET_VEHICLE = VEHICLES_ENDPOINT + "/{id}";
    public static final String VEHICLE_BROKEN_STATUS = VEHICLES_ENDPOINT + "/handleBrokenStatus/{id}";

    public static final String CARGO_ENDPOINT = "/cargo";
    public static final String CREATE_CARGO = CARGO_ENDPOINT;
    public static final String GET_ALL_ORDERS = CARGO_ENDPOINT;
    public static final String GET_ORDER = CARGO_ENDPOINT + "/{id}";
}
