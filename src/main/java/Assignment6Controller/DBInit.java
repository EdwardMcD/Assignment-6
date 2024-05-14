package Assignment6Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public class DBInit {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "CS413";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "219Med@78";

    public static void main(String[] args) {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        createDatabase();
        initializeDatabase();
        printTablesAndData();
    }

    private static void createDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            // Drop the database if it already exists
            statement.executeUpdate("DROP DATABASE IF EXISTS " + DB_NAME);

            // Create the database
            statement.executeUpdate("CREATE DATABASE " + DB_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            // Create tables
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS admin (userid VARCHAR(40), pwd VARCHAR(45))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS bankcustomer (id INT PRIMARY KEY AUTO_INCREMENT,first_name VARCHAR(45),last_name VARCHAR(45),email VARCHAR(40),phone VARCHAR(20),sex VARCHAR(10),birthday VARCHAR(20),addressid INT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS customeraddress (streetnum INT,streetname VARCHAR(45),city VARCHAR(40),state VARCHAR(5),zip INT,custid INT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS bankaccount (acct_num INT PRIMARY KEY AUTO_INCREMENT,cust_id INT,balance FLOAT,create_date VARCHAR(40),last_update_date VARCHAR(40),acct_type VARCHAR(5),od_limit FLOAT,int_rate FLOAT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS accounttransaction (create_date DATETIME,tran_type VARCHAR(45),amount FLOAT, summary VARCHAR(40),acct_id INT)");

            // Load data into tables
            loadBankCustomerData(connection);
            loadCustomerAddressData(connection);
            loadBankAccountData(connection);
            loadAccountTransactionData(connection);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadBankCustomerData(Connection connection) throws SQLException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/DBResources/bankcustomer.csv"));
             Statement statement = connection.createStatement()) {
            // Skip the header line
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String firstName = data[0];
                String lastName = data[1];
                String email = data[2];
                String phone = data[3];
                String sex = data[4];
                String birthday = data[5];
                int addressId = Integer.parseInt(data[6]);
                statement.executeUpdate("INSERT INTO bankcustomer (first_name, last_name, email, phone, sex, birthday, addressid) " +
                        "VALUES ('" + firstName + "', '" + lastName + "', '" + email + "', '" + phone + "', '" + sex + "', '" + birthday + "', " + addressId + ")");
            }
        }
    }

    private static void loadCustomerAddressData(Connection connection) throws SQLException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/DBResources/customeraddress.csv"));
             Statement statement = connection.createStatement()) {
            // Skip the header line
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int streetNum = Integer.parseInt(data[0]);
                String streetName = data[1];
                String city = data[2];
                String state = data[3];
                int zip = Integer.parseInt(data[4]);
                int custId = Integer.parseInt(data[5]);
                statement.executeUpdate("INSERT INTO customeraddress (streetnum, streetname, city, state, zip, custid) " +
                        "VALUES (" + streetNum + ", '" + streetName + "', '" + city + "', '" + state + "', " + zip + ", " + custId + ")");
            }
        }
    }

    private static void loadBankAccountData(Connection connection) throws SQLException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/DBResources/bankaccount.csv"));
             Statement statement = connection.createStatement()) {
            // Skip the header line
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int custId = Integer.parseInt(data[0]);
                float balance = Float.parseFloat(data[1]);
                String createDate = data[2];
                String lastUpdateDate = data[3];
                String acctType = data[4];
                float odLimit = Float.parseFloat(data[5]);
                float intRate = Float.parseFloat(data[6]);
                statement.executeUpdate("INSERT INTO bankaccount (cust_id, balance, create_date, last_update_date, acct_type, od_limit, int_rate) " +
                        "VALUES (" + custId + ", " + balance + ", '" + createDate + "', '" + lastUpdateDate + "', '" + acctType + "', " + odLimit + ", " + intRate + ")");
            }
        }
    }

    private static void loadAccountTransactionData(Connection connection) throws SQLException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/DBResources/accounttransaction.csv"));
             Statement statement = connection.createStatement()) {
            // Skip the header line
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String createDate = data[0];
                String tranType = data[1];
                float amount = Float.parseFloat(data[2]);
                String summary = data[3];
                int acctId = Integer.parseInt(data[4]);
                statement.executeUpdate("INSERT INTO accounttransaction (create_date, tran_type, amount, summary, acct_id) " +
                        "VALUES ('" + createDate + "', '" + tranType + "', " + amount + ", '" + summary + "', " + acctId + ")");
            }
        }
    }

    // Confirm it worked and stuff
    private static void printTablesAndData() {
        try (Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            // Array to store table names
            String[] tableNames = {"admin", "bankcustomer", "customeraddress", "bankaccount", "accounttransaction"};

            // Iterate over each table
            for (String tableName : tableNames) {
                System.out.println("Table: " + tableName);
                System.out.println("--------------------------");

                // Execute SELECT query to retrieve all rows from the table
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);

                // Get metadata to fetch column names
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Print column names
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + "\t");
                }
                System.out.println();

                // Print table data
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(resultSet.getString(i) + "\t");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
