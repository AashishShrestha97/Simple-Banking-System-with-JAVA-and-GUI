package coursework;

import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Specify the relative path to the CSV file containing account data
        String relativePath = "C:\\Users\\Lenovo\\eclipse-workspace\\Coursework\\src\\coursework\\Accounts.csv"; 
        // Create an instance of the ReadAccounts class to read account data from the file
        ReadAccounts readAccounts = new ReadAccounts(relativePath);

        // Initialize LinkedLists to store account details
        LinkedList<String> firstNames = null;
        LinkedList<String> lastNames = null;
        LinkedList<Integer> accountNumbers = null;
        LinkedList<Integer> balances = null;

        try {
            // Attempt to read the account data from the CSV file
            firstNames = readAccounts.getFirstNames();
            lastNames = readAccounts.getLastNames();
            accountNumbers = readAccounts.getAccounts();
            balances = readAccounts.getBalances();
        } catch (IOException e) {
            // If an error occurs during reading, print the stack trace and exit the program
            e.printStackTrace();
            System.exit(1); // Exit the program with a non-zero status to indicate failure
        }

        
        // Create a LinkedList to store the Accounts objects
        LinkedList<Accounts> accounts = new LinkedList<>();
        // Check if the data was successfully loaded
        if (firstNames != null && lastNames != null && accountNumbers != null && balances != null) {
            // Iterate through the lists and create an Accounts object for each entry
            for (int i = 0; i < firstNames.size(); i++) {
                Accounts account = new Accounts(
                        firstNames.get(i),          
                        lastNames.get(i),          
                        accountNumbers.get(i),     
                        balances.get(i)          
                );
                // Add the Accounts object to the LinkedList of accounts
                accounts.add(account);
            }
        } else {
            // If data loading failed, print an error message and exit the program
            System.err.println("Failed to load account data.");
            System.exit(1); // Exit with a non-zero status to indicate failure
        }

        // Launch the GUI and pass the list of accounts to it
        new GUI(accounts).setVisible(true);  // Set visibility here to ensure GUI is shown
    }
}




