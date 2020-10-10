import implementation.AccountImpl;
import implementation.TransactionImpl;
import implementation.UserImpl;
import models.User;

import java.util.Scanner;

public class App {
    static Scanner input=new Scanner(System.in);
    public static UserImpl userImpl = new UserImpl();
    public static AccountImpl accountImpl=new AccountImpl();
    public static TransactionImpl transactionImpl=new TransactionImpl();
    public static String loggedInUser;
    static String file;
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to YOYO Bank: ");
        int choice;
        do {
        System.out.println("\n Please Select an option: \n 1) Login \n 2) Register \n 3) Exit");
        choice=input.nextInt();

            switch (choice) {
                case 1:
                    loggedInUser=userImpl.Login();
                    loginSuccess();
                    break;

                case 2:
                    userImpl.Register(input);
                    System.out.println("Registration Successful Please login to continue");
                    break;

                case 3:
                    break;
            }
        }while (choice!=3);
        choice=0;
        System.out.println("Thanks for using YOYO Bank Services");

    }

    public static void loginSuccess()
    {

        System.out.println("Welcome User "+loggedInUser);
        file="C:/Users/saikrishnaboddu/Documents/New folder/Java-Project-MAD3463/YOYO Bank/Files/"+loggedInUser+".json";
        int choice;
        do {
            System.out.println("Please select and option :\n 1) Display Account Balance\n 2) Display Transactions\n 3) Deposit Money\n 4) Withdraw Money\n 5) Transfer Money\n 6) Utility Bill\n 0) Logout");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    accountImpl.DisplayBalance(file);
                    break;
                case 2://ToDo Display Transactions
                    break;
                case 3://ToDo Deposit
                    break;
                case 4:
                    transactionImpl.withDraw(loggedInUser, 20, file);
                    break;
                case 5://ToDo Transfer
                    break;
                case 6://ToDo Display Uitlity Bills
                    break;
                case 0://Control returns to main
                    break;
                default:
                    System.out.println("Enter a valid Option");
            }
        }while (choice!=0);

    }
}
