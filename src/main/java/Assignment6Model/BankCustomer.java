/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment6Model;

import java.util.*;
import Assignment6Model.BankCustomer;
import Assignment6Model.CustomerAddress;
import Assignment6Model.BankAccount;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kmehta
 */


public class BankCustomer implements Comparable<BankCustomer> {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "CS413";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "219Med@78";

    // Define static variables to be used in the customer processing 
    private static ArrayList<BankCustomer> customers = new ArrayList<>();
    
    private static int custCount = 0;
    String firstName;
    String lastName;
    String phone;
    String email;
    String birthday;
    int customerNumber;
    List accounts = new ArrayList<BankAccount>();
    CustomerAddress address;

    public BankCustomer() {}
    private int id;
    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private String zipCode;
    
    //2-Arg Constructor that creates a new instance, adds self to customer collection and increments the static counter
    public BankCustomer(String fName, String lName) {
        firstName = fName;
        lastName = lName;
        customers.add(this);
        customerNumber = ++custCount;
    }

    public BankCustomer(int num, String fName, String lName) {
        firstName = fName;
        lastName = lName;
        customers.add(this);
        customerNumber = num;
    }

    // Additional constructor for the DAO
    public BankCustomer(int id, String fName, String lName, String em, String ph, String bd) {
        customerNumber = id;
        firstName = fName;
        lastName = lName;
        email = em;
        phone = ph;
        birthday = bd;
        customers.add(this);
    }

    // Constructor including address details
    public BankCustomer(String firstName, String lastName, String email, String phone, String birthday, String streetNumber, String streetName, String city, String state, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    private BankCustomer fetchCustomerDetails(String firstName, String lastName) {
        BankCustomer customer = null;
        String query = "SELECT * FROM bankcustomer JOIN customeraddress ON bankcustomer.id = customeraddress.custid WHERE first_name = ? AND last_name = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve customer details from the result set
                int customerId = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String birthday = resultSet.getString("birthday");
                // Fetch other details similarly

                // Create a BankCustomer object
                customer = new BankCustomer(customerId, firstName, lastName, email, phone, birthday);
                // Populate other details if needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
    
    //All setters and getters for cusotmer

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String em) {
        email = em;
    }    
    
    public void setAddress(CustomerAddress anAddress) {
        address = anAddress;
    }
    
    
    public String getName() {
        return (firstName + " " + lastName);
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setFirstName(String nm) {
        firstName = nm;
    }
    
    public void setLastName(String nm) {
        lastName = nm;
    }

    public List getAccounts() {
        return accounts;
    }
    
    public CustomerAddress getAddress() {
        return address;
    }
    
    public void setAccounts(List acts) {
        accounts = acts;
    }    
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String ph) {
        phone = ph;
    }        

    public String getBirthday() {
        return birthday;
    }
    
    public void setBirthday(String bd) {
        birthday = bd;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }
    
    public void addAccount(BankAccount anAct) {
        accounts.add(anAct);
    }

    public static ArrayList<BankCustomer> getCustomers() {
        //Will add code later to get all bank customers from the Database
        // in collaboration with Customer DTO and DAO objects
        return customers;
    }
    
    public static int getCustCount() {
        return custCount;
    }
    
    public int getCustomerNumber() {
        return customerNumber;
    }
    
    public int compareTo(BankCustomer cust) {

        int num1 = this.getCustomerNumber();
        int num2 = cust.getCustomerNumber();
        
        if(num1 == num2) return 0;
        else if(num1 > num2) return 1;
        else return -1;

    }
    
    public static BankCustomerBuilder builder() {
        return new BankCustomerBuilder();
    }
    
    
    public String toString() {
        
       String str = "\n";
       
       str += this.getCustomerNumber();
       str += " : " + this.getName();
       str += " : " + this.getAddress();
       System.out.println(str);
       
       return str;
       
    }
  
}
