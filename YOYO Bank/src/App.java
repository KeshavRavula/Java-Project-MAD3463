import implementation.UserImpl;
import models.User;

import java.util.Scanner;

public class App {
    static Scanner input=new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to YOYO Bank: ");
        UserImpl userimpl = new UserImpl();
        int choice;
        do {
        System.out.println("\n Please Select an option: \n 1) Login \n 2) Register \n 3) Exit");
        choice=input.nextInt();

            switch (choice) {
                case 1:
                    //ToDO add login functionality
                    userimpl.Login();
                    break;

                case 2:
                    userimpl.Register(input);
                    System.out.println("Registration Successful Please login to continue");
                    break;

                case 3:
                    break;
            }
        }while (choice!=3);
        System.out.println("Thanks for using YOYO Bank Services");

    }
}
