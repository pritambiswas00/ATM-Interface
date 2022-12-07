package src;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Array;
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
        this.uuid=theBank.getNewUUID();

        //hashing algorithms MD5 for security algorithm;
        this.accounts= new ArrayList<>();

        //Logging the message
        System.out.println("firstName = " + firstName + ", lastName = " + lastName + " UUID "+ uuid);
    }

    public String addAccount(Account account){
         return "";
    }
}
