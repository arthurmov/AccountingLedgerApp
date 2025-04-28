package com.pluralsight;

public class Main {

    private static Console console = new Console();
    private static Transaction[] transactions = Ledger.getAllEntries();

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
            option = console.promptforString(homeScreenPrompt);
            option = option.toLowerCase();

            switch (option) {
                case "d":
                    System.out.println("\n[Navigating to Add Deposit screen...]");
                    showScreenAddDeposit();
                    break;
                case "p":
                    System.out.println("\n[Navigating to Make Payment screen...]");
                    showScreenMakePayment();
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
                    System.out.println("\nInvalid option. Please try again.");
                    break;
            }
        } while(!option.equals("x"));
    }

    private static void showScreenAddDeposit() {

    }

    private static void showScreenMakePayment() {

    }
}