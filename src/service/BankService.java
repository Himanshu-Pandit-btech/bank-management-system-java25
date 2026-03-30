package service;

import model.Account;
import model.Transaction;
import java.io.*;
import java.util.*;

public class BankService {
    private ArrayList<Account> list = new ArrayList<>();

    private Account find(int id) {
        for (Account a : list) {
            if (a.accNo == id) return a;
        }
        return null;
    }

    public void create(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.next();
        System.out.print("PIN: ");
        int pin = sc.nextInt();
        System.out.print("Type (Savings/Current): ");
        String type = sc.next();
        System.out.print("Initial deposit: ");
        double bal = sc.nextDouble();

        int id = 10000000 + new Random().nextInt(90000000);
        list.add(new Account(id, name, pin, bal, type));

        System.out.println("Account created! Account No: " + id);
    }

    public void deposit(Scanner sc) {
        System.out.print("Account No: ");
        int id = sc.nextInt();
        Account a = find(id);
        if (a == null) { System.out.println("Account not found"); return; }

        System.out.print("PIN: ");
        if (a.pin != sc.nextInt()) { System.out.println("Wrong PIN"); return; }

        System.out.print("Amount: ");
        double amt = sc.nextDouble();
        System.out.print("Method (CASH/UPI/CARD): ");
        String method = sc.next();

        a.deposit(amt, method);
        System.out.println("Deposited. New balance: " + a.balance);
    }

    public void withdraw(Scanner sc) {
        System.out.print("Account No: ");
        int id = sc.nextInt();
        Account a = find(id);
        if (a == null) { System.out.println("Account not found"); return; }

        System.out.print("PIN: ");
        if (a.pin != sc.nextInt()) { System.out.println("Wrong PIN"); return; }

        System.out.print("Amount: ");
        double amt = sc.nextDouble();
        System.out.print("Method (CASH/UPI/CARD): ");
        String method = sc.next();

        a.withdraw(amt, method);
    }

    public void balance(Scanner sc) {
        System.out.print("Account No: ");
        int id = sc.nextInt();
        Account a = find(id);
        if (a == null) { System.out.println("Account not found"); return; }

        System.out.print("PIN: ");
        if (a.pin != sc.nextInt()) { System.out.println("Wrong PIN"); return; }

        System.out.println("Balance: " + a.balance);
    }

    public void interest(Scanner sc) {
        System.out.print("Account No: ");
        int id = sc.nextInt();
        Account a = find(id);
        if (a == null) { System.out.println("Account not found"); return; }

        System.out.print("PIN: ");
        if (a.pin != sc.nextInt()) { System.out.println("Wrong PIN"); return; }

        a.interest();
    }

    public void statement(Scanner sc) {
        System.out.print("Account No: ");
        int id = sc.nextInt();
        Account a = find(id);
        if (a == null) { System.out.println("Account not found"); return; }

        System.out.print("PIN: ");
        if (a.pin != sc.nextInt()) { System.out.println("Wrong PIN"); return; }

        System.out.println("--- Statement for " + a.name + " (" + a.type + ") ---");
        for (Transaction t : a.history) {
            System.out.println(t);
        }
    }

    public void save() {
        try {
            FileWriter fw = new FileWriter("data.csv");
            for (Account a : list) {
                // Save account line
                fw.write("ACC," + a.accNo + "," + a.name + "," + a.pin + "," + a.balance + "," + a.type + "\n");
                // Save each transaction
                for (Transaction t : a.history) {
                    fw.write("TXN," + a.accNo + "," + t.id + "," + t.amount + "," + t.type + "," + t.method + "," + t.time + "\n");
                }
            }
            fw.close();
            System.out.println("Data saved.");
        } catch (Exception e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.csv"));
            String line;
            Map<Integer, Account> map = new LinkedHashMap<>();

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p[0].equals("ACC")) {
                    Account a = new Account(
                            Integer.parseInt(p[1]),
                            p[2],
                            Integer.parseInt(p[3]),
                            Double.parseDouble(p[4]),
                            p[5],
                            true  // fromFile — skips adding INIT transaction
                    );
                    map.put(a.accNo, a);
                } else if (p[0].equals("TXN")) {
                    int accNo = Integer.parseInt(p[1]);
                    Account a = map.get(accNo);
                    if (a != null) {
                        Transaction t = new Transaction(Double.parseDouble(p[3]), p[4], p[5]);
                        t.id = p[2];
                        t.time = p[6];
                        a.history.add(t);
                    }
                }
            }

            br.close();
            list.addAll(map.values());
            System.out.println("Loaded " + list.size() + " account(s).");
        } catch (Exception e) {
            System.out.println("No data found, starting fresh.");
        }
    }
}