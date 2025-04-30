package com.pluralsight;

import java.time.LocalDate;

public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public String getFormattedLedgerText() {
        return String.format("%-12s %-10s %-30s %-20s %10.2f",
                this.date, this.time, this.description, this.vendor, this.amount);
    }

    public static String getFormattedLedgerTextHeader() {
        return    "\nDATE         TIME       DESCRIPTION                    VENDOR               AMOUNT\n"
                + "------------ ---------- ------------------------------ -------------------- ----------";
    }
}
