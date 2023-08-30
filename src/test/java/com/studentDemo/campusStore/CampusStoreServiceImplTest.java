package com.studentDemo.campusStore;

import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.studentDemo.user.UserDAOImpl;
import com.studentDemo.bank.BankAccount;
import com.studentDemo.user.User;
import java.util.ArrayList;
import java.util.List;

public class CampusStoreServiceImplTest {
    private UserDAOImpl userDAO;
    private CampusStoreServiceImpl campusStoreService;
    User testUser;

    @BeforeEach
    public void setUp() {
        userDAO = new UserDAOImpl();
        campusStoreService = new CampusStoreServiceImpl();
        testUser = new User();
        // testUser.setId(1L);
        testUser.setUsername("test");
        testUser.setPassword("test");
    }

    @Test
    public void testBankAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000D);
        testUser.setBankAccount(bankAccount);
        userDAO.saveUser(testUser);
        User user = userDAO.getUserById(testUser.getId());
        assert (user.getBankAccount().getBalance() == 1000D);
    }
}