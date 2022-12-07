import src.Account;
import src.Bank;
import src.User;

import java.util.Scanner;

public class ATM {
      public static void main(String[] args){
          System.out.println("Lets Run it");
          Scanner newScanner = new Scanner(System.in);
          Bank myBank = new Bank("Bank of Pritam");
          ///Add a user //savings account
          User newUser = myBank.createNewUser("Pritam","Biswas", "1234");
          Account newAccount = new Account("Checking", newUser, myBank);
          newUser.addAccount(newAccount);
          myBank.addAccount(newAccount);
          System.out.println(myBank);
          User currentUser;
          while(true){
              currentUser = ATM.mainMenuPrompt(myBank, newScanner);
              ATM.printUserMenu(currentUser, newScanner);
          }
      }

      public static User mainMenuPrompt(Bank myBank, Scanner sc){
          String userId;
          String pin;
          User authenticateUser;

          do{
             System.out.printf("\n\nWelcome to the %s\n\n", myBank.getName());
             System.out.print("Enter your userID : ");
             userId = sc.nextLine();
             System.out.print("Enter your pin : ");
             pin = sc.nextLine();
             authenticateUser = myBank.login(userId, pin);
             if(authenticateUser==null){
                 System.out.println("Sorry. Please provide the valid IDs");
             }
          }while(authenticateUser==null);

          return authenticateUser;
      }

      public static void printUserMenu(User currentUser, Scanner newScanner){
          currentUser.printUserSummary();
          int choice;
          do{
              System.out.printf("Welcome %s , What would you like to do\n", currentUser.getFirstName());
              System.out.println("1: Show all the transaction history.");
              System.out.println("2: Withdrawal Money.");
              System.out.println("3: Deposit Money.");
              System.out.println("4: Transfer Money.");
              System.out.println("5: Logout Account.");
              System.out.println("\n Enter your choice : ");
              choice = newScanner.nextInt();
              if(choice<1 || choice>5){
                  System.out.println("Invalid choice. Please select 1 to 5");
              }
          }while(choice<1||choice>5);
          switch (choice) {
              case 1 -> ATM.showCurrentUserTransactionHistory(currentUser, newScanner);
              case 2 -> ATM.withDrawlFunds(currentUser, newScanner);
              case 3 -> ATM.depositFunds(currentUser, newScanner);
              case 4 -> ATM.transactionFunds(currentUser, newScanner);
          }

          if(choice!=5){
               ATM.printUserMenu(currentUser, newScanner);
          }
      }

      public static  void showCurrentUserTransactionHistory(User currentUser, Scanner newScanner){
            int myAccount;
            do{
                System.out.printf("Enter the number (1-%d) of your account\n"+ "to see the transactions: ", currentUser.numberOfAccounts());
                myAccount = newScanner.nextInt()-1;
                if(myAccount<0||myAccount>=currentUser.numberOfAccounts()){
                    System.out.println("Invalid account. Please try again.");
                }
            }while(myAccount<0||myAccount>=currentUser.numberOfAccounts());
            currentUser.printTransactionHistory(myAccount);
      }
      public static void transactionFunds(User currentUser, Scanner newScanner){
            int fromAccount;
           int toAccount;
           double amount;
           double accountBalance;
           do{
                System.out.printf("Enter the number (1-%d) of the account\n"+" to transfer from : ");
                fromAccount
 = newScanner.nextInt();
                if(fromAccount
<0||fromAccount
>=currentUser.numberOfAccounts()){
                    System.out.println("Invalid account. Please try again.");
                }
           }while(fromAccount
<0||fromAccount
>=currentUser.numberOfAccounts());
           accountBalance = currentUser.getLeftAccountBalance(fromAccount
);
          do{
              System.out.printf("Enter the number (1-%d) of the account\n"+" to transfer to : ");
              toAccount = newScanner.nextInt();
              if(toAccount<0||toAccount>=currentUser.numberOfAccounts()){
                  System.out.println("Invalid account. Please try again.");
              }
          }while(toAccount<0||toAccount>=currentUser.numberOfAccounts());
          do{
              System.out.printf("Enter the amount to transfer (max INR %.02f) : INR ", accountBalance);
              amount = newScanner.nextDouble();
              if(amount<0){
                  System.out.println("Amount must be greater than 0");
              }else if(amount>accountBalance){
                  System.out.println("Invalid amount. Please provide a valid amount");
              }
          }while(amount<0||amount>accountBalance);

          currentUser.processTrasaction(fromAccount, -1*amount, String.format("Transfer to account %s ", currentUser.getAccountUUID(toAccount)));
      }
      public static void withDrawlFunds(User currentUser, Scanner newScanner){
          int fromAccount;
          int toAccount;
          double amount;
          double accountBalance;
          String note;
          do{
              System.out.printf("Enter the number (1-%d) of the account\n"+" to transfer from : ");
              fromAccount = newScanner.nextInt();
              if(fromAccount<0||fromAccount>=currentUser.numberOfAccounts()){
                  System.out.println("Invalid account. Please try again.");
              }
          }while(fromAccount<0||fromAccount>=currentUser.numberOfAccounts());
          accountBalance = currentUser.getLeftAccountBalance(fromAccount);
          do{
              System.out.printf("Enter the number (1-%d) of the account\n"+" to transfer to : ");
              toAccount = newScanner.nextInt();
              if(toAccount<0||toAccount>=currentUser.numberOfAccounts()){
                  System.out.println("Invalid account. Please try again.");
              }
          }while(toAccount<0||toAccount>=currentUser.numberOfAccounts());
          do{
              System.out.printf("Enter the amount to transfer (max INR %.02f) : INR ", accountBalance);
              amount = newScanner.nextDouble();
              if(amount<0){
                  System.out.println("Amount must be greater than 0");
              }else if(amount>accountBalance){
                  System.out.println("Invalid amount. Please provide a valid amount");
              }
          }while(amount<0||amount>accountBalance);
          newScanner.nextLine();
          System.out.println("Enter a memo : ");
          note =  newScanner.nextLine();
          currentUser.processTrasaction(fromAccount, -1*amount, note);
      }
      public static void depositFunds(User currentUser, Scanner newScanner){

          int toAccount;
          double amount;
          double accountBalance;
          String note;
          do{
              System.out.printf("Enter the number of the account\n"+" to deposit from : ");
              toAccount = newScanner.nextInt();
              if(toAccount<0||toAccount>=currentUser.numberOfAccounts()){
                  System.out.println("Invalid account. Please try again.");
              }
          }while(toAccount<0||toAccount>=currentUser.numberOfAccounts());
          accountBalance = currentUser.getLeftAccountBalance(toAccount);
          do{
              System.out.printf("Enter the amount to transfer (max INR %.02f) : INR ", accountBalance);
              amount = newScanner.nextDouble();
              if(amount<0){
                  System.out.println("Amount must be greater than 0");
              }else if(amount>accountBalance){
                  System.out.println("Invalid amount. Please provide a valid amount");
              }
          }while(amount<0||amount>accountBalance);
          newScanner.nextLine();
          System.out.println("Enter a memo : ");
          note =  newScanner.nextLine();
          currentUser.processTrasaction(toAccount, amount, note);
      }
}
