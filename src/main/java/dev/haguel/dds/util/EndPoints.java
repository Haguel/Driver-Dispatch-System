package dev.haguel.dds.util;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class EndPoints {
    public static final String GET_MAIN_PAGE = "/main";

    public static final String GET_AUTH_MENU_PAGE = "/";
    public static final String GET_LOGIN_PAGE = "/login/get";
    public static final String GET_SIGN_UP_PAGE = "/sign-up/get";
    public static final String SIGN_UP = "/sign-up";

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

    public static List<String> getAuthEndpoints() {
        return List.of(
                GET_AUTH_MENU_PAGE,
                GET_LOGIN_PAGE,
                GET_SIGN_UP_PAGE,
                SIGN_UP
        );
    }


    public static List<String> getDispatcherEndpoints() {
        return List.of(
                GET_MAIN_PAGE,
                GET_DRIVERS,
                GET_DRIVER,
                GET_CARGO_ORDERS,
                GET_CARGO_ORDER,
                GET_VEHICLES,
                GET_VEHICLE,
                CREATE_CARGO_ORDER,
                CREATE_CARGO_ORDER_FORM,
                HANDLE_BROKEN_STATUS
        );
    };

    public static List<String> getAdminEndpoints() {
        return List.of(
                GET_INITIALIZER_MENU,
                CLEAR_DB,
                INIT_DRIVERS,
                INIT_VEHICLES,
                CREATE_VEHICLE_FORM,
                CREATE_VEHICLE,
                CREATE_DRIVER_FORM,
                CREATE_DRIVER
        );
    };

    public static void setMainMenuEndpoints(Model model) {
        model.addAttribute("initializerMenu", EndPoints.GET_INITIALIZER_MENU);
        model.addAttribute("getCargoOrders", EndPoints.GET_CARGO_ORDERS);
        model.addAttribute("getVehicles", EndPoints.GET_VEHICLES);
        model.addAttribute("getDrivers", EndPoints.GET_DRIVERS);
        model.addAttribute("createCargoOrderForm", EndPoints.CREATE_CARGO_ORDER_FORM);
        model.addAttribute("createVehicleForm", EndPoints.CREATE_VEHICLE_FORM);
        model.addAttribute("createDriverForm", EndPoints.CREATE_DRIVER_FORM);
    }

    public static void setAuthMenuEndpoints(Model model) {
        model.addAttribute("loginPage", EndPoints.GET_LOGIN_PAGE);
        model.addAttribute("signUpPage", EndPoints.GET_SIGN_UP_PAGE);
    }
}
