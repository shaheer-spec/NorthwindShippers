package com.pluralsight.db;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private DataSource dataSource;

    public DataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Shippers> displayAllShippers(){
        List<Shippers> shippers = new ArrayList<>();
        String getAllShippers = """
                SELECT ShipperID, CompanyName, Phone FROM northwind.shippers;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllShippers);
             ResultSet resultSet = preparedStatement.executeQuery()){
            if (resultSet.next()){

                do {
                    int shipperID = resultSet.getInt(1);
                    String companyName = resultSet.getString(2);
                    String phone = resultSet.getString(3);

                    shippers.add(new Shippers(shipperID, companyName, phone));
                }while (resultSet.next());
            }else {
                System.out.println("No Matches");
            }
        }  catch (Exception ex) {
            System.err.println("An error has occurred!");
            ex.printStackTrace();
        }
        return shippers;
    }

    public void generatingShipper (String shipperName, String shipperPhone){
        String shipperQuery = """
                INSERT INTO Shippers (CompanyName, Phone)
                VALUES (?, ?)
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     shipperQuery, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, shipperName);
            preparedStatement.setString(2, shipperPhone);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows Inserted: " + rows);

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    System.out.println("A new key was added: " + keys.getInt(1));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updatePhoneNumber(int shipperIDChange, String phoneNumberToChange){
        String updateQuery = "UPDATE Shippers SET Phone = ? WHERE ShipperID = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
            preparedStatement.setString(1, phoneNumberToChange);
            preparedStatement.setInt(2, shipperIDChange);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows Updated: " + rows);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteShipperID(int shipperIDToDelete){
        String deleteQuery = """
                DELETE FROM Shippers WHERE ShipperID = ?
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)){
            preparedStatement.setInt(1, shipperIDToDelete);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows Deleted: " + rows);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
