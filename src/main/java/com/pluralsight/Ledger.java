package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class Ledger {

    private static final Console console = new Console(); //create a console instance for handling user input
    private static List<Transaction> transactions = getAllEntries(); //load all transaction records from the CSV file into memory at startup

    public static void showScreenLedger() {

        //ledger screen prompt displayed to the user
        String ledgerScreenPrompt = ColorCodes.BLUE + "\nLedger Menu\n" + ColorCodes.RESET +
                "-----------\n" +
                "[A] All Entries   - View all transactions\n" +
                "[D] Deposits Only - View only deposit entries\n" +
                "[P] Payments Only - View only payment entries\n" +
                "[R] Reports       - Access financial reports\n" +
                "[H] Home          - Return to the home page\n" +
                "\n" +
                "Enter your selection: ";

        String option;

        //ledger loop runs until the user chooses to exit
        do {
            //prompt the user for input and normalize to lowercase
            option = console.promptForString(ledgerScreenPrompt);
            option = option.toLowerCase();

            //handle user's selection using a switch statement
            switch (option) {
                case "a":
                    System.out.println("\n[Displaying all entries...]");
                    showScreenEntries();
                    break;
                case "d":
                    System.out.println("\n[Displaying deposit entries...]");
                    showScreenDepositsOrPayments(option); //pass user input to filter by deposit
                    break;
                case "p":
                    System.out.println("\n[Displaying payment entries...]");
                    showScreenDepositsOrPayments(option); //pass user input to filter by payment
                    break;
                case "r":
                    System.out.println("\n[Navigating to Reports menu...]");
                    Report.showScreenReports(); //launch the reports screen
                    break;
                case "h":
                    System.out.println( "\nReturning to Home Page...\n" +
                            "Please wait."); //return message
                    break;
                default:
                    //handles invalid commands
                    System.out.println(ColorCodes.RED + "\nInvalid option. Please try again." + ColorCodes.RESET);
                    break;
            }
        } while(!option.equals("h")); //keep looping until user returns to home
    }

    //displays all transactions from the ledger (newest to oldest)
    private static void showScreenEntries() {
        transactions = getAllEntries(); //refresh from file

        System.out.println(Transaction.getFormattedLedgerTextHeader());

        //loop through each transaction and display its formatted details
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getFormattedLedgerText());
        }

        System.out.println( "\nReturning to Ledger Screen...\n" +
                "Please wait.");
    }

    public static List<Transaction> getAllEntries() {

        try {
            //open and read the csv file
            FileReader fr = new FileReader("transactions.csv");
            BufferedReader reader = new BufferedReader(fr);

            Main.transactionsList.clear(); //clear existing data before adding new

            String dataString;

            while ((dataString = reader.readLine()) != null) { //read the file line by line

                Transaction transaction = getTransactionFromEncodedString(dataString); //converts each line into a Transaction object
                Main.transactionsList.add(transaction); //adds each object to the global list
            }

            reader.close(); //closes the file after finished reading

            //sort transactions by newest first using date and time
            Main.transactionsList.sort(Comparator.comparing(
                    (Transaction t) -> LocalDateTime.of(t.getDate(), t.getTime())
            ));

            return Main.transactionsList; //returns full list of transactions

        // catch file reading problems
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Converts a single line of text from the CSV into a Transaction object
    private static Transaction getTransactionFromEncodedString(String encodedTransaction) {

        String[] temp = encodedTransaction.split(Pattern.quote("|")); //split the line using the pipe delimiter and trim whitespace

        //assigns each part to a variable
        LocalDate date = LocalDate.parse(temp[0].trim());
        LocalTime time = LocalTime.parse(temp[1].trim());
        String description = temp[2].trim();
        String vendor = temp[3].trim();
        double amount = Double.parseDouble(temp[4].trim());

        //return a new Transaction object with parsed data
        return new Transaction(date, time, description, vendor, amount);
    }

    private static void showScreenDepositsOrPayments(String option) {
        transactions = getAllEntries(); //refresh from file

        System.out.println(Transaction.getFormattedLedgerTextHeader());

        //loop through each transaction
        for (Transaction transaction : transactions) {
            if (option.equalsIgnoreCase("d") && transaction.getAmount() < 0) {
                continue; // skip payments if showing deposits
            }
            if (option.equalsIgnoreCase("p") && transaction.getAmount() > 0) {
                continue; // skip deposits if showing payments
            }

            System.out.println(transaction.getFormattedLedgerText());
        }

        System.out.println("\nReturning to Ledger Screen...\nPlease wait.");
    }
}