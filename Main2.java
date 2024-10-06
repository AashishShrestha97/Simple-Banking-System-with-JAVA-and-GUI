package coursework;
// Main class to test the banking system
public class Main2 {
    public static void main(String[] args) {
        // Create account1 with name "Binaya Karki", account number 1, and initial balance of 2000
        Accounts account1 = new Accounts("Binaya", "Karki", 1, 2000);
        
        // Create account2 with name "Aashish Shrestha", account number 2, and initial balance of 1000
        Accounts account2 = new Accounts("Aashish", "Shrestha", 2, 1000);
        
        // Print the first and last name along with the initial balance of account1
        System.out.println("Account1 - Name: " + account1.getFirstName() + " " + account1.getLastName() + ", Balance: " + account1.getBalance());
        
        // Print the first and last name along with the initial balance of account2
        System.out.println("Account2 - Name: " + account2.getFirstName() + " " + account2.getLastName() + ", Balance: " + account2.getBalance());

        // Deposit 250 into account1
        account1.deposit(250);
        // Print the first and last name along with the balance of account1 after the deposit
        System.out.println("Account1 - Name: " + account1.getFirstName() + " " + account1.getLastName() + ", Balance after deposit: " + account1.getBalance());

        // Withdraw 500 from account2
        account2.withdraw(500);
        // Print the first and last name along with the balance of account2 after the withdrawal
        System.out.println("Account2 - Name: " + account2.getFirstName() + " " + account2.getLastName() + ", Balance after withdrawal: " + account2.getBalance());

        // Create a Transaction object to handle the transfer
        Transactions t = new Transactions();

        // Transfer 250 from account1 to account2
        t.transfer(account1, account2, 250);

        // Print the first and last name along with the final balance of account1 after the transfer
        System.out.println("Account1 - Name: " + account1.getFirstName() + " " + account1.getLastName() + ", Final balance: " + account1.getBalance());
        
        // Print the first and last name along with the final balance of account2 after the transfer
        System.out.println("Account2 - Name: " + account2.getFirstName() + " " + account2.getLastName() + ", Final balance: " + account2.getBalance());
    }
}
