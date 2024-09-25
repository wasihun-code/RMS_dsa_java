package Main;
import java.util.Scanner;

import Account_Login.createAccount;
import Account_Login.loginAccount;
import Commmons.Utilities;

public class RMS_Tester {
    public static void main(String[] args) {
        // Utilities.clearScreen();
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n\n");
                System.out.println(Utilities.ANSI_CYAN + "\t \t \t \t 1. Create Account" + Utilities.ANSI_RESET);
                System.out.println(Utilities.ANSI_CYAN + "\t \t \t \t 2. Login" + Utilities.ANSI_RESET);
                System.out.println(Utilities.ANSI_CYAN + "\t \t \t \t 3. Exit" + Utilities.ANSI_RESET);

                int choice;
                // Validate User input using -catch
                try { 
                    System.out.print(Utilities.ANSI_CYAN + "\n\t\t\t\t =>Enter your choice: " + Utilities.ANSI_RESET);
                    choice = sc.nextInt();
                    sc.nextLine(); // To consume the newline character
                } catch (Exception e) {
                    Utilities.clearScreen();
                    System.out.println(Utilities.ANSI_RED + "\n\t\t\t\t =>Invalid Input" + Utilities.ANSI_RESET);
                    sc.nextLine(); // To consume the newline character
                    continue;
                }

                Utilities.clearScreen();
                switch (choice) {
                    case 1:
                        new createAccount();
                        break;
                    case 2:
                        new loginAccount();
                        break;
                    case 3:
                        System.out.println(Utilities.ANSI_RED + "\n\t\t\t\t =>Thank you for using our service" + Utilities.ANSI_RESET);
                        System.exit(0);
                        break;
                    default:
                        System.out.println(Utilities.ANSI_RED + "I\n\t\t\t\t =>nvalid choice" + Utilities.ANSI_RESET);
                        break;
                }
            }
        }
    }
}