package coursework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;

class GUI extends JFrame {
    private LinkedList<Accounts> globalAccounts;
    private JLabel showAllData;
    private JButton showAllButton, depositButton, withdrawButton, transferButton;
    private JTextField accDeposit, depositInput, accWithdraw, withdrawInput, acc1Transfer, acc2Transfer, transferAmount;
    private Transactions transferObject = new Transactions();
    private StringBuilder sbAllData = new StringBuilder();
    private String csvFilePath = "C:\\Users\\Lenovo\\eclipse-workspace\\Coursework\\src\\coursework\\Accounts.csv";  // Path to your CSV file

    public GUI(LinkedList<Accounts> accounts) {
        super("Banking System");
        setLayout(null);
        this.globalAccounts = accounts;

        // Initialize UI components
        showAllData = new JLabel();
        showAllButton = new JButton("Show All Accounts");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");

        JLabel depositLabel = new JLabel("Deposit:");
        JLabel accDepositLabel = new JLabel("Account No:");
        JLabel depositAmountLabel = new JLabel("Amount:");

        JLabel withdrawLabel = new JLabel("Withdraw:");
        JLabel accWithdrawLabel = new JLabel("Account No:");
        JLabel withdrawAmountLabel = new JLabel("Amount:");

        JLabel transferLabel = new JLabel("Transfer:");
        JLabel acc1TransferLabel = new JLabel("From Account:");
        JLabel acc2TransferLabel = new JLabel("To Account:");
        JLabel transferAmountLabel = new JLabel("Amount:");

        accDeposit = new JTextField();
        depositInput = new JTextField();
        accWithdraw = new JTextField();
        withdrawInput = new JTextField();
        acc1Transfer = new JTextField();
        acc2Transfer = new JTextField();
        transferAmount = new JTextField();

        // Set bounds and colors
        showAllData.setBounds(20, 20, 500, 150);
        showAllData.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        showAllData.setOpaque(true); // Make sure the background is visible
        showAllData.setBackground(Color.WHITE);
        showAllData.setText(""); // Initially hide the account data
        showAllButton.setBounds(20, 180, 200, 30);

        depositLabel.setBounds(20, 220, 100, 30);
        accDepositLabel.setBounds(20, 260, 100, 30);
        accDeposit.setBounds(120, 260, 100, 30);
        depositAmountLabel.setBounds(230, 260, 100, 30);
        depositInput.setBounds(330, 260, 100, 30);
        depositButton.setBounds(450, 260, 100, 30);

        withdrawLabel.setBounds(20, 300, 100, 30);
        accWithdrawLabel.setBounds(20, 340, 100, 30);
        accWithdraw.setBounds(120, 340, 100, 30);
        withdrawAmountLabel.setBounds(230, 340, 100, 30);
        withdrawInput.setBounds(330, 340, 100, 30);
        withdrawButton.setBounds(450, 340, 100, 30);

        transferLabel.setBounds(20, 380, 100, 30);
        acc1TransferLabel.setBounds(20, 420, 100, 30);
        acc1Transfer.setBounds(120, 420, 100, 30);
        acc2TransferLabel.setBounds(230, 420, 100, 30);
        acc2Transfer.setBounds(330, 420, 100, 30);
        transferAmountLabel.setBounds(450, 420, 100, 30);
        transferAmount.setBounds(550, 420, 100, 30);
        transferButton.setBounds(670, 420, 100, 30);

        // Set colors
        showAllButton.setBackground(Color.LIGHT_GRAY);
        depositButton.setBackground(Color.CYAN);
        withdrawButton.setBackground(Color.ORANGE);
        transferButton.setBackground(Color.PINK);

        // Add components
        add(showAllData);
        add(showAllButton);
        add(depositLabel);
        add(accDepositLabel);
        add(depositInput);
        add(accDeposit);
        add(depositAmountLabel);
        add(depositButton);

        add(withdrawLabel);
        add(accWithdrawLabel);
        add(accWithdraw);
        add(withdrawAmountLabel);
        add(withdrawInput);
        add(withdrawButton);

        add(transferLabel);
        add(acc1TransferLabel);
        add(acc1Transfer);
        add(acc2TransferLabel);
        add(acc2Transfer);
        add(transferAmountLabel);
        add(transferAmount);
        add(transferButton);

        // Set actions
        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDisplayData(); // Update the display data when button is clicked
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (accDeposit.getText().isEmpty() || depositInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in both Account Number and Amount fields for deposit.");
                    return;
                }
                int accNum = Integer.parseInt(accDeposit.getText());
                int amount = Integer.parseInt(depositInput.getText());
                boolean accountFound = false;
                for (Accounts account : globalAccounts) {
                    if (account.getAccountNumber() == accNum) {
                        accountFound = true;
                        JOptionPane.showMessageDialog(null, "Attempting to deposit " + amount + " to Account: " + accNum);
                        account.deposit(amount);
                        updateDisplayData();
                        updateCSVFile(); // Update the CSV file after deposit
                        JOptionPane.showMessageDialog(null, "Deposit successful. CSV file updated.");
                        break;
                    }
                }
                if (!accountFound) {
                    JOptionPane.showMessageDialog(null, "Account number not found.");
                }
            }
        });


        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (accWithdraw.getText().isEmpty() || withdrawInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in both Account Number and Amount fields for withdrawal.");
                    return;
                }
                int accNum = Integer.parseInt(accWithdraw.getText());
                int amount = Integer.parseInt(withdrawInput.getText());
                boolean accountFound = false;
                for (Accounts account : globalAccounts) {
                    if (account.getAccountNumber() == accNum) {
                        accountFound = true;
                        if (account.getBalance() >= amount) {
                            JOptionPane.showMessageDialog(null, "Attempting to withdraw " + amount + " from Account: " + accNum);
                            account.withdraw(amount);
                            updateDisplayData();
                            updateCSVFile(); // Update the CSV file after withdrawal
                            JOptionPane.showMessageDialog(null, "Withdrawal successful. CSV file updated.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient balance for withdrawal.");
                        }
                        break;
                    }
                }
                if (!accountFound) {
                    JOptionPane.showMessageDialog(null, "Account number not found.");
                }
            }
        });


        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (acc1Transfer.getText().isEmpty() || acc2Transfer.getText().isEmpty() || transferAmount.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in From Account, To Account, and Amount fields for transfer.");
                    return;
                }
                int fromAcc = Integer.parseInt(acc1Transfer.getText());
                int toAcc = Integer.parseInt(acc2Transfer.getText());
                int amount = Integer.parseInt(transferAmount.getText());
                Accounts fromAccount = null, toAccount = null;

                for (Accounts account : globalAccounts) {
                    if (account.getAccountNumber() == fromAcc) {
                        fromAccount = account;
                    }
                    if (account.getAccountNumber() == toAcc) {
                        toAccount = account;
                    }
                }

                if (fromAccount != null && toAccount != null) {
                    if (fromAccount.getBalance() >= amount) {
                        JOptionPane.showMessageDialog(null, "Attempting to transfer " + amount + " from Account: " + fromAcc + " to Account: " + toAcc);
                        transferObject.transfer(fromAccount, toAccount, amount);
                        updateDisplayData();
                        updateCSVFile(); // Update the CSV file after transfer
                        JOptionPane.showMessageDialog(null, "Transfer successful. CSV file updated.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance for transfer.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "One or both account numbers not found.");
                }
            }
        });


        // Set frame properties
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void updateDisplayData() {
        sbAllData.setLength(0); // Clear the previous data
        for (Accounts account : globalAccounts) {
            sbAllData.append("Account: ").append(account.getAccountNumber())
                    .append(", Name: ").append(account.getFirstName()).append(" ").append(account.getLastName())
                    .append(", Balance: ").append(account.getBalance()).append("\n");
        }
        showAllData.setText("<html>" + sbAllData.toString().replace("\n", "<br>") + "</html>");
    }

    // Method to update the CSV file with the latest account data
    private void updateCSVFile() {
        System.out.println("Attempting to update CSV file...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (Accounts account : globalAccounts) {
                // Write data in the original order: firstName, lastName, accountNumber, balance
                writer.write(account.getFirstName() + "," +
                             account.getLastName() + "," +
                             account.getAccountNumber() + "," +
                             account.getBalance());
                writer.newLine(); // Move to the next line after each account
            }
            System.out.println("CSV file updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error updating CSV file: " + e.getMessage());
        }
    }


}
