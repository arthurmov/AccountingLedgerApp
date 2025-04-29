package com.pluralsight;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Report {

    private static Console console = new Console();

    public static void showScreenReports() {

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

    private static void showScreenMonthToDate() {

    }

    private static void showScreenPreviousMonth() {

    }

    private static void showScreenYearToDate() {

    }

    private static void showScreenPreviousYear() {

    }

    private static void showScreenSearchByVendor() {
        String vendor = console.promptforString("Enter the vendor name: ");


    }
}
