package exercise1;

public class Transaction implements Runnable{
    private final Account account;
    private final boolean isDeposit;
    private final int amount;

    public Transaction(Account account, boolean isDeposit, int amount) {
        this.account = account;
        this.isDeposit = isDeposit;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (isDeposit) {
            account.deposit(amount);
            System.out.printf("Thread %s: Deposited %d%n", Thread.currentThread().getName(), amount);
        } else {
            account.withdraw(amount);
            System.out.printf("Thread %s: Withdrawn %d%n", Thread.currentThread().getName(), amount);
        }
    }
}
