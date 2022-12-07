package src;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class User {
    //Firstname of the user.
    private String firstName;
    //Lastname of the user.
    private String lastName;
    //Unique user id.
    private String uuid;
    //hash code of user atm pin
    private byte[] pinNumber;
    //List of account for that user;
    private ArrayList<Account> accounts;

    public User(String firstName, String lastName, String pinNumber, Bank theBank) {
        this.firstName = firstName;
        this.lastName = lastName;
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            this.pinNumber = messageDigest.digest(pinNumber.getBytes());
        }catch(NoSuchAlgorithmException error){
            System.err.println("Error from the User No such algorithms"+ error);
             error.printStackTrace();
        }
        this.uuid=theBank.getNewUserUUID();

        //hashing algorithms MD5 for security algorithm;
        this.accounts= new ArrayList<>();

        //Logging the message
        System.out.println("firstName = " + firstName + ", lastName = " + lastName + " UUID "+ uuid);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUUID() {
        return this.uuid;
    }

    public String addAccount(Account account){
         this.accounts.add(account);
         return "Account is opened for the User"+this.firstName+" "+this.lastName;
    }

    public boolean checkPinNumber(String pin){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return  MessageDigest.isEqual(messageDigest.digest(pin.getBytes()), this.pinNumber);
        }catch(NoSuchAlgorithmException error){
            System.err.println("Error from the User No such algorithms"+ error);
            error.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    public void printUserSummary(){
        System.out.printf("\n\n %s's accounts summary ", this.firstName);
        for(int i=0;i<this.accounts.size();i++){
            System.out.printf("%d--> %s\n",i+1,this.accounts.get(i).getSummaryLine());
        }
    }

    public int numberOfAccounts(){
        return this.accounts.size();
    }
    public void printTransactionHistory(int accountNumber){
        this.accounts.get(accountNumber).printTransactionHistory();
    }

    public double getLeftAccountBalance(int fromAccount){
         return this.accounts.get(fromAccount).getBalance();
    }

    public void processTrasaction(int accountIndex, double amount, String note){
            this.accounts.get(accountIndex).addTransaction(amount, note);
    }

    public String getAccountUUID(int accountIndex){
          return this.accounts.get(accountIndex).getUUID();
    }


}
