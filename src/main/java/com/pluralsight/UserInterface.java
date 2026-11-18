package com.pluralsight;

import java.math.BigDecimal;
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
        BigDecimal lowPrice = promptForPrice("Enter the Lowest Price (ex: 5000): ");
        BigDecimal highPrice = promptForPrice("Enter the Highest Price (ex: 20000): ");

        System.out.println("\n=== Vehicles between $" + lowPrice + " and $" + highPrice + " ===");
        displayVehicles(dealership.getVehiclesByPrice(lowPrice, highPrice));
    }

    // search vehicles by make and model
    public void processGetByMakeModelRequest() {
        System.out.print("\nEnter the Make (ex: Ford): ");
        String make = input.nextLine().trim();
        System.out.print("Enter the Model (ex: Ranger): ");
        String model = input.nextLine().trim();

        System.out.println("\n=== " + make + " " + model + " Vehicles ===");
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    // search vehicles by year range
    public void processGetByYearRequest() {
        int lowYear = promptForNumber("Enter the Oldest Year (ex: 2005): ");
        int highYear = promptForNumber("Enter the Newest Year (ex: 2020): ");

        System.out.println("\n=== Vehicles from " + lowYear + " to " + highYear + " ===");
        displayVehicles(dealership.getVehiclesByYear(lowYear, highYear));
    }

    // search vehicles by color
    public void processGetByColorRequest() {
        System.out.print("\nEnter the Color (ex: Red): ");
        String color = input.nextLine().trim();

        System.out.println("\n=== " + color + " Vehicles ===");
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    // search vehicles by mileage range
    public void processGetByMileageRequest() {
        int lowMileage = promptForNumber("Enter the Lowest Mileage (ex: 0): ");
        int highMileage = promptForNumber("Enter the Highest Mileage (ex: 100000): ");

        System.out.println("\n=== Vehicles with " + lowMileage + " to " + highMileage + " miles ===");
        displayVehicles(dealership.getVehiclesByMileage(lowMileage, highMileage));
    }

    // search vehicles by type
    public void processGetByVehicleTypeRequest() {
        System.out.print("\nEnter the Vehicle Type (car, truck, SUV, van): ");
        String vehicleType = input.nextLine().trim();

        System.out.println("\n=== " + vehicleType + " Vehicles ===");
        displayVehicles(dealership.getVehiclesByType(vehicleType));
    }

    // keep asking until the user enters a whole number
    private int promptForNumber(String prompt) {
        while (true) {
            System.out.print("\n" + prompt);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("!!! Please enter a whole number !!!");
            }
        }
    }

    // keep asking until the user enters a valid price
    private BigDecimal promptForPrice(String prompt) {
        while (true) {
            System.out.print("\n" + prompt);
            try {
                return new BigDecimal(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("!!! Please enter a valid price (numbers only) !!!");
            }
        }
    }

    // prompt for a new vehicle and add it to the inventory
    public void processAddVehicleRequest() {
        System.out.println("\n=== Enter the New Vehicle Information ===");

        int vehicleVin = promptForNumber("VIN (whole number, ex: 12345): ");

        // every VIN in the inventory has to be unique
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vehicleVin) {
                System.out.println("\n!!! A vehicle with VIN " + vehicleVin + " already exists !!!");
                return;
            }
        }

        int vehicleYear = promptForNumber("Year (ex: 2018): ");
        System.out.print("\nMake (ex: Toyota): ");
        String vehicleMake = input.nextLine().trim();
        System.out.print("Model (ex: Camry): ");
        String vehicleModel = input.nextLine().trim();
        System.out.print("Type (car, truck, SUV, van): ");
        String vehicleType = input.nextLine().trim();
        System.out.print("Color (ex: Blue): ");
        String vehicleColor = input.nextLine().trim();
        int vehicleOdometer = promptForNumber("Odometer (miles, ex: 45000): ");
        BigDecimal vehiclePrice = promptForPrice("Price (ex: 15995.00): ");

        Vehicle newVehicle = new Vehicle(vehicleVin, vehicleYear, vehicleMake, vehicleModel,
                vehicleType, vehicleColor, vehicleOdometer, vehiclePrice);
        dealership.addVehicle(newVehicle);

        // save right away so the file always matches the inventory
        new DealershipFileManager().saveDealership(dealership);

        System.out.println("\n=== Vehicle Added Successfully ===");
        System.out.println(Vehicle.tableHeader());
        System.out.println(newVehicle);
    }

    // remove a vehicle from the inventory by VIN
    public void processRemoveVehicleRequest() {
    }
}
