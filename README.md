# Car Dealership

A command-line Java application for managing a used car dealership's inventory. Users can search vehicles in different ways, list the whole inventory, and add or remove vehicles. The dealership and its vehicles are saved in `inventory.csv`, so any changes are still there the next time the app starts.

## Features

- **Search Vehicles:**
  - Price range - vehicles between a lowest and highest price
  - Make / model - vehicles matching a make and model (for example `Ford Ranger`)
  - Year range - vehicles made between two years
  - Color - vehicles of a certain color
  - Mileage range - vehicles with an odometer between two values
  - Type - car, truck, SUV or van

- **Manage Inventory:**
  - List ALL vehicles - show every vehicle in the dealership
  - Add a vehicle - enter the details of a new vehicle (VINs must be unique)
  - Remove a vehicle - remove a vehicle by its VIN after confirming (Y/N)
  - Quit - close the application (option `99`)

- **Table view:** results are shown in lined-up columns with a count of how many vehicles were found.
- **Auto save:** the inventory file is updated right after a vehicle is added or removed.
- **Input validation:** invalid menu choices, numbers and prices are rejected, and the user is asked again. Bad lines in the inventory file are reported and skipped.

## How to Run

Requires Java 17 or newer.

### IntelliJ

1. Open the project folder in IntelliJ.
2. Open `src/main/java/com/pluralsight/Program.java`.
3. Click the green **Run** arrow next to `main`.

### Command Line

From the project folder, compile the code:

```bash
javac -d target/classes src/main/java/com/pluralsight/*.java
```

Then start the app:

```bash
java -cp target/classes com.pluralsight.Program
```

## Notes

- Searches for make, model, color and type ignore upper and lower case.
- All ranges include both ends (for example a year range of `2010` to `2015` includes 2010 and 2015).
- `inventory_backup.csv` is a copy of the original inventory, in case you want to reset the data.
- The first line of `inventory.csv` holds the dealership, and every line after it is a vehicle:

```
name|address|phone
D & B Used Cars|111 Old Benbrook Rd|817-555-5555

vin|year|make|model|type|color|odometer|price
10112|1993|Ford|Explorer|SUV|Red|525123|995.00
```

---

*README.md Written by Peter Ukor, reviewed with Anthropic's Claude.*
