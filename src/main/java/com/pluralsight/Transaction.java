package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
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
        return    "\nDATE         TIME       DESCRIPTION                    VENDOR               AMOUNT ($)\n"
                + "------------ ---------- ------------------------------ -------------------- ----------";
    }
}
