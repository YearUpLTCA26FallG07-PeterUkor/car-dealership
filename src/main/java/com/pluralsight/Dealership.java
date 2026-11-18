package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Dealership {
    private String dealershipName;
    private String dealershipAddress;
    private String dealershipPhone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String dealershipName, String dealershipAddress, String dealershipPhone) {
        this.dealershipName = dealershipName;
        this.dealershipAddress = dealershipAddress;
        this.dealershipPhone = dealershipPhone;
        this.inventory = new ArrayList<>();
    }

    public String getDealershipName() {
        return dealershipName;
    }

    public void setDealershipName(String dealershipName) {
        this.dealershipName = dealershipName;
    }

    public String getDealershipAddress() {
        return dealershipAddress;
    }

    public void setDealershipAddress(String dealershipAddress) {
        this.dealershipAddress = dealershipAddress;
    }

    public String getDealershipPhone() {
        return dealershipPhone;
    }

    public void setDealershipPhone(String dealershipPhone) {
        this.dealershipPhone = dealershipPhone;
    }

    // return vehicles priced between min and max (inclusive)
    public ArrayList<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            // compareTo returns a negative number if price < min, positive if price > max
            if (vehicle.getPrice().compareTo(min) >= 0 && vehicle.getPrice().compareTo(max) <= 0) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    // return vehicles matching the make and model, ignoring case
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    // return vehicles made between min and max year (inclusive)
    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    // return vehicles matching the color, ignoring case
    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    // return vehicles with odometer between min and max (inclusive)
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    // return vehicles of the given type (car, truck, SUV, van), ignoring case
    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    // return every vehicle in the inventory
    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    // add a vehicle to the inventory
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    // remove a vehicle from the inventory
    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    // dealership in the pipe-delimited format used by the first line of inventory.csv
    public String toFileString() {
        return dealershipName + "|" + dealershipAddress + "|" + dealershipPhone;
    }

}
