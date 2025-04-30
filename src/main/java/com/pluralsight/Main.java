package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {

    private static Console console = new Console();
    public static ArrayList<Transaction> transactionsList = new ArrayList<>(); //holds all entries in memory

    public static void main(String[] args) {

        System.out.println("\nWelcome to the Accounting Ledger App.\n" +
                "Manage your deposits, payments, and reports easily.\n");

        showScreenHome();

    }

    public static void showScreenHome() {

        String homeScreenPrompt =  "\nAccounting Ledger App\n" +
                "----------------------------------\n" +
                "[D] Add Deposit          - Record a deposit transaction\n" +
                "[P] Make Payment (Debit) - Record a payment or expense\n" +
                "[L] View Ledger          - View transaction history\n" +
                "[X] Exit                 - Close the application\n" +
                "\n" +
                "Enter your selection: ";

        String option;

        do {
            option = console.promptForString(homeScreenPrompt);
            option = option.toLowerCase();

            switch (option) {
                case "d":
                    System.out.println("\n[Navigating to Add Deposit screen...]");
                    showScreenAddTransaction(option);
                    break;
                case "p":
                    System.out.println("\n[Navigating to Make Payment screen...]");
                    showScreenAddTransaction(option);
                    break;
                case "l":
                    System.out.println("\n[Navigating to Ledger screen...]");
                    Ledger.showScreenLedger();
                    break;
                case "x":
                    System.out.println("\nThank you for using the Accounting Ledger App.\n" +
                            "Goodbye!\n");
                    break;
                default:
                    System.out.println(ColorCodes.RED + "\nInvalid option. Please try again." + ColorCodes.RESET);
                    break;
            }
        } while(!option.equals("x"));
    }

    private static void showScreenAddTransaction(String option) {

        LocalDate date;
        LocalTime time;

        double dateTimeInput = console.promptForDouble("\nWould you like to use the current Date and Time or input a custom Date and Time?\n" +
                "[1] Current\n" +
                "[2] Custom\n" +
                "Enter Your selection: ");

        if(dateTimeInput == 1) {
            //setting date and time variables to the current date and time
            date = LocalDate.now();
            time = LocalTime.now();
        }

        else if(dateTimeInput == 2) {
            //formats the date
            DateTimeFormatter dateFormatter;
            dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            String customDate = console.promptForString("\nEnter the Date: (MM/dd/yyyy)");
            date = LocalDate.parse(customDate,dateFormatter); //reformatting the users date input

            String customTime = console.promptForString("\nEnter the Time (24-hour format, HH:mm:ss): ");
            time = LocalTime.parse(customTime); //reformatting the users time input
        }

        else {
            //sets date and time to current by default
            System.out.println(ColorCodes.RED + "Invalid selection. Defaulting to current date and time." + ColorCodes.RESET);
            date = LocalDate.now();
            time = LocalTime.now();
        }

        String vendor = console.promptForString("\nEnter Vendor: ");
        double amount = console.promptForDouble("Enter amount: ");
        String description = console.promptForString("Enter Description: ");

        if (option.equalsIgnoreCase("p")) {
            amount = -Math.abs(amount); // Payments must be negative
        }

        try {
            FileWriter writer = new FileWriter("transactions.csv", true); //opens the file

            writer.write(String.format("\n%s|%s|%s|%s|%.2f", date, time, description, vendor, amount)); //writes user given data to the file

            writer.close(); //closes the file after finished writing

        } catch (IOException e) {
            System.out.println(ColorCodes.RED + "An unexpected error occurred while saving the transaction." + ColorCodes.RESET);
        }

        if (option.equalsIgnoreCase("d")) {
            System.out.println(ColorCodes.GREEN + "\nSuccessfully added a deposit!" + ColorCodes.RESET);
            
        } else if (option.equalsIgnoreCase("p")) {
            System.out.println(ColorCodes.GREEN + "\nSuccessfully made a payment!" + ColorCodes.RESET);
        }
    }
}