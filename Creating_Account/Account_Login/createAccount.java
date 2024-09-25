// package CountriesTextFile;
package Account_Login;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Commmons.Utilities;
import Restaurant.Admin;
import Restaurant.Customers;

public class createAccount extends loginAbstract {
    Scanner sc = new Scanner(System.in);

     /////////////////////////**************////////////////////////////////////



    public createAccount(){
        //To accept user details while creating a new account we use constractor method

        
        
        // System.out.println(Utilities.ANSI_RED + "\n \t \t \t \t " + Utilities.ANSI_RESET);

        while (true) {

            int userChoice;
        // boolean jumpToMain = false;
       
        System.out.println("\n\n");
                System.out.println(Utilities.ANSI_CYAN + "\t \t \t \t 1. Create Admin Account" + Utilities.ANSI_RESET);
                System.out.println(Utilities.ANSI_CYAN + "\t \t \t \t 2. Create User Account" + Utilities.ANSI_RESET);
                System.out.println(Utilities.ANSI_CYAN + "\t \t \t \t 3. Jump to Main Menu" + Utilities.ANSI_RESET);
        

            try {
                System.out.print(Utilities.ANSI_CYAN + "\n\t\t\t\t => Enter your choice: " + Utilities.ANSI_RESET);
                userChoice = sc.nextInt();
                sc.nextLine(); // To consume the newline character
            } catch (Exception e) {
                Utilities.clearScreen();
                System.out.println(Utilities.ANSI_RED + "\n\t\t\t\t => Invalid input" + Utilities.ANSI_RESET);
                sc.nextLine(); // To consume the newline character
                continue;
            }

            Utilities.clearScreen();
           if(userChoice==1){
                System.out.println(Utilities.ANSI_CYAN + "\t \t \t \t Enter Admin   Pass Code" + Utilities.ANSI_RESET);
                String adminPass=sc.nextLine();
                if(adminPass.equals("admin")){
                    receiveAccountDetail();
                    writeOnFile("Admin.txt");
                    displayAccountDetail();
                    new Admin();
                    return;
                }
                else{
                    System.out.println(Utilities.ANSI_RED + "\t \t \t \t Invalid Pass Code" + Utilities.ANSI_RESET);
                    continue;
                }
            }
            else if(userChoice==2){
                    receiveAccountDetail();
                    writeOnFile("User.txt");
                    displayAccountDetail();
                    new Customers();
                    return;
            }
            else if(userChoice==3){
                    // jumpToMain= true;
                   return;
            }
            else if(userChoice==4){
                    System.out.println(Utilities.ANSI_RED + "\n\t\t\t\t => Thank you for using our service" + Utilities.ANSI_RESET);
                    System.exit(0);
                    return;
                
            }
            else{
                    System.out.println(Utilities.ANSI_RED + "\n\t\t\t\t => Invalid choice" + Utilities.ANSI_RESET);
                    return;
            }
                   
        }
            // if user wants to go back to main menu
            // if (jumpToMain) {
            //     break;
            // }
    }
   

    ////////////////////////////***************/////////////////////////////////

    // receive new account DETAILS
    public void receiveAccountDetail() {


        // Create the phoneNumber, accountNumber and fullName pattern to match to
        Pattern phoneNumberPattern = Pattern.compile("^[0-9]{5}$");
        Pattern accountNumberPattern = Pattern.compile("^[0-9]{5}$");
        Pattern fullNamePattern = Pattern.compile("^[a-zA-Z]{2,10} [a-zA-z]{2,10}$");
        Pattern passwordPattern = Pattern.compile(
                "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,8}$");

        // Create matcher to find if the pattern exists in the user input
        // for phoneNumber, accountNumber and fullName
        Matcher phoneMatcher = null, accounMatcher = null, nameMatcher = null, passwordMatcher = null;
        String prompt;

        ownerName = verifyUserInputREGEX(fullNamePattern, nameMatcher,
                ownerName, "\t Full name('firstname lastname(minimum 2 characters each'): ");
        phoneNumber = verifyUserInputREGEX(phoneNumberPattern, phoneMatcher,
                phoneNumber, "\t Phone Number(10 digits): ");
        bankAccount = verifyUserInputREGEX(accountNumberPattern, accounMatcher,
                bankAccount, "\t Acccount Number(10 digits): ");

        prompt = "\nMinimum 6 and maximum 8 characters\n";
        prompt += "at least one uppercase letter, one lowercase letter\n";
        prompt += "one number and one special character\n";
        prompt += "\t Enter Password: ";
        password = verifyUserInputREGEX(passwordPattern, passwordMatcher,
                password, prompt);

    }

    // Write userid, phonenumber, password, bankaccount to file
    public void writeOnFile(String fileToOpen) {
        try (BufferedWriter filewrite = new BufferedWriter(
                new FileWriter(fileToOpen, true))) {
            filewrite.newLine();

            int userId = Integer.parseInt(ID) + 1;
            //test statment
            System.out.println("userid parsed: " + userId);
            ID = userId + "";

            System.out.println("Static ID " + ID);
            filewrite.append("ID: " + userId + "\n");
            filewrite.append("Password: " + password + "\n");
            filewrite.append("Full Name: " + ownerName + "\n");
            filewrite.append("Phone Number: " + phoneNumber + "\n");
            filewrite.append("Bank Account: " + bankAccount + "\n");
            filewrite.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Displaying detail after creating a new account
    public void displayAccountDetail() {
        System.out.println("\nAccount Created successfully!\n");
        System.out.println("YOUR DETAILS:");
        System.out.println("         ID: " + ID);
        System.out.println("   PassWord: " + password);
        System.out.println("       Name: " + ownerName);
        System.out.println("    PhoneNo: " + phoneNumber);
        System.out.println("BankAccount: " + bankAccount);
    }

    // Use regular expression to match user inputs: phone, name and accountnumber
    public String verifyUserInputREGEX(Pattern pattern, Matcher matcher,
            String string, String prompt) {
        do {
            System.out.print(prompt);
            string = sc.nextLine();
            matcher = pattern.matcher(string);
        } while (!(matcher.find()));
        return string;
    }

}
