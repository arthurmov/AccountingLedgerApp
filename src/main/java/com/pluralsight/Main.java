package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Main {

    private static final Console console = new Console(); //create a console instance for handling user input
    public static ArrayList<Transaction> transactionsList = new ArrayList<>(); //holds all entries in memory

    public static void main(String[] args) {

        //greet the user when the application starts
        System.out.println("\nWelcome to the Accounting Ledger App.\n" +
                "Manage your deposits, payments, and reports easily.\n");

        showScreenHome(); //launch the home screen

    }

    public static void showScreenHome() {

        //main menu prompt displayed to the user
        String homeScreenPrompt =  ColorCodes.BLUE + "\nAccounting Ledger App\n" + ColorCodes.RESET +
                "----------------------------------\n" +
                "[D] Add Deposit          - Record a deposit transaction\n" +
                "[P] Make Payment (Debit) - Record a payment or expense\n" +
                "[L] View Ledger          - View transaction history\n" +
                "[X] Exit                 - Close the application\n" +
                "\n" +
                "Enter your selection: ";

        String option;

        //menu loop runs until the user chooses to exit
        do {
            //prompt the user for input and normalize to lowercase
            option = console.promptForString(homeScreenPrompt);
            option = option.toLowerCase();

            //handle user's selection using a switch statement
            switch (option) {
                case "d":
                    System.out.println("\n[Navigating to Add Deposit screen...]");
                    showScreenAddTransaction(option); //pass user input to handle deposit logic
                    break;
                case "p":
                    System.out.println("\n[Navigating to Make Payment screen...]");
                    showScreenAddTransaction(option); //pass user input to handle payment logic
                    break;
                case "l":
                    System.out.println("\n[Navigating to Ledger screen...]");
                    Ledger.showScreenLedger(); //launch the ledger screen
                    break;
                case "x":
                    System.out.println("\nThank you for using the Accounting Ledger App.\n" +
                            "Goodbye!\n"); //exit message
                    break;
                default:
                    //handles invalid commands
                    System.out.println(ColorCodes.RED + "\nInvalid option. Please try again." + ColorCodes.RESET);
                    break;
            }
        } while(!option.equals("x")); //keep looping until user exits
    }

    private static void showScreenAddTransaction(String option) {

        LocalDate date = null;
        LocalTime time = null;

        //prompt the user for input
        double dateTimeInput = console.promptForDouble("\nWould you like to use the current Date and Time or input a custom Date and Time?\n" +
                "[1] Current\n" +
                "[2] Custom\n" +
                "Enter Your selection: ");

        if (dateTimeInput == 1) {
            //setting date and time variables to the current date and time
            date = LocalDate.now();
            time = LocalTime.now();
        } else if (dateTimeInput == 2) {
            LocalDateTime customDateTime = customDateAndTime();
            date = customDateTime.toLocalDate();
            time = customDateTime.toLocalTime();
        } else {
            //sets date and time to current by default
            System.out.println(ColorCodes.YELLOW + "\nInvalid selection. Defaulting to current date and time." + ColorCodes.RESET);
            date = LocalDate.now();
            time = LocalTime.now();
        }

        String vendor = console.promptForString("\nEnter Vendor: ").trim();
        double amount = console.promptForDouble("Enter amount: ");
        String description = console.promptForString("Enter Description: ").trim();

        if (option.equalsIgnoreCase("p")) {
            amount = -Math.abs(amount); // Payments must be negative
        }

        String formattedTime = null;

        try {
            FileWriter writer = new FileWriter("transactions.csv", true); //opens the file

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            formattedTime = time.format(timeFormatter);

            writer.write(String.format("\n%s|%s|%s|%s|%.2f", date, formattedTime, description, vendor, amount)); //writes user given data to the file

            writer.close(); //closes the file after finished writing

        } catch (IOException e) {
            System.out.println(ColorCodes.RED + "An unexpected error occurred while saving the transaction." + ColorCodes.RESET);
        }

        if (option.equalsIgnoreCase("d")) {
            System.out.println(ColorCodes.GREEN + "\nSuccessfully added a deposit!" + ColorCodes.RESET);
        } else if (option.equalsIgnoreCase("p")) {
            System.out.println(ColorCodes.GREEN + "\nSuccessfully made a payment!" + ColorCodes.RESET);
        }

        // Print the transaction details
        System.out.printf("\nEntry Details:\n%s|%s|%s|%s|%.2f\n\n",
                date, formattedTime, description, vendor, amount);

    }

    private static LocalDateTime customDateAndTime() {

        LocalDate date;
        LocalTime time;

        //formats the date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Loop until the user enters a valid date in MM/dd/yyyy format
        while (true) {
            try {
                String customDate = console.promptForString("\nEnter the Date: (MM/dd/yyyy)");
                date = LocalDate.parse(customDate, dateFormatter); // Parse user input to LocalDate
                break; // Exit the loop if parsing is successful
            } catch (DateTimeParseException e) {
                // Handle incorrect format input and prompt again
                System.out.println(ColorCodes.RED + "\nInvalid date format. Please use MM/dd/yyyy." + ColorCodes.RESET);
            }
        }

        // Loop until the user enters a valid time in HH:mm:ss 24-hour format
        while (true) {
            try {
                String customTime = console.promptForString("\nEnter the Time (24-hour format, HH:mm:ss): ");
                time = LocalTime.parse(customTime, timeFormatter); // Parse user input to LocalTime
                break; // Exit the loop if parsing is successful
            } catch (DateTimeParseException e) {
                // Handle incorrect format input and prompt again
                System.out.println(ColorCodes.RED + "\nInvalid time format. Please use HH:mm:ss (24-hour)." + ColorCodes.RESET);
            }
        }
        return LocalDateTime.of(date, time);
    }
}