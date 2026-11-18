package com.pluralsight;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Vehicle {
    private int vehicleVin;
    private int vehicleYear;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleType;
    private String vehicleColor;
    private int vehicleOdometer;
    private BigDecimal vehiclePrice;

    public Vehicle(int vehicleVin, int vehicleYear, String vehicleMake, String vehicleModel, String vehicleType,
                   String vehicleColor, int vehicleOdometer, BigDecimal vehiclePrice) {
        this.vehicleVin = vehicleVin;
        this.vehicleYear = vehicleYear;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.vehicleColor = vehicleColor;
        this.vehicleOdometer = vehicleOdometer;
        this.vehiclePrice = vehiclePrice;
    }

    public int getVin() {
        return vehicleVin;
    }

    public void setVin(int vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public int getYear() {
        return vehicleYear;
    }

    public void setYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getMake() {
        return vehicleMake;
    }

    public void setMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getModel() {
        return vehicleModel;
    }

    public void setModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    // car, truck, SUV, van
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return vehicleColor;
    }

    public void setColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public int getOdometer() {
        return vehicleOdometer;
    }

    public void setOdometer(int vehicleOdometer) {
        this.vehicleOdometer = vehicleOdometer;
    }

    public BigDecimal getPrice() {
        return vehiclePrice;
    }

    public void setPrice(BigDecimal vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    // display vehicle in one line under the table header
    @Override
    public String toString() {
        return String.format("%-8d  %-4d  %-12.12s  %-12.12s  %-6.6s  %-10.10s  %10d  %12.2f",
                vehicleVin, vehicleYear, vehicleMake, vehicleModel, vehicleType, vehicleColor, vehicleOdometer, vehiclePrice);
    }

    // column titles matching toString
    public static String tableHeader() {
        return String.format("%-8s  %-4s  %-12s  %-12s  %-6s  %-10s  %10s  %12s",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
    }

    // vehicle in the pipe-delimited format used by inventory.csv
    public String toFileString() {
        return vehicleVin + "|" + vehicleYear + "|" + vehicleMake + "|" + vehicleModel + "|" + vehicleType + "|" +
                vehicleColor + "|" + vehicleOdometer + "|" + vehiclePrice.setScale(2, RoundingMode.HALF_UP);
    }
}
