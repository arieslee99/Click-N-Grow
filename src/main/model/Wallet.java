package model;

public class Wallet {

    private int balance;

    //EFFECTS: constructs a wallet with a starting balance of 100
    public Wallet() {
        this.balance = 100;
    }

    //REQUIRES: balance needs to be greater than price
    //MODIFIES: this
    //EFFECTS: decreases balance by price; balance stays the same if decrease will result in the balance
    //         being in the negatives
    public void decreaseBalance(int price) {
        if (balance >= price) {
            balance -= price;
        }
    }

    //MODIFIES: this
    //EFFECTS: increases balance by price
    public void increaseBalance(int price) {
        balance += price;
    }

    //MODIFIES: this
    //EFFECTS: sets balance to amount
    public void setBalance(int amount) {
        balance = amount;
    }

    //getters
    //EFFECTS: return current balance; if it is under 100 then set it back to 100
    public Integer getBalance() {
        if (balance < 100) {
            setBalance(100);
        }
        return balance;
    }
}
