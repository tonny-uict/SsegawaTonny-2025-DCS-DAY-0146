// Day 2026-09-15 - Module 4: ATM with Encapsulation & Validation
class ATM {
    private String pin = "0146";
    private double balance = 500000;
    public boolean authenticate(String p) { return pin.equals(p); }
    public void checkBalance(String p) {
        if (authenticate(p)) System.out.println("Balance: UGX " + balance);
        else System.out.println("Wrong PIN");
    }
    public void withdraw(String p, double amount) {
        if (!authenticate(p)) { System.out.println("Wrong PIN"); return; }
        if (amount > balance) System.out.println("Insufficient");
        else { balance-=amount; System.out.println("Withdrawn " + amount + " New: " + balance); }
    }
}
public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.checkBalance("0146");
        atm.withdraw("0146", 100000);
        atm.withdraw("0000", 50000);
    }
}
