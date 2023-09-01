package com.studentDemo.bank;

import com.studentDemo.user.User;
import com.studentDemo.user.UserDAOImpl;

public class Bank {
    public boolean transferMoney(User fromUser, User toUser, Double amount) {
        if (fromUser.getBankAccount().getBalance() >= amount) {
            fromUser.getBankAccount().setBalance(fromUser.getBankAccount().getBalance() - amount);
            toUser.getBankAccount().setBalance(toUser.getBankAccount().getBalance() + amount);
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setCounterUserId(toUser.getId());
            transaction.setTransactionType("TransferFrom");
            transaction.setBankAccount(fromUser.getBankAccount());
            fromUser.getBankAccount().getTransactions().add(transaction);
            transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setCounterUserId(fromUser.getId());
            transaction.setTransactionType("TransferTo");
            transaction.setBankAccount(toUser.getBankAccount());
            toUser.getBankAccount().getTransactions().add(transaction);
            UserDAOImpl userDAOImpl = new UserDAOImpl();
            userDAOImpl.saveUser(fromUser);
            userDAOImpl.saveUser(toUser);
            return true;
        }
        return false;
    }

    // 撤销转账
    public boolean cancelTransferMoney(User fromUser, Transaction transaction) {
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        User toUser = userDAOImpl.getUserById(transaction.getCounterUserId());
        boolean result = transferMoney(toUser, fromUser, transaction.getAmount());
        if (result) {
            transaction.setTransactionType("CancelTransfer");
            transaction.setCounterUserId(fromUser.getId());
            transaction.setBankAccount(fromUser.getBankAccount());
            fromUser.getBankAccount().getTransactions().add(transaction);
            userDAOImpl.saveUser(fromUser);
            userDAOImpl.saveUser(toUser);
            return true;
        }
        return false;
    }

}
