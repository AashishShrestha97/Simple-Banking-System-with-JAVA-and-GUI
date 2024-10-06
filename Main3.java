package coursework;

import java.io.IOException;
import java.util.LinkedList;

public class Main3 {
    public static void main(String[] args) {

        LinkedList<Accounts> accounts = new LinkedList<>();
        ReadAccounts readAccounts = new ReadAccounts("src/coursework/Accounts.csv");

        LinkedList<String> firstNames = null;
        LinkedList<String> lastNames = null;
        LinkedList<Integer> accountNumbers = null;
        LinkedList<Integer> balances = null;

        try {
            firstNames = readAccounts.getFirstNames();
            lastNames = readAccounts.getLastNames();
            accountNumbers = readAccounts.getAccounts();
            balances = readAccounts.getBalances();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Check if lists are loaded and of the same size
        if (firstNames == null || lastNames == null || accountNumbers == null || balances == null) {
            System.err.println("Failed to load account data. Exiting.");
            System.exit(1);
        }

        if (firstNames.size() != lastNames.size() || firstNames.size() != accountNumbers.size() || firstNames.size() != balances.size()) {
            System.err.println("Data mismatch: Lists are not of the same size. Exiting.");
            System.exit(1);
        }

        // Populate the accounts list
        for (int i = 0; i < firstNames.size(); i++) {
            accounts.add(new Accounts(firstNames.get(i), lastNames.get(i), accountNumbers.get(i), balances.get(i)));
        }

        // Check if there are at least 4 accounts in the list
        if (accounts.size() >= 4) {
            Accounts account1 = accounts.get(0);
            Accounts account2 = accounts.get(1);
            Accounts account3 = accounts.get(2);
            Accounts account4 = accounts.get(3);

            System.out.println("Name of account 1: " + account1.getFirstName() + account1.getLastName());
            System.out.println("Name of account 2: " + account2.getFirstName() + account2.getLastName());
            System.out.println("Name of account 3: " + account3.getFirstName() + account3.getLastName());
            System.out.println("Name of account 4: " + account4.getFirstName() + account4.getLastName());
            
            System.out.println("Account number of account 1: " + account1.getAccountNumber());
            System.out.println("Account number of account 2: " + account2.getAccountNumber());
            System.out.println("Account number of account 3: " + account3.getAccountNumber());
            System.out.println("Account number of account 4: " + account4.getAccountNumber());

            System.out.println("Balance of account 1: " + account1.getBalance());
            System.out.println("Balance of account 2: " + account2.getBalance());
            System.out.println("Balance of account 3: " + account3.getBalance());
            System.out.println("Balance of account 4: " + account4.getBalance());
            
            account1.deposit(250);
            System.out.println("Balance of account 1 after deposit: " + account1.getBalance());

            account2.withdraw(500);
            System.out.println("Balance of account 2 after withdrawal: " + account2.getBalance());

            Transactions t = new Transactions();
            t.transfer(account1, account2, 250);
            System.out.println("Balance of account 1 after transfer: " + account1.getBalance());
            System.out.println("Balance of account 2 after receiving transfer: " + account2.getBalance());
        } else {
            System.err.println("Not enough accounts loaded. Exiting.");
            System.exit(1);
        }
    }
}
