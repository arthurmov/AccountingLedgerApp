package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {

    private static Console console = new Console();
    private static Transaction[] transactions = getAllEntries();

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

        String ledgerScreenPrompt = "\nledger screen";

        String option;

        do {
            option = console.promptforString(ledgerScreenPrompt);

            switch (option) {
                case "a":
                    showScreenEntries();
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

    private static void showScreenEntries() {
        System.out.println(Transaction.getFormattedLedgerTextHeader());
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getFormattedLedgerText());
        }
    }

    private static Transaction[] getAllEntries() {

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