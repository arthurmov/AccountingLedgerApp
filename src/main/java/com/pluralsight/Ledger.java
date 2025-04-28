package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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
                    showScreenDeposits();
                    break;
                case "p":
                    System.out.println("\n[Displaying payment entries...]");
                    showScreenPayments();
                    break;
                case "r":
                    System.out.println("\n[Navigating to Reports menu...]");
                    showScreenReports();
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
            FileReader fr = new FileReader("transactions.csv");
            BufferedReader reader = new BufferedReader(fr);

            Transaction[] transactionTemp = new Transaction[100];
            int size = 0;
            String dataString;

            while ((dataString = reader.readLine()) != null) {

                transactionTemp[size] = getTransactionFromEncodedString(dataString);

                size++;
            }

            Transaction[] transactionFinal = Arrays.copyOf(transactionTemp, size);

            return transactionFinal;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Transaction getTransactionFromEncodedString(String encodedTransaction) {

        String[] temp = encodedTransaction.split(Pattern.quote("|"));

        String date = temp[0];
        String time = temp[1];
        String description = temp[2];
        String vendor = temp[3];
        double amount = Double.parseDouble(temp[4]);

        Transaction result = new Transaction(date, time, description, vendor, amount);
        return result;
    }

    private static void showScreenDeposits() {

    }

    private static void showScreenPayments() {

    }

    private static void showScreenReports() {

        String reportsScreenPrompt = "\nReports Menu\n" +
                "------------\n" +
                "[1] Month To Date    - Transactions from this month\n" +
                "[2] Previous Month   - Transactions from last month\n" +
                "[3] Year To Date     - Transactions from this year\n" +
                "[4] Previous Year    - Transactions from last year\n" +
                "[5] Search by Vendor - Find transactions by vendor\n" +
                "[0] Back             - Return to the ledger menu\n" +
                "\n" +
                "Enter your selection: ";

        int option;

        do {
            option = (int) console.promptForDouble(reportsScreenPrompt);

            switch (option) {
                case 1:
                    System.out.println("\n[Generating Month-To-Date report...]");
                    break;
                case 2:
                    System.out.println("\n[Generating Previous Month report...]");
                    break;
                case 3:
                    System.out.println("\n[Generating Year-To-Date report...]");
                    break;
                case 4:
                    System.out.println("\n[Generating Previous Year report...]");
                    break;
                case 5:
                    System.out.println("\n[Searching transactions by vendor...]");
                    break;
                case 0:
                    System.out.println( "\nReturning to Ledger Screen...\n" +
                            "Please wait.");
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
                    break;
            }
        } while(option != 0);
    }
}
