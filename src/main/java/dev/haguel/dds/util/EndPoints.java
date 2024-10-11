package dev.haguel.dds.util;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class EndPoints {
    public static final String MENU = "/menu/get";

    public static final String MAIN = "/";
    public static final String LOGIN = "/login";

    public static final String GET_INITIALIZER_MENU = "/initializer";
    public static final String CLEAR_DB = "/initializer/clearDb";
    public static final String INIT_DRIVERS = "/initializer/initDrivers";
    public static final String INIT_VEHICLES = "/initializer/initVehicles";

    public static final String GET_DRIVERS = "/drivers/get";
    public static final String GET_DRIVER = "/drivers/get/{id}";
    public static final String CREATE_DRIVER = "/drivers/create";
    public static final String CREATE_DRIVER_FORM = "/drivers/create/form";

    public static final String GET_CARGO_ORDERS = "/cargoOrders/get";
    public static final String GET_CARGO_ORDER = "/cargoOrders/get/{id}";
    public static final String CREATE_CARGO_ORDER = "/cargoOrders/create";
    public static final String CREATE_CARGO_ORDER_FORM = "/cargoOrders/create/form";

    public static final String GET_VEHICLES = "/vehicles/get";
    public static final String GET_VEHICLE = "/vehicles/get/{id}";
    public static final String CREATE_VEHICLE = "/vehicles/create";
    public static final String CREATE_VEHICLE_FORM = "/vehicles/create/form";
    public static final String HANDLE_BROKEN_STATUS = "/vehicles/{id}/broken-status";

    private static List<String> getDriverEndpoints() {
        return List.of(GET_DRIVERS, GET_DRIVER, CREATE_DRIVER, CREATE_DRIVER_FORM);
    }

    private static List<String> getCargoOrderEndpoints() {
        return List.of(GET_CARGO_ORDERS, GET_CARGO_ORDER, CREATE_CARGO_ORDER, CREATE_CARGO_ORDER_FORM);
    }

    private static List<String> getVehicleEndpoints() {
        return List.of(GET_VEHICLES, GET_VEHICLE, CREATE_VEHICLE, CREATE_VEHICLE_FORM, HANDLE_BROKEN_STATUS);
    }

    private static List<String> getInitializerEndpoints() {
        return List.of(GET_INITIALIZER_MENU, CLEAR_DB, INIT_DRIVERS, INIT_VEHICLES);
    }

    public static void setMenuEndpoints(Model model) {
        model.addAttribute("initializerMenu", EndPoints.GET_INITIALIZER_MENU);
        model.addAttribute("getCargoOrders", EndPoints.GET_CARGO_ORDERS);
        model.addAttribute("getVehicles", EndPoints.GET_VEHICLES);
        model.addAttribute("getDrivers", EndPoints.GET_DRIVERS);
        model.addAttribute("createCargoOrderForm", EndPoints.CREATE_CARGO_ORDER_FORM);
        model.addAttribute("createVehicleForm", EndPoints.CREATE_VEHICLE_FORM);
        model.addAttribute("createDriverForm", EndPoints.CREATE_DRIVER_FORM);
    }

    public static List<String> getDispatcherEndpoints() {
        List<String> dispatcherEndpoints = new ArrayList<>();
        dispatcherEndpoints.addAll(getDriverEndpoints());
        dispatcherEndpoints.addAll(getCargoOrderEndpoints());
        dispatcherEndpoints.addAll(getVehicleEndpoints());
        dispatcherEndpoints.add(MENU);

        return dispatcherEndpoints;
    };

    public static List<String> getAdminEndpoints() {
        List<String> adminEndpoints = new ArrayList<>();
        adminEndpoints.addAll(getInitializerEndpoints());

        return adminEndpoints;
    };
}
