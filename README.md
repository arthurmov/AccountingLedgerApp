# Accounting Ledger App (Java CLI)

A simple, console-based accounting ledger application built in Java. This app allows users to track deposits and payments, view a complete transaction ledger, and generate custom financial reports — all via a user-friendly command-line interface.

---

## Features

- [x] Add **Deposits** and **Payments**
- [x] All transactions saved to `transactions.csv`
- [x] Color-coded feedback for successful entries, errors, and prompts
- [x] Input validation with support for canceling an action using `0`
- [x] Optional manual date/time entry in `MM/dd/yyyy` and `HH:mm:ss` formats

### Home Menu
- `[D]` Add Deposit — Record a deposit transaction
- `[P]` Make Payment (Debit) — Record a payment or expense
- `[L]` View Ledger — View transaction history
- `[X]` Exit — Close the application

### Ledger Menu
- `[A]` All Entries — View all transactions
- `[D]` Deposits Only — View only deposit transactions
- `[P]` Payments Only — View only payment transactions
- `[R]` Reports — Open the reporting menu
- `[H]` Home — Return to the home page

### Reports Menu
- `[1]` Month-To-Date — Transactions from the current month
- `[2]` Previous Month — Transactions from the previous month
- `[3]` Year-To-Date — Transactions from the current year
- `[4]` Previous Year — Transactions from the previous year
- `[5]` Search by Vendor — Filter transactions by vendor name
- `[6]` Custom Search — Search by date range, vendor, description, or amount
- `[0]` Back — Return to the ledger menu


---

## File Structure

```bash
├── Main.java              # Entry point and main navigation
├── Transaction.java       # Data model for ledger entries
├── Ledger.java            # Handles reading/parsing transaction data
├── Report.java            # Implements all report screens and filters
├── Console.java           # Input/output utilities for prompts and formatting
├── ColorCodes.java        # ANSI color codes for console styling
└── transactions.csv       # Transaction history (created/updated by app)
