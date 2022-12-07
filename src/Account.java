package src;

import java.util.ArrayList;

public class Account {
    //Name of the account;
    private String name;
    //Balance in the account;
    private double balance;
    //Unique id for that account;
    private String uuid;
    //Account holder;
    private User holder;
    //Number of transactions made by the user;
    private ArrayList<Transaction> transactions;

    public Account(String name, User holder, Bank theBank) {
        this.name = name;
        this.balance = balance;
        this.uuid = theBank.getNewAccountUUID();
        this.holder = holder;
        this.transactions = new ArrayList<Transaction>();
    }

    public String getUUID() {
        return this.uuid;
    }

    public String getSummaryLine(){
        double balance = this.getBalance();
        if(balance >=0){
            return String.format("%s : INR%.02f : %s", this.uuid, balance, this.name);
        }else{
            return String.format("%s : INR(%.02f) : %s", this.uuid, balance, this.name);
        }
    }

    public double getBalance(){
        double balance = 0;
        for(Transaction transaction : this.transactions){
            balance+=transaction.getAmount();
        }
        return balance;
    }
    public void printTransactionHistory(){
        System.out.printf("\n Transaction History for the account %s \n", this.uuid);
        for(int transactionIndex = this.transactions.size()-1; transactionIndex>=0;transactionIndex--){
            System.out.printf(this.transactions.get(transactionIndex).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String note){
          Transaction newTransaction = new Transaction(amount, note,this);
          this.transactions.add(newTransaction);
    }
}
