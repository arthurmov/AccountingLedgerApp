package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Ledger {

    private static Console console = new Console();
    private static Transaction[] transactions = getAllEntries();

    public static void showScreenLedger() {

        String ledgerScreenPrompt = "\nLedger Menu\n" +
                "-----------\n" +
                "[A] All Entries   - View all transactions\n" +
                "[D] Deposits Only - View only deposit entries\n" +
                "[P] Payments Only - View only payment entries\n" +
                "[R] Reports       - Access financial reports\n" +
                "[H] Home          - Return to the home page\n" +
                "\n" +
                "Enter your selection: ";

        String option;

        do {
            option = console.promptforString(ledgerScreenPrompt);
            option = option.toLowerCase();

            switch (option) {
                case "a":
                    System.out.println("\n[Displaying all entries...]");
                    showScreenEntries();
                    break;
                case "d":
                    System.out.println("\n[Displaying deposit entries...]");
                    showScreenDepositsOrPayments(option);
                    break;
                case "p":
                    System.out.println("\n[Displaying payment entries...]");
                    showScreenDepositsOrPayments(option);
                    break;
                case "r":
                    System.out.println("\n[Navigating to Reports menu...]");
                    Report.showScreenReports();
                    break;
                case "h":
                    System.out.println( "\nReturning to Home Page...\n" +
                            "Please wait.");
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
                    break;
            }
        } while(!option.equals("h"));
    }

    private static void showScreenEntries() {
        System.out.println(Transaction.getFormattedLedgerTextHeader());
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getFormattedLedgerText());
        }

        System.out.println( "\nReturning to Ledger Screen...\n" +
                "Please wait.");
    }

    public static Transaction[] getAllEntries() {

        try {
            FileReader fr = new FileReader("transactions.csv"); //open and read the file
            BufferedReader reader = new BufferedReader(fr);

            Main.transactionsList.clear(); //clear existing data before adding new

            String dataString;

            while ((dataString = reader.readLine()) != null) { //read the file line by line

                Transaction transaction = getTransactionFromEncodedString(dataString);
                Main.transactionsList.add(transaction);
            }

            reader.close();

            Transaction[] transactions = new Transaction[Main.transactionsList.size()];
            transactions = Main.transactionsList.toArray(transactions);

            return transactions; //return final array

        // catch file reading problems
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Transaction getTransactionFromEncodedString(String encodedTransaction) {

        String[] temp = encodedTransaction.split(Pattern.quote("|")); //split the line

        //assigns each part to a variable
        String date = temp[0];
        String time = temp[1];
        String description = temp[2];
        String vendor = temp[3];
        double amount = Double.parseDouble(temp[4]);

        //return all info
        return new Transaction(date, time, description, vendor, amount);
    }

    private static void showScreenDepositsOrPayments(String option) {
        System.out.println(Transaction.getFormattedLedgerTextHeader());

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
