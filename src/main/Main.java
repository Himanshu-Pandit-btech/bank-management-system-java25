package main;

import service.BankService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankService service = new BankService();
        Scanner sc = new Scanner(System.in);
        service.load();
        int choice;
        do {
            System.out.println("\n1 Create 2 Deposit 3 Withdraw 4 Balance 5 Interest 6 Statement 7 Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> service.create(sc);
                case 2 -> service.deposit(sc);
                case 3 -> service.withdraw(sc);
                case 4 -> service.balance(sc);
                case 5 -> service.interest(sc);
                case 6 -> service.statement(sc);
                case 7 -> service.save();
            }
        } while (choice != 7);
    }
}