package com.studentDemo.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.studentDemo.bank.BankAccount;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        User oldUser = new User();
        oldUser.setUsername("testuser");
        oldUser.setPassword("testpassword");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000.0);
        oldUser.setBankAccount(bankAccount);
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        userDAOImpl.saveUser(oldUser);
        user = new User();
        user = userDAOImpl.getUser(oldUser.getId());
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
        assertEquals(1000.0, user.getBankAccount().getBalance());
    }
}