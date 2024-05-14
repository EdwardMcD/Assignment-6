package Assignment6Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "CS413";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "219Med@78";

    // Method to fetch accounts associated with a customer ID from the database
    public List<BankAccountDTO> findCustomerAccounts(int customerId) {
        List<BankAccountDTO> accounts = new ArrayList<>();
        String query = "SELECT * FROM bankaccount WHERE cust_id = ?";

        try (Connection connection = DataConnection.getDBConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                double balance = resultSet.getDouble("balance");
                String accountType = resultSet.getString("account_type");

                // Create a BankAccountDTO object using the fetched data
                BankAccountDTO account = new BankAccountDTO(accountId, customerId, balance, accountType);

                // Add the created account to the accounts list
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }
}