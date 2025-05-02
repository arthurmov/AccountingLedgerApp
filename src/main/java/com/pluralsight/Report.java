package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Report {

    private static final Console console = new Console();

    public static void showScreenReports() {

        //reports screen prompt displayed to the user
        String reportsScreenPrompt = ColorCodes.BLUE + "\nReports Menu\n" + ColorCodes.RESET +
                "------------\n" +
                "[1] Month To Date    - Transactions from this month\n" +
                "[2] Previous Month   - Transactions from last month\n" +
                "[3] Year To Date     - Transactions from this year\n" +
                "[4] Previous Year    - Transactions from last year\n" +
                "[5] Search by Vendor - Find transactions by vendor\n" +
                "[6] Custom Search    - Filter by date, vendor, description, or amount\n" +
                "[0] Back             - Return to the ledger menu\n" +
                "\n" +
                "Enter your selection: ";

        int option;

        //reports loop runs until the user chooses to exit
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
                    showScreenCustomSearch();
                    break;
                case 0:
                    System.out.println( "\nReturning to Ledger Screen...\n" +
                            "Please wait."); //return message
                    break;
                default:
                    //handles invalid commands
                    System.out.println(ColorCodes.RED + "\nInvalid option. Please try again." + ColorCodes.RESET);
                    break;
            }
        } while(option != 0);
    }

    private static void showScreenMonthToDate() {

        System.out.println(Transaction.getFormattedLedgerTextHeader());

        //loop through all transactions
        for (Transaction transaction : Ledger.getAllEntries()) {
            LocalDate transactionDate = transaction.getDate();

            //check if the transaction occurred in the current year and current month
            if (transactionDate.getYear() == LocalDate.now().getYear() &&
                    transactionDate.getMonth() == LocalDate.now().getMonth()) {

                //print the transaction if it matches the date criteria
                System.out.println(transaction.getFormattedLedgerText());
            }
        }

        System.out.println( "\nReturning to Reports Screen...\n" +
                "Please wait.");
    }

    private static void showScreenPreviousMonth() {

        System.out.println(Transaction.getFormattedLedgerTextHeader());

        //loop through all transactions
        for (Transaction transaction : Ledger.getAllEntries()) {
            LocalDate transactionDate = transaction.getDate();

            //check if the transaction occurred in the current month - 1 (previous month)
            if (transactionDate.getYear() == LocalDate.now().getYear() &&
                    transactionDate.getMonth() == LocalDate.now().getMonth().minus(1)) {

                //print the transaction if it matches the date criteria
                System.out.println(transaction.getFormattedLedgerText());
            }
        }

        System.out.println( "\nReturning to Reports Screen...\n" +
                "Please wait.");
    }

    private static void showScreenYearToDate() {

        System.out.println(Transaction.getFormattedLedgerTextHeader());

        //loop through all transactions
        for (Transaction transaction : Ledger.getAllEntries()) {
            LocalDate transactionDate = transaction.getDate();

            //check if the transaction occurred in the current year
            if (transactionDate.getYear() == LocalDate.now().getYear()) {

                //print the transaction if it matches the date criteria
                System.out.println(transaction.getFormattedLedgerText());
            }
        }

        System.out.println( "\nReturning to Reports Screen...\n" +
                "Please wait.");
    }

    private static void showScreenPreviousYear() {

        System.out.println(Transaction.getFormattedLedgerTextHeader());

        //loop through all transactions
        for (Transaction transaction : Ledger.getAllEntries()) {
            LocalDate transactionDate = transaction.getDate();

            //check if the transaction occurred in the current year - 1 (previous year)
            if (transactionDate.getYear() == LocalDate.now().getYear()-1) {

                //print the transaction if it matches the date criteria
                System.out.println(transaction.getFormattedLedgerText());
            }
        }

        System.out.println( "\nReturning to Reports Screen...\n" +
                "Please wait.");
    }

    private static void showScreenSearchByVendor() {

        //prompt the user for vendor input
        String vendor = console.promptForString("\nEnter the vendor name: ").trim();

        System.out.println(Transaction.getFormattedLedgerTextHeader());

        //loop through all transactions
        for (Transaction transaction : Ledger.getAllEntries()) {

            //compare the user input to every vendor in the file
            if (vendor.equalsIgnoreCase(transaction.getVendor())) {

                //print the transaction if it matches the date criteria
                System.out.println(transaction.getFormattedLedgerText());
            }
        }

        System.out.println( "\nReturning to Reports Screen...\n" +
                "Please wait.");
    }

    private static void showScreenCustomSearch() {

        //prompt for start date, allow skipping
        String startDateInput = console.promptForString("\nEnter Start Date (MM/dd/yyyy) or press Enter to skip: ");
        LocalDate startDate = null;
        if (!startDateInput.isBlank()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            try {
                startDate = LocalDate.parse(startDateInput, formatter); // Try parsing valid date
            } catch (DateTimeParseException e) {
                // Inform user if the input format is wrong
                System.out.println(ColorCodes.RED + "Invalid start date format." + ColorCodes.RESET);
            }
        }

        //prompt for end date, allow skipping
        String endDateInput = console.promptForString("Enter End Date (MM/dd/yyyy) or press Enter to skip: ");
        LocalDate endDate = null;
        if (!endDateInput.isBlank()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            try {
                endDate = LocalDate.parse(endDateInput, formatter);
            } catch (DateTimeParseException e) {
                System.out.println(ColorCodes.RED + "Invalid end date format." + ColorCodes.RESET);
            }
        }

        //prompt for vendor (optional)
        String vendorInput = console.promptForString("Enter Vendor (or press Enter to skip): ").trim();

        //prompt for description (optional)
        String descriptionInput = console.promptForString("Enter Description (or press Enter to skip): ").trim();

        //prompt for amount, but handle it as a string so Enter can be detected
        String amountInputRaw = console.promptForString("Enter Amount (or press Enter to skip): ").trim();
        Double amountInput = null;
        if (!amountInputRaw.isBlank()) {
            try {
                amountInput = Double.parseDouble(amountInputRaw); // parse as double
            } catch (NumberFormatException e) {
                System.out.println(ColorCodes.RED + "Invalid amount format." + ColorCodes.RESET);
            }
        }

        System.out.println(Transaction.getFormattedLedgerTextHeader());
        for (Transaction transaction : Ledger.getAllEntries()) {
            boolean matches = true;

            //Start Date Filter
            if (startDate != null && transaction.getDate().isBefore(startDate)) {
                matches = false;
            }

            //End Date Filter
            if (endDate != null && transaction.getDate().isAfter(endDate)) {
                matches = false;
            }

            //Vendor Filter
            if (!vendorInput.isBlank() && !transaction.getVendor().equalsIgnoreCase(vendorInput)) {
                matches = false;
            }

            //Description Filter
            if (!descriptionInput.isBlank() && !transaction.getDescription().toLowerCase().contains(descriptionInput.toLowerCase())) {
                matches = false;
            }

            //Amount Filter
            if (amountInput != null && transaction.getAmount() != amountInput) {
                matches = false;
            }

            //print the transactions that patch the input
            if (matches) {
                System.out.println(transaction.getFormattedLedgerText());
            }
        }

        System.out.println( "\nReturning to Reports Screen...\n" +
                "Please wait.");

    }
}