/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment6View;

import Assignment6Controller.CustomerDTO;
import Assignment6Model.BankCustomer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 *
 * @author karunmehta
 */

public class HomePage extends JFrame implements ActionListener {

        JButton jbSearch, jbAdd, jbRemove, jbUpdate, jbShowCustomers;

        HomePage() {

            setLayout(null);

            //create icon you want on the frame
            ImageIcon iIcon = new ImageIcon(ClassLoader.getSystemResource("icons/customer.jpg"));
            Image anImage = iIcon.getImage().getScaledInstance(1000, 630, Image.SCALE_DEFAULT);
            ImageIcon iIcon2 = new ImageIcon(anImage);
            JLabel theLabel = new JLabel(iIcon2);
            theLabel.setBounds(0, 0, 1100,630);
            add(theLabel);

            JLabel heading = new JLabel("Application Main Menu");
            heading.setBounds(100, 100, 400,40);
            heading.setFont(new Font("Tahoma", Font.BOLD, 25));
            theLabel.add(heading);

            jbSearch = new JButton("Search Customer(s)");
            jbSearch.setBounds(100, 200, 200,40);
            jbSearch.addActionListener(this);
            theLabel.add(jbSearch);

            jbAdd = new JButton("Add New Customer");
            jbAdd.setBounds(100, 250, 200,40);
            jbAdd.addActionListener(this);
            theLabel.add(jbAdd);

            jbShowCustomers = new JButton("Show All Customers");
            jbShowCustomers.setBounds(100, 300, 200,40);
            jbShowCustomers.addActionListener(this);
            theLabel.add(jbShowCustomers);

            setSize(1120, 630);
            setLocation(250, 100);
            setVisible(true);

        }

    public void actionPerformed(ActionEvent ae) {

        if ((ae.getSource()) == jbAdd) {
            new CustomerFrame();
        } else if ((ae.getSource()) == jbSearch) {
            try {
                System.out.println("Search Customers button clicked.");
                CustomerSearch customerSearch = new CustomerSearch();
                customerSearch.setVisible(true); // Set the CustomerList frame visible
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CustomerSearch();
            this.setVisible(true);
        } else if ((ae.getSource()) == jbShowCustomers) {
            try {
                System.out.println("Show All Customers button clicked.");
                List<BankCustomer> customers = CustomerDTO.searchCustomers("", "", "", "", "");
                CustomerList customerListForm = new CustomerList();
                customerListForm.setCustomerList(customers); // Pass the list of customers to the method
                customerListForm.setVisible(true);
                this.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

        public static void main(String[] args) {
            new HomePage();
        }
    }
