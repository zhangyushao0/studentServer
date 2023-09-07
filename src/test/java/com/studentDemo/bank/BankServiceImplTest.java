package com.studentDemo.bank;

import com.studentDemo.campusStore.CampusStoreServiceImpl;
import com.studentDemo.user.User;
import com.studentDemo.user.UserDAOImpl;

import io.grpc.Server;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.resource.transaction.spi.TransactionCoordinator.TransactionDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServerBuilder;

public class BankServiceImplTest {
    Server server;
    ManagedChannel channel;
    BankServiceGrpc.BankServiceBlockingStub blockingStub;

    @BeforeEach
    public void setUp() throws Exception {
        server = ServerBuilder.forPort(8080).addService(new BankServiceImpl()).build();
        server.start();
        channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        blockingStub = BankServiceGrpc.newBlockingStub(channel);
    }

    @AfterEach
    public void tearDown() throws Exception {
        server.shutdown();
        channel.shutdown();
    }

    @Test
    public void testTransfer() {
        UserDAOImpl userDAO = new UserDAOImpl();
        User testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword("test");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000D);
        bankAccount.setPassword("test");
        testUser.setBankAccount(bankAccount);
        userDAO.saveUser(testUser);
        User testUser2 = new User();
        testUser2.setUsername("test2");
        testUser2.setPassword("test2");
        BankAccount bankAccount2 = new BankAccount();
        bankAccount2.setPassword("test2");
        bankAccount2.setBalance(1000D);
        testUser2.setBankAccount(bankAccount2);
        userDAO.saveUser(testUser2);
        TransferRequest request = TransferRequest.newBuilder().setFromUserId(testUser.getId())
                .setToUserId(testUser2.getId()).setAmount(100D).setPassword("test").build();
        TransferResponse response = blockingStub.transfer(request);
        assert (response.getSuccess());
        assert (userDAO.getUserById(testUser.getId()).getBankAccount().getBalance() == 900D);
        assert (userDAO.getUserById(testUser2.getId()).getBankAccount().getBalance() == 1100D);
        User testUser3 = userDAO.getUserById(testUser.getId());
        assert (testUser3.getBankAccount().getTransactions().size() == 1);
        assert (testUser3.getBankAccount().getTransactions().get(0).getAmount() == -100D);
        assert (testUser3.getBankAccount().getTransactions().get(0).getCounterUserId().equals(testUser2.getId()));
    }

    @Test
    public void testCancelTransfer() {
        UserDAOImpl userDAO = new UserDAOImpl();
        User testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword("test");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000D);
        bankAccount.setPassword("test");
        testUser.setBankAccount(bankAccount);
        userDAO.saveUser(testUser);
        User testUser2 = new User();
        testUser2.setUsername("test2");
        testUser2.setPassword("test2");
        BankAccount bankAccount2 = new BankAccount();
        bankAccount2.setPassword("test2");
        bankAccount2.setBalance(1000D);
        testUser2.setBankAccount(bankAccount2);
        userDAO.saveUser(testUser2);
        TransferRequest request = TransferRequest.newBuilder().setFromUserId(testUser.getId())
                .setToUserId(testUser2.getId()).setAmount(100D).setPassword("test").build();
        TransferResponse response = blockingStub.transfer(request);
        assert (response.getSuccess());
        assert (userDAO.getUserById(testUser.getId()).getBankAccount().getBalance() == 900D);
        assert (userDAO.getUserById(testUser2.getId()).getBankAccount().getBalance() == 1100D);
        User testUser3 = userDAO.getUserById(testUser.getId());
        assert (testUser3.getBankAccount().getTransactions().size() == 1);
        assert (testUser3.getBankAccount().getTransactions().get(0).getAmount() == -100D);
        assert (testUser3.getBankAccount().getTransactions().get(0).getCounterUserId().equals(testUser2.getId()));
        CancelTransferRequest cancelRequest = CancelTransferRequest.newBuilder()
                .setTransactionId(testUser3.getBankAccount().getTransactions().get(0).getTransactionId())
                .setFromUserId(testUser.getId()).build();
        CancelTransferResponse cancelResponse = blockingStub.cancelTransfer(cancelRequest);
        assert (cancelResponse.getSuccess());
        assert (userDAO.getUserById(testUser.getId()).getBankAccount().getBalance() == 1000D);
        assert (userDAO.getUserById(testUser2.getId()).getBankAccount().getBalance() == 1000D);
    }

    @Test
    public void testGetTransactionsById() {
        UserDAOImpl userDAO = new UserDAOImpl();
        User testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword("test");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000D);
        bankAccount.setPassword("test");
        testUser.setBankAccount(bankAccount);
        userDAO.saveUser(testUser);
        User testUser2 = new User();
        testUser2.setUsername("test2");
        testUser2.setPassword("test2");
        BankAccount bankAccount2 = new BankAccount();
        bankAccount2.setPassword("test2");
        bankAccount2.setBalance(1000D);
        testUser2.setBankAccount(bankAccount2);
        userDAO.saveUser(testUser2);
        TransferRequest request = TransferRequest.newBuilder().setFromUserId(testUser.getId())
                .setToUserId(testUser2.getId()).setAmount(100D).setPassword("test").build();
        TransferResponse response = blockingStub.transfer(request);
        assert (response.getSuccess());
        assert (userDAO.getUserById(testUser.getId()).getBankAccount().getBalance() == 900D);
        assert (userDAO.getUserById(testUser2.getId()).getBankAccount().getBalance() == 1100D);
        User testUser3 = userDAO.getUserById(testUser.getId());
        assert (testUser3.getBankAccount().getTransactions().size() == 1);
        assert (testUser3.getBankAccount().getTransactions().get(0).getAmount() == -100D);
        assert (testUser3.getBankAccount().getTransactions().get(0).getCounterUserId().equals(testUser2.getId()));
        GetTransactionsByIdRequest getRequest = GetTransactionsByIdRequest.newBuilder().setUserId(testUser.getId())
                .build();
        GetTransactionsByIdResponse getResponse = blockingStub.getTransactionsById(getRequest);
        assert (getResponse.getTransactionsCount() == 1);
        assert (getResponse.getTransactions(0).getAmount() == -100D);
        assert (getResponse.getTransactions(0).getCounterUserId() == testUser2.getId());
    }

    @Test
    public void testGetAllTransactions() {
        BankAccountDAOImpl bankAccountDAO = new BankAccountDAOImpl();
        int nowNum = bankAccountDAO.getAllTransactions().size();
        UserDAOImpl userDAO = new UserDAOImpl();
        User testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword("test");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000D);
        bankAccount.setPassword("test");
        testUser.setBankAccount(bankAccount);
        userDAO.saveUser(testUser);
        User testUser2 = new User();
        testUser2.setUsername("test2");
        testUser2.setPassword("test2");
        BankAccount bankAccount2 = new BankAccount();
        bankAccount2.setPassword("test2");
        bankAccount2.setBalance(1000D);
        testUser2.setBankAccount(bankAccount2);
        userDAO.saveUser(testUser2);
        TransferRequest request = TransferRequest.newBuilder().setFromUserId(testUser.getId())
                .setToUserId(testUser2.getId()).setAmount(100D).setPassword("test").build();
        TransferResponse response = blockingStub.transfer(request);
        assert (response.getSuccess());
        assert (userDAO.getUserById(testUser.getId()).getBankAccount().getBalance() == 900D);
        assert (userDAO.getUserById(testUser2.getId()).getBankAccount().getBalance() == 1100D);
        User testUser3 = userDAO.getUserById(testUser.getId());
        assert (testUser3.getBankAccount().getTransactions().size() == 1);
        assert (testUser3.getBankAccount().getTransactions().get(0).getAmount() == -100D);
        assert (testUser3.getBankAccount().getTransactions().get(0).getCounterUserId().equals(testUser2.getId()));
        GetAllTransactionsRequest getRequest = GetAllTransactionsRequest.newBuilder().build();
        GetAllTransactionsResponse getResponse = blockingStub.getAllTransactions(getRequest);
        assert (getResponse.getTransactionsCount() == nowNum + 2);
        assert (getResponse.getTransactions(getResponse.getTransactionsCount() - 2).getAmount() == -100D);
        assert (getResponse.getTransactions(getResponse.getTransactionsCount() - 2).getCounterUserId() == testUser2
                .getId());
        assert (getResponse.getTransactions(getResponse.getTransactionsCount() - 1).getAmount() == 100D);
        assert (getResponse.getTransactions(getResponse.getTransactionsCount() - 1).getCounterUserId() == testUser
                .getId());
    }
}