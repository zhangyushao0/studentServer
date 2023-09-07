package com.studentDemo.bank;

import java.time.LocalDateTime;

import com.studentDemo.user.User;
import com.studentDemo.user.UserDAOImpl;

public class Bank {
    public boolean transferMoney(User fromUser, User toUser, Double amount) {
        if (fromUser.getBankAccount().getBalance() >= amount) {
            fromUser.getBankAccount().setBalance(fromUser.getBankAccount().getBalance() - amount);
            toUser.getBankAccount().setBalance(toUser.getBankAccount().getBalance() + amount);
            UserDAOImpl userDAOImpl = new UserDAOImpl();
            Transaction transaction = new Transaction();
            transaction.setAmount(-amount);
            transaction.setTransactionType("TransferFrom");
            transaction.setCounterUserId(toUser.getId());
            transaction.setBankAccount(fromUser.getBankAccount());
            fromUser.getBankAccount().getTransactions().add(transaction);
            userDAOImpl.saveUser(fromUser);
            Long fromTransactionId = transaction.getTransactionId();
            Transaction transactionTo = new Transaction();
            transactionTo.setAmount(amount);
            transactionTo.setCounterTransactionId(fromTransactionId);
            transactionTo.setTransactionType("TransferTo");
            transactionTo.setCounterUserId(fromUser.getId());
            transactionTo.setBankAccount(toUser.getBankAccount());
            toUser.getBankAccount().getTransactions().add(transactionTo);
            userDAOImpl.saveUser(toUser);
            Long toTransactionId = transactionTo.getTransactionId();
            transaction.setCounterTransactionId(toTransactionId);
            userDAOImpl.saveUser(fromUser);
            return true;
        }
        return false;
    }

    // 撤销转账
    public boolean cancelTransferMoney(User fromUser, User toUser, Transaction transactionFrom,
            Transaction transactionTo) {
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        if (transactionFrom.getTransactionType().equals("TransferFrom")
                && transactionTo.getTransactionType().equals("TransferTo")) {
            fromUser.getBankAccount().setBalance(fromUser.getBankAccount().getBalance() - transactionFrom.getAmount());
            toUser.getBankAccount().setBalance(toUser.getBankAccount().getBalance() - transactionTo.getAmount());
            transactionFrom.setTransactionType("CancelTransferFromAt" + LocalDateTime.now());
            transactionTo.setTransactionType("CancelTransferToAt" + LocalDateTime.now());
            userDAOImpl.saveUser(fromUser);
            userDAOImpl.saveUser(toUser);
            return true;
        }
        return false;
    }

}
