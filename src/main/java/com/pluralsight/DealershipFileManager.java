package com.pluralsight;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;

public class DealershipFileManager {
    private static final String FILE_NAME = "inventory.csv";

    // read inventory.csv and build the dealership with all its vehicles
    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {

            // the first line holds the dealership information
            String dealershipLine = bufferedReader.readLine();
            if (dealershipLine == null || dealershipLine.isBlank()) {
                System.out.println("!!! Inventory file is empty !!!");
                return null;
            }

            String[] dealershipParts = dealershipLine.split("\\|");
            if (dealershipParts.length != 3) {
                System.out.println("!!! Dealership line must be Name|Address|Phone !!!");
                return null;
            }
            dealership = new Dealership(dealershipParts[0], dealershipParts[1], dealershipParts[2]);

            // every line after the first is a vehicle
            int lineNumber = 1;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;

                if (line.isBlank()) {
                    continue;
                }

                String[] vehicleParts = line.split("\\|");
                if (vehicleParts.length != 8) {
                    invalidVehicleEntry(vehicleParts, lineNumber);
                    System.out.println("Exactly 8 parts divided by 7 \"|\" expected");
                    continue;
                }

                // only add the vehicle if every part was valid
                Vehicle vehicle = getVehicle(vehicleParts, lineNumber);
                if (vehicle != null) {
                    dealership.addVehicle(vehicle);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("!!! Inventory file not found !!!");
        } catch (IOException e) {
            System.out.println("!!! Error reading inventory file !!!");
        }

        return dealership;
    }

    // overwrite inventory.csv with the dealership and its current vehicles
    public void saveDealership(Dealership dealership) {

        // false = overwrite the file instead of appending to it
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, false))) {

            bufferedWriter.write(dealership.toFileString());
            bufferedWriter.newLine();

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                bufferedWriter.write(vehicle.toFileString());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("!!! Error saving inventory file !!!");
        }
    }

    // parse one line of vehicle parts into a Vehicle, returns null if a number is invalid
    private Vehicle getVehicle(String[] vehicleParts, int lineNumber) {
        try {
            int vehicleVin = Integer.parseInt(vehicleParts[0].trim());
            int vehicleYear = Integer.parseInt(vehicleParts[1].trim());
            String vehicleMake = vehicleParts[2].trim();
            String vehicleModel = vehicleParts[3].trim();
            String vehicleType = vehicleParts[4].trim();
            String vehicleColor = vehicleParts[5].trim();
            int vehicleOdometer = Integer.parseInt(vehicleParts[6].trim());
            BigDecimal vehiclePrice = new BigDecimal(vehicleParts[7].trim());

            return new Vehicle(vehicleVin, vehicleYear, vehicleMake, vehicleModel,
                    vehicleType, vehicleColor, vehicleOdometer, vehiclePrice);

        } catch (NumberFormatException e) {
            invalidVehicleEntry(vehicleParts, lineNumber);
            System.out.println("VIN, year, and odometer must be whole numbers and price must be a number");
            return null;
        }
    }

    // print invalid vehicle error messages
    private void invalidVehicleEntry(String[] vehicleParts, int lineNumber) {
        System.out.println("\n!!! Invalid Vehicle Entry on line " + lineNumber + ": " +
                Arrays.toString(vehicleParts) + " !!!");
    }
}
