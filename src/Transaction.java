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

    public Transaction(double amount, Account accountDetails) {
        this.amount = amount;
        this.timestamp = new Date();
        this.accountDetails = accountDetails;
    }
   public Transaction(double amount, String note, Account accountDetails){
         this.amount=amount;
         this.note=note;
         this.accountDetails=accountDetails;
   }

   public double getAmount(){
        return this.amount;
   }

   public String getSummaryLine(){
          if(this.amount>=0) {
              return String.format("%s : INR%0.2f : %s", this.timestamp.toString(), this.amount, this.note);
          }else{
              return String.format("%s : INR(%0.2f) : %s", this.timestamp.toString(), this, amount, this.note);
          }
   }


}
