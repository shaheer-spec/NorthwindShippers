package com.pluralsight.db;

public class Shippers {
    private int shipperID;
    private String CompanyName;
    private String phone;

    public Shippers(int shipperID, String companyName, String phone) {
        this.shipperID = shipperID;
        CompanyName = companyName;
        this.phone = phone;
    }

    public int getShipperID() {
        return shipperID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return  "Shippers\n" +
                "----------------------\n" +
                "ShipperID: " + shipperID + "\n" +
                "CompanyName: " + CompanyName + "\n" +
                "Phone: " + phone + "\n";
    }
}
