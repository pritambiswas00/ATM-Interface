package src;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
    //Name of the Account;
    private String name;
    //List of the Accounts;
    private ArrayList<Account> accounts;
    //List of the users;
    private ArrayList<User> users;

    public Bank (String bankName){
        this.name = bankName;
        this.accounts = new ArrayList<Account>();
        this.users=new ArrayList<User>();
    }

    public String getNewUserUUID(){
         String UUID;
         Random random = new Random();
         int length=6;
         boolean nonQual;

         //Do while im doing because until we get the new UUID keep generating new one.
         do{
            UUID="";
            nonQual=false;
            for (int i=0;i<length;i++){
                UUID+=((Integer)random.nextInt(10)).toString();
            }
            for(User user : this.users){
                  if(UUID.compareTo(user.getUUID())==0){
                      nonQual=true;
                      break;
                  }
            }
         }while(nonQual);
        return UUID;
    };

    public String getNewAccountUUID(){
        String UUID;
        Random random = new Random();
        int length=6;
        boolean nonQual;

        //Do while im doing because until we get the new UUID keep generating new one.
        do{
            UUID="";
            nonQual=false;
            for (int i=0;i<length;i++){
                UUID+=((Integer)random.nextInt(10)).toString();
            }
            for(Account account : this.accounts){
                if(UUID.compareTo(account.getUUID())==0){
                    nonQual=true;
                    break;
                }
            }
        }while(nonQual);
        return UUID;
    };

    public User createNewUser(String firstName, String lastName, String secretCOde){
        User newUser = new User(firstName,lastName, secretCOde, this);
        this.users.add(newUser);
        Account account = new Account("savings", newUser, this);
        newUser.addAccount(account);
        this.accounts.add(account);
        return newUser;
    }

    public String getName() {
        return this.name;
    }

    public User login(String userID, String pin){
         for (User user : this.users){
             if(user.getUUID().compareTo(userID)==0&&user.checkPinNumber(pin)){
                  return user;
             }
         }
         return null;
    }

    public void addAccount(Account account){
         this.accounts.add(account);
    }

}

////35:27 seconds
