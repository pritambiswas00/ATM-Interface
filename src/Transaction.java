package src;

import java.util.Date;

public class Transaction {
    //Amount debit or credit to the account;
    private double amount;
    //Time of credited or debited
    private Date timestamp;
    //Note of the transaction;
    private String note;
    //Account details of the transactions;
    private Account accountDetails;
}
