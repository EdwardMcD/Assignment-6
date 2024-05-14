package Assignment6Controller;

public class BankAccountDTO {
     private int accountId;
            private int customerId;
            private double balance;
            private String accountType;

            // Constructors
            public BankAccountDTO() {
            }

            public BankAccountDTO(int accountId, int customerId, double balance, String accountType) {
                this.accountId = accountId;
                this.customerId = customerId;
                this.balance = balance;
                this.accountType = accountType;
            }

            // Getters and setters
            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
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

            // toString method for debugging/logging
            @Override
            public String toString() {
                return "BankAccountDTO{" +
                        "accountId=" + accountId +
                        ", customerId=" + customerId +
                        ", balance=" + balance +
                        ", accountType='" + accountType + '\'' +
                        '}';
            }
        }
