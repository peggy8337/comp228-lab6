package exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account();
        List<Transaction> transactions = new ArrayList<Transaction>();

        //create multiple transactions
        transactions.add(new Transaction(account, true, 100)); //deposit 100
        transactions.add(new Transaction(account, false, 50)); //withdraw 50
        transactions.add(new Transaction(account, true, 200)); //deposit 200
        transactions.add(new Transaction(account, false, 100)); //withdraw 100
        transactions.add(new Transaction(account, true, 300)); //deposit 300

        //create 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //execute transactions
        for (Transaction transaction : transactions) {
            executorService.execute(transaction);
        }

        //shut down executorService and wait for tasks to complete
        executorService.shutdown();
        try{
            if(!executorService.awaitTermination(1, TimeUnit.MINUTES)){
                executorService.shutdownNow();
            }
        }catch (InterruptedException e){
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.printf("Final Balance: %d%n", account.getBalance() );
    }
}
