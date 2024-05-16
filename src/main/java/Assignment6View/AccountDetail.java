/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Assignment6View;

import Assignment6Model.*;
import Assignment6Controller.*;

import java.sql.*;
import javax.swing.*;


/**
 *
 * @author karunmehta
 */
public class AccountDetail extends javax.swing.JFrame {

    /**
     * Creates new form AccountDetail
     */

    private final int accountNumber;

    // Establish a connection to the database
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "CS413";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "219Med@78";

    public AccountDetail(int accountNumber) {
        initComponents();
        this.accountNumber = accountNumber;
        displayAccountInformation();
    }

    // Populate text fields with account information
    private void displayAccountInformation() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);

            // Prepare a SQL statement to retrieve account information based on the account number
            String sql = "SELECT acct_num, cust_id, balance, create_date, acct_type FROM bankaccount WHERE acct_num = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accountNumber);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if the result set has data
            if (resultSet.next()) {
                // Retrieve values from the result set
                int accountNum = resultSet.getInt("acct_num");
                    System.out.println(accountNum);
                int customerId = resultSet.getInt("cust_id");
                    System.out.println(customerId);
                float balance = resultSet.getFloat("balance");
                String createDate = resultSet.getString("create_date");
                String accountType = resultSet.getString("acct_type");

                if (accountType.equalsIgnoreCase("CH")){
                    accountType = "CHECKING";
                } else {
                    accountType = "SAVINGS";
                }

                // Set the retrieved values to the text fields
                jTextField5.setText(Integer.toString(accountNum));
                jTextField1.setText(Integer.toString(customerId));
                jTextField2.setText(accountType);
                jTextField3.setText(Float.toString(balance));
                jTextField4.setText(createDate);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exception
        }
    }

    // JFrame for trasactions
    public class TransactionsFrame extends JFrame {
        // Constructor
        public TransactionsFrame(int accountNumber) {
            // Initialize frame properties
            setTitle("Transactions for Account: " + accountNumber);
            setSize(400, 300);
        }
    }

    // "Show Transactions" button listener
//    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
//
//        TransactionsFrame transactionsFrame = new TransactionsFrame(accountNumber);
//        transactionsFrame.setVisible(true);
//    }
//
//    // "Cancel" button
//    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
//        // Close the screen
//        this.dispose();
//    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);

            // Prepare a SQL statement to update account information based on the account number
            String sql = "UPDATE bankaccount SET cust_id=?, balance=?, create_date=?, acct_type=? WHERE acct_num=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set values from text fields to the statement
            statement.setInt(1, Integer.parseInt(jTextField1.getText())); // Customer ID
            statement.setFloat(2, Float.parseFloat(jTextField3.getText())); // Balance
            statement.setString(3, jTextField4.getText()); // Create Date
            statement.setString(4, jTextField2.getText()); // Account Type
            statement.setInt(5, Integer.parseInt(jTextField5.getText())); // Account Number

            // Execute the update
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Account details updated successfully.");
            } else {
                System.out.println("Failed to update account details.");
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exception
        }
    }

    // "Show Transactions" button listener
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Get the account number
        int accountNumber = Integer.parseInt(jTextField5.getText());

        TransactionSummary transactionSummary = new TransactionSummary(accountNumber);
        transactionSummary.setVisible(true);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setText("Account Number:");

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setText("Account Detail");

        jLabel2.setText("Customer ID:");

        jTextField1.setText("custname");

        jLabel3.setText("Account Type:");

        jTextField2.setText("accttype");

        jLabel4.setText("Balance:");

        jTextField3.setText("balance");

        jLabel5.setText("Create Date:");

        jTextField4.setText("created");

        jButton1.setText("Show Transactions");

        jButton2.setText("Cancel");

        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addGap(40, 40, 40)
                                                                .addComponent(jTextField1))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel5)
                                                                .addGap(37, 37, 37)
                                                                .addComponent(jTextField4))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel6)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jTextField5)))
                                                .addGap(113, 113, 113))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel3)
                                                                .addGap(31, 31, 31)
                                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel4)
                                                                .addGap(47, 47, 47)
                                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(68, 68, 68))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>

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
            java.util.logging.Logger.getLogger(AccountDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                int accountNumber = 1; // Replace with actual account number
                new AccountDetail(accountNumber).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    // Buttuns
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;

    // Labels
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;

    // Text fields
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
