package com.pluralsight;

public class Main {

    private static Console console = new Console();

    public static void main(String[] args) {

        showScreenHome();

    }

    public static void showScreenHome() {

        String homeScreenPrompt = "home screen";

        String option;

        do {
            option = console.promptforString(homeScreenPrompt);

            switch (option) {
                case "d":
                    showScreenAddDeposit();
                    break;
                case "p":
                    showScreenMakePayment();
                    break;
                case "l":
                    showScreenLedger();
                    break;
                case "x":
                    System.out.println("Goodbye");
                    break;
            }
        } while(!option.equalsIgnoreCase("x"));
    }

    private static void showScreenAddDeposit() {

    }

    private static void showScreenMakePayment() {

    }

    private static void showScreenLedger() {

        String ledgerScreenPrompt = "ledger screen";

        String option;

        do {
            option = console.promptforString(ledgerScreenPrompt);

            switch (option) {
                case "a":
                    showScreenAllEntries();
                    break;
                case "d":
                    showScreenDeposits();
                    break;
                case "p":
                    showScreenPayments();
                    break;
                case "r":
                    showScreenReports();
                    break;
                case "h":
                    System.out.println("Returning to Home Page");
                    break;
            }
        } while(!option.equalsIgnoreCase("h"));
    }

    private static void showScreenAllEntries() {

    }

    private static void showScreenDeposits() {

    }

    private static void showScreenPayments() {

    }

    private static void showScreenReports() {

        String reportsScreenPrompt = "reports screen";

        int option;

        do {
            option = (int) console.promptForDouble(reportsScreenPrompt);

            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("Returning to Report Page");
                    break;
            }
        } while(option != 0);
    }

}