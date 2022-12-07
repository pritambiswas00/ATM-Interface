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
        this.uuid = theBank.getNewUUID();
        this.holder = holder;
        this.transactions = new ArrayList<Transaction>();
        holder.addAccount(this);
        theBank.addAccount(this);
    }
}
