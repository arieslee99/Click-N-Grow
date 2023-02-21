package model.tests;

import model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWallet {

    private Wallet wallet;

    @BeforeEach
    public void setup() {
        wallet = new Wallet();
        wallet.setBalance(300);
    }

    @Test
    public void testIncreaseBalanceOnce() {
        wallet.increaseBalance(100);
        assertEquals(400, wallet.getBalance());
    }

    @Test
    public void testIncreaseBalanceMultipleTimes() {
        wallet.increaseBalance(100);
        assertEquals(400, wallet.getBalance());
        wallet.increaseBalance(300);
        assertEquals(700, wallet.getBalance());

    }

    @Test
    public void testDecreaseBalanceOnce() {
        wallet.decreaseBalance(200);
        assertEquals(100, wallet.getBalance());
    }

    @Test
    public void testDecreaseBalanceMultipleTimes() {
        wallet.decreaseBalance(100);
        assertEquals(200, wallet.getBalance());
        wallet.decreaseBalance(200);
        assertEquals(0, wallet.getBalance());

    }

    @Test
    public void testDecreaseBalanceButFailed() {
        wallet.decreaseBalance(500);
        assertEquals(300, wallet.getBalance());
    }
}
