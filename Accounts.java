package coursework;

public class Accounts extends Customer {
    private int accountNumber;
    private int balance;

    // Constructor
    public Accounts(String fName, String lName, int accountNumber, int balance) {
        setFirstName(fName);
        setLastName(lName);
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getter for balance
    public int getBalance() {
        return balance;
    }

    // Getter for account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // Other methods like deposit, withdraw, etc.
    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money
    public void withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }
}
