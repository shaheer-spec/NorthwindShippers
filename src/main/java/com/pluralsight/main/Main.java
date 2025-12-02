package com.pluralsight.main;

import com.pluralsight.db.DataManager;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("User and Password are needed!");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

        BasicDataSource northwindDataSource = new BasicDataSource();
        northwindDataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        northwindDataSource.setUsername(username);
        northwindDataSource.setPassword(password);

        DataManager dataManager = new DataManager(northwindDataSource);

        Scanner scanner = new Scanner(System.in);

        System.out.print("What is the Shippers name: ");
        String shipperName = scanner.nextLine();
        System.out.print("What is the Shipper's phone: ");
        String shipperPhone = scanner.nextLine();

        dataManager.generatingShipper(shipperName, shipperPhone);
        System.out.println(dataManager.displayAllShippers());

        System.out.print("What shipper do you want to change the phone number of (ShipperID): ");
        int shipperIDChange = scanner.nextInt();
        scanner.nextLine();
        System.out.print("What is the phone number you want to change to: ");
        String phoneNumberToChange = scanner.nextLine();

        dataManager.updatePhoneNumber(shipperIDChange, phoneNumberToChange);
        System.out.println(dataManager.displayAllShippers());

        System.out.print("What shipper do you want to delete (ShipperID): ");
        int shipperIDToDelete = scanner.nextInt();

        dataManager.deleteShipperID(shipperIDToDelete);
        System.out.println(dataManager.displayAllShippers());

    }
}
