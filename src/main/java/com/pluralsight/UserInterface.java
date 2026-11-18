package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    static Scanner input = new Scanner(System.in);

    public UserInterface() {
    }

    // show the menu until the user quits
    public void display() {
        init();

        // stop the app if the inventory file could not be loaded
        if (dealership == null) {
            System.out.println("!!! Could not load the dealership - Ending Program !!!");
            return;
        }

        boolean inLoop = true;
        do {
            System.out.println("\n=== " + dealership.getDealershipName() + " ===");
            System.out.println("""
                    1 - Find vehicles within a price range
                    2 - Find vehicles by make / model
                    3 - Find vehicles by year range
                    4 - Find vehicles by color
                    5 - Find vehicles by mileage range
                    6 - Find vehicles by type (car, truck, SUV, van)
                    7 - List ALL vehicles
                    8 - Add a vehicle
                    9 - Remove a vehicle
                    99 - Quit
                    """);
            System.out.print("Enter your choice (1-9 or 99): ");
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1" -> processGetByPriceRequest();
                case "2" -> processGetByMakeModelRequest();
                case "3" -> processGetByYearRequest();
                case "4" -> processGetByColorRequest();
                case "5" -> processGetByMileageRequest();
                case "6" -> processGetByVehicleTypeRequest();
                case "7" -> processGetAllVehiclesRequest();
                case "8" -> processAddVehicleRequest();
                case "9" -> processRemoveVehicleRequest();
                case "99" -> {
                    inLoop = false;
                    System.out.println("\n=== Thank you, Goodbye ===");
                }
                default -> System.out.println("\n!!! Invalid Choice - Please enter 1-9 or 99 !!!");
            }
        } while (inLoop);
    }

    // load the dealership and its vehicles from the inventory file
    private void init() {
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.getDealership();
    }

    // print a list of vehicles as a table
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("\n!!! No Vehicles Found !!!");
            return;
        }

        System.out.println("\n" + Vehicle.tableHeader());
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
        System.out.println(vehicles.size() + " vehicle(s) found");
    }

    // list every vehicle in the dealership
    public void processGetAllVehiclesRequest() {
        System.out.println("\n=== All Vehicles ===");
        displayVehicles(dealership.getAllVehicles());
    }

    // search vehicles by price range
    public void processGetByPriceRequest() {
    }

    // search vehicles by make and model
    public void processGetByMakeModelRequest() {
    }

    // search vehicles by year range
    public void processGetByYearRequest() {
    }

    // search vehicles by color
    public void processGetByColorRequest() {
    }

    // search vehicles by mileage range
    public void processGetByMileageRequest() {
    }

    // search vehicles by type
    public void processGetByVehicleTypeRequest() {
    }

    // prompt for a new vehicle and add it to the inventory
    public void processAddVehicleRequest() {
    }

    // remove a vehicle from the inventory by VIN
    public void processRemoveVehicleRequest() {
    }
}
