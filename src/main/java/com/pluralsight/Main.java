package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

    private static Console console = new Console();

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

        String vendor = console.promptforString("\nEnter Vendor: ");
        double amount = console.promptForDouble("Enter amount: ");
        String description = console.promptforString("Enter Description: ");

        try {
            FileWriter writer = new FileWriter("transactions.csv", true);

            writer.write(String.format("\n%s|%s|%s|%s|%.2f", LocalDate.now(),
                    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), description, vendor, amount));

            writer.close();

        } catch (IOException e) {
            System.out.println("An unexpected error occurred.");
        }

        System.out.println("\nSuccessfully added a deposit!");
    }

    private static void showScreenMakePayment() {
        String vendor = console.promptforString("\nEnter Vendor: ");
        double amount = console.promptForDouble("Enter amount: ");
        String description = console.promptforString("Enter Description: ");

        amount = -Math.abs(amount); // payment is negative

        try {
            FileWriter writer = new FileWriter("transactions.csv", true);

            writer.write(String.format("\n%s|%s|%s|%s|%.2f", LocalDate.now(),
                    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), description, vendor, amount));

            writer.close();

        } catch (IOException e) {
            System.out.println("An unexpected error occurred.");
        }

        System.out.println("\nSuccessfully made a payment!");
    }
}