package com.studentDemo.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.studentDemo.bank.Bank;
import com.studentDemo.bank.BankAccount;
import com.studentDemo.bank.Transaction;

public class UserTest {
    private User user;
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        User user1 = new User();
        user1.setId(5L);
        user1.setUsername("testuser1");
        user1.setPassword("testpassword1");
        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(100.0);
        user1.setBankAccount(bankAccount1);
        User user2 = new User();
        user2.setId(6L);
        user2.setUsername("testuser2");
        user2.setPassword("testpassword2");
        BankAccount bankAccount2 = new BankAccount();
        bankAccount2.setBalance(100.0);
        user2.setBankAccount(bankAccount2);
        Bank bank = new Bank();
        bank.transferMoney(user1, user2, 50.0);
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        userDAOImpl.saveUser(user1);
        userDAOImpl.saveUser(user2);
        user = userDAOImpl.getUserById(5L);
        transaction = user.getBankAccount().getTransactions().get(0);
    }

    @Test
    public void testTransaction() {
        assertEquals(50.0, transaction.getAmount());
        assertEquals(6, transaction.getCounterUserId());
        assertEquals("TransferFrom", transaction.getTransactionType());
    }

    @Test
    public void testGetId() {
        Long id = 1L;
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void testGetUsername() {
        assertEquals("testuser", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("testpassword", user.getPassword());
    }

    @Test
    public void testGetBankAccount() {
        assertNotNull(user.getBankAccount());
        assertEquals(50.0, user.getBankAccount().getBalance());
        assertEquals(11, transaction.getCounterUserId());
    }

    @Test
    public void testSaveUser() {
        User user1 = new User();
        user1.setUsername("test");
        user1.setPassword("test");
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        userDAOImpl.saveUser(user1);
        User user2 = userDAOImpl.getUserById(user1.getId());
        assertEquals(user1.getUsername(), user2.getUsername());
    }
}
