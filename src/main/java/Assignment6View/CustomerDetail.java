/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Assignment6View;

import Assignment6Model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


/**
 *
 * @author karunmehta
 */
public class CustomerDetail extends javax.swing.JFrame {

    /**
     * Creates new form CustomerDetail
     */

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "CS413";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "219Med@78";

    private final String firstName;
    private final String lastName;

    public CustomerDetail(String firstName, String lastName) {
        initComponents();
        this.firstName = firstName;
        this.lastName = lastName;
        CustomerJList = new javax.swing.JList<>();
        displayCustomerDetails();
    }

    private javax.swing.JList<String> CustomerJList;

    private Object[] fetchCustomerDetails(String firstName, String lastName) {
        BankCustomer customer = null;
        int customerId = 0;

        try (Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM bankcustomer WHERE first_name = ? AND last_name = ?")) {

            // Set parameters for the query
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if any results were returned
            if (resultSet.next()) {
                // Get customer details
                customerId = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String birthday = resultSet.getString("birthday");

                // Now that we have the customer's ID, let's fetch their associated accounts
                List<BankAccount> accounts = fetchCustomerAccounts(customerId);

                // Create a BankCustomer object with fetched details and associated accounts
                customer = new BankCustomer(customerId, firstName, lastName, email, phone, birthday);
            } else {
                System.out.println("No customer found with the provided first name and last name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Object[]{customer, customerId};
    }

    private void fetchAndDisplayAddress(int customerId) {
        String query = "SELECT * FROM customeraddress WHERE custid = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Populate address fields in the CustomerDetail frame
                streetNumberTextField.setText(resultSet.getString("streetnum"));
                streetNameTextField.setText(resultSet.getString("streetname"));
                cityTextField.setText(resultSet.getString("city"));
                stateTextField.setText(resultSet.getString("state"));
                zipCodeTextField.setText(resultSet.getString("zip"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayCustomerDetails() {
        // Fetch customer details from the database based on the first name and last name
        Object[] customerDetails = fetchCustomerDetails(firstName, lastName);
        BankCustomer customer = (BankCustomer) customerDetails[0];
        int customerId = (int) customerDetails[1];

        // If customer is null, return
        if (customer == null) {
            return;
        }

        // Display customer details
        jTextField1.setText(customer.getFirstName());
        jTextField2.setText(customer.getLastName());
        jTextField3.setText(customer.getEmail());
        jTextField4.setText(customer.getPhone());
        System.out.println(customer.getId());
        fetchAndDisplayAddress(customerId);

        // Display customer's accounts
        displayCustomerAccounts(customer.getAccounts(), CustomerJList);
    }

    private BankCustomer BankCusInst;

    private void displayCustomerAccounts(List<BankAccount> accounts, javax.swing.JList<String> accountList) {
        // Check if the accounts list is null or empty
        if (accounts == null || accounts.isEmpty()) {
            // If no accounts are found, display a message
            accountList.setListData(new String[]{"No accounts found"});
        } else {
            // Convert the list of BankAccount objects to an array of strings for display
            String[] accountDetails = accounts.stream()
                    .map(account -> "Account ID: " + account.getAccountNum() + ", Balance: $" + account.getBalance())
                    .toArray(String[]::new);

            // Set the array of account details as the data for the accountList JList component
            accountList.setListData(accountDetails);
        }
    }

    private List<BankAccount> fetchCustomerAccounts(int customerId) {
        List<BankAccount> accounts = new ArrayList<>();
        String query = "SELECT * FROM bankaccount WHERE cust_id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int accountId = resultSet.getInt("cust_id");
                double balance = resultSet.getDouble("balance");
                String accountType = resultSet.getString("acct_type");

                // Create BankAccount objects using the new constructor
                BankAccount account = new BankAccount(accountId, customerId, balance, accountType);

                // Add the created account to the accounts list
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        actDetail = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        // Address Fields
        JLabel jLabel7 = new JLabel();
        JLabel jLabel8 = new JLabel();
        JLabel jLabel9 = new JLabel();
        JLabel jLabel10 = new JLabel();
        JLabel jLabel11 = new JLabel();
        streetNumberTextField = new javax.swing.JTextField();
        streetNameTextField = new javax.swing.JTextField();
        cityTextField = new javax.swing.JTextField();
        stateTextField = new javax.swing.JTextField();
        zipCodeTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setText("Customer Detail");

        jTextField1.setText("first name");

        jTextField2.setText("last name");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setText("email");

        jTextField4.setText("phone");

        saveChangesButton = new javax.swing.JButton();
        saveChangesButton.setText("Save Changes");
        saveChangesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveCustomer(evt);
            }
        });



        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });

        jScrollPane1.setViewportView(jList1);

        jLabel2.setText("First Name:");

        jLabel3.setText("Last Name:");

        jLabel4.setText("Email:");

        jLabel5.setText("Phone:");

        jLabel6.setText("Accounts:");

        actDetail.setText("Account Detail");

        actDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actDetailActionPerformed(evt);
            }
        });

        cancel.setText("Cancel");

        jLabel7.setText("Street Number:");

        jLabel8.setText("Street Name:");

        jLabel9.setText("City:");

        jLabel10.setText("State:");

        jLabel11.setText("Zip Code:");

        // Layout setup
        // Do I get extra points for doing all this without a UI editor? Haha
        // Turns out intelliJ isn't too fond of .form files made with other IDEs
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(146, 146, 146)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jScrollPane1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel2)
                                                                        .addComponent(jLabel4))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel5)
                                                                                .addGap(38, 38, 38)
                                                                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel3)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(26, 26, 26)))))
                                .addGap(30, 30, 30))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(actDetail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED) // Add space between buttons
                                .addComponent(saveChangesButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED) // Add space between buttons
                                .addComponent(cancel)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(streetNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(streetNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(zipCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1)
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3))
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(streetNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(streetNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(stateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(zipCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(actDetail)
                                        .addComponent(saveChangesButton)
                                        .addComponent(cancel))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }

    private void saveCustomer(java.awt.event.ActionEvent evt) {
        // Retrieve values from text fields
        String firstName = jTextField1.getText();
        String lastName = jTextField2.getText();
        String email = jTextField3.getText();
        String phone = jTextField4.getText();
        String streetNumber = streetNumberTextField.getText();
        String streetName = streetNameTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String zipCode = zipCodeTextField.getText();

        // Update corresponding tables in the database with new values
        pushChangesToDB(firstName, lastName, email, phone, streetNumber, streetName, city, state, zipCode);
    }

    private void pushChangesToDB(String firstName, String lastName, String email, String phone,
                                 String streetNumber, String streetName, String city, String state, String zipCode) {
        try (Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD)) {
            // Update customer details in bankcustomer table
            String updateCustomerQuery = "UPDATE bankcustomer SET first_name=?, last_name=?, email=?, phone=? WHERE first_name=? AND last_name=?";
            try (PreparedStatement statement = connection.prepareStatement(updateCustomerQuery)) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, email);
                statement.setString(4, phone);
                statement.setString(5, this.firstName); // Previous first name
                statement.setString(6, this.lastName); // Previous last name
                statement.executeUpdate();
            }

            // Retrieve the custid for the customer
            String getCustIdQuery = "SELECT id FROM bankcustomer WHERE first_name=? AND last_name=?";
            int custId;
            try (PreparedStatement getIdStatement = connection.prepareStatement(getCustIdQuery)) {
                getIdStatement.setString(1, firstName);
                getIdStatement.setString(2, lastName);
                try (ResultSet resultSet = getIdStatement.executeQuery()) {
                    if (resultSet.next()) {
                        custId = resultSet.getInt("id");
                    } else {
                        // Handle the case where customer ID is not found
                        throw new SQLException("Customer ID not found");
                    }
                }
            }

            // Update customer address in customeraddress table
            String updateAddressQuery = "UPDATE customeraddress SET streetnum=?, streetname=?, city=?, state=?, zip=? WHERE custid=?";
            try (PreparedStatement addressStatement = connection.prepareStatement(updateAddressQuery)) {
                addressStatement.setString(1, streetNumber);
                addressStatement.setString(2, streetName);
                addressStatement.setString(3, city);
                addressStatement.setString(4, state);
                addressStatement.setString(5, zipCode);
                addressStatement.setInt(6, custId); // Set the custid parameter
                addressStatement.executeUpdate();
            }

            System.out.println("Customer details updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void actDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actDetailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_actDetailActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomerDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerDetail("John", "Doe").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actDetail;
    private javax.swing.JButton cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField streetNumberTextField;
    private javax.swing.JTextField streetNameTextField;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JTextField stateTextField;
    private javax.swing.JTextField zipCodeTextField;
    private javax.swing.JButton saveChangesButton;
    // End of variables declaration//GEN-END:variables
}