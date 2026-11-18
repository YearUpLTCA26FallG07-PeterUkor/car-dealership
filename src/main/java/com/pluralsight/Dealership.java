package com.pluralsight;

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
