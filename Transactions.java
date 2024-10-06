package coursework;

public class Transactions {

    public void transfer(Accounts sourceAccount, Accounts destinationAccount, int amount) {
        if (sourceAccount.getBalance() >= amount) {
            sourceAccount.withdraw(amount);
            destinationAccount.deposit(amount);
        } else {
            System.out.println("Insufficient balance in source account");
        }
    }
}
