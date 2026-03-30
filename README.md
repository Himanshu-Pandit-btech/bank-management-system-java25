# Java Banking System

A simple console-based banking application built in Java supporting account creation, deposits, withdrawals, balance checks, interest calculation, and transaction history.

##  Project Structure

```
src/
├── main/
│   └── Main.java           # Entry point
├── model/
│   ├── Account.java        # Account model
│   └── Transaction.java    # Transaction model
└── service/
    └── BankService.java    # Core banking logic
```

##  Features

- Create Savings or Current accounts
- Deposit & withdraw (with overdraft support for Current accounts)
- Check balance
- Apply 5% interest (Savings accounts only)
- View full transaction statement
- Persistent storage via CSV

##  Getting Started

### Prerequisites
- Java JDK 14 or higher (uses switch expressions)

### Compile
```bash
cd src
javac main/Main.java model/Account.java model/Transaction.java service/BankService.java
```

### Run
```bash
java main.Main
```

## 📋 Menu Options

| Option | Action |
|--------|--------|
| 1 | Create account |
| 2 | Deposit |
| 3 | Withdraw |
| 4 | Check balance |
| 5 | Apply interest |
| 6 | View statement |
| 7 | Save & Exit |

## ⚠️ Notes

- Always choose **option 7** to exit — this saves your data
- `data.csv` is auto-generated locally and is excluded from version control (contains PINs)
- Current accounts support overdraft up to ₹5,000

## 🔒 Security Notice

PINs are stored in plain text in `data.csv`. This is a learning project and **not suitable for production use**.
