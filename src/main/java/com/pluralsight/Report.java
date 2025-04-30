package com.pluralsight;

import java.time.LocalDate;

public class Report {

    private static Console console = new Console();

    public static void showScreenReports() {

        String reportsScreenPrompt = ColorCodes.BLUE + "\nReports Menu\n" + ColorCodes.RESET +
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
                    showScreenMonthToDate();
                    break;
                case 2:
                    System.out.println("\n[Generating Previous Month report...]");
                    showScreenPreviousMonth();
                    break;
                case 3:
                    System.out.println("\n[Generating Year-To-Date report...]");
                    showScreenYearToDate();
                    break;
                case 4:
                    System.out.println("\n[Generating Previous Year report...]");
                    showScreenPreviousYear();
                    break;
                case 5:
                    System.out.println("\n[Searching transactions by vendor...]");
                    showScreenSearchByVendor();
                    break;
                case 6:
                    System.out.println("\n[Loading custom search...]");
                    break;
                case 0:
                    System.out.println( "\nReturning to Ledger Screen...\n" +
                            "Please wait.");
                    break;
                default:
                    System.out.println(ColorCodes.RED + "\nInvalid option. Please try again." + ColorCodes.RESET);
                    break;
            }
        } while(option != 0);
    }

    private static void showScreenMonthToDate() {

        System.out.println(Transaction.getFormattedLedgerTextHeader());

        for (Transaction transaction : Ledger.getAllEntries()) {
            LocalDate transactionDate = transaction.getDate();

            if (transactionDate.getYear() == LocalDate.now().getYear() &&
                    transactionDate.getMonth() == LocalDate.now().getMonth()) {

                System.out.println(transaction.getFormattedLedgerText());
            }
        }

        System.out.println( "\nReturning to Reports Screen...\n" +
                "Please wait.");
    }

    private static void showScreenPreviousMonth() {

        System.out.println(Transaction.getFormattedLedgerTextHeader());

        for (Transaction transaction : Ledger.getAllEntries()) {
            LocalDate transactionDate = transaction.getDate();

            if (transactionDate.getYear() == LocalDate.now().getYear() &&
                    transactionDate.getMonth() == LocalDate.now().getMonth().minus(1)) {

                System.out.println(transaction.getFormattedLedgerText());
            }
        }

        System.out.println( "\nReturning to Reports Screen...\n" +
                "Please wait.");
    }

    private static void showScreenYearToDate() {

        System.out.println(Transaction.getFormattedLedgerTextHeader());

        for (Transaction transaction : Ledger.getAllEntries()) {
            LocalDate transactionDate = transaction.getDate();

            if (transactionDate.getYear() == LocalDate.now().getYear()) {

                System.out.println(transaction.getFormattedLedgerText());
            }
        }

        System.out.println( "\nReturning to Reports Screen...\n" +
                "Please wait.");
    }

    private static void showScreenPreviousYear() {

        System.out.println(Transaction.getFormattedLedgerTextHeader());

        for (Transaction transaction : Ledger.getAllEntries()) {
            LocalDate transactionDate = transaction.getDate();

            if (transactionDate.getYear() == LocalDate.now().getYear()-1) {

                System.out.println(transaction.getFormattedLedgerText());
            }
        }

        System.out.println( "\nReturning to Reports Screen...\n" +
                "Please wait.");
    }

    private static void showScreenSearchByVendor() {

        String vendor = console.promptForString("\nEnter the vendor name: ");

        System.out.println(Transaction.getFormattedLedgerTextHeader());

        for (Transaction transaction : Ledger.getAllEntries()) {

            if (vendor.equalsIgnoreCase(transaction.getVendor())) {

                System.out.println(transaction.getFormattedLedgerText());
            }
        }

        System.out.println( "\nReturning to Reports Screen...\n" +
                "Please wait.");
    }
}