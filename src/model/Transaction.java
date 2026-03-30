package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Transaction {
    public String id;
    public double amount;
    public String type;
    public String method;
    public String time;

    public Transaction(double amount, String type, String method) {
        this.amount = amount;
        this.type = type;
        this.method = method;
        this.id = "TXN" + (100000 + new Random().nextInt(900000));
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    @Override
    public String toString() {
        return id + " | " + type + " | " + amount + " | " + method + " | " + time;
    }
}