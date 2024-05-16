package Assignment6Controller;

public class AccountDTO {
    private int accountNum;
    private double balance;
    private String accountType;
    private int customerId;

    // Constructors
    public AccountDTO() {
    }

    public AccountDTO(int accountNum, double balance, String accountType, int customerId) {
        this.accountNum = accountNum;
        this.balance = balance;
        this.accountType = accountType;
        this.customerId = customerId;
    }

    // Getters and setters
    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}