package com.studentDemo.campusStore;

import com.studentDemo.bank.Bank;
import com.studentDemo.bank.Transaction;
import com.studentDemo.user.User;
import com.studentDemo.user.UserDAOImpl;
import java.util.List;

public class CampusStore {
    final private Long storeUserId = -2992L;

    public boolean sellProduct(Product product, User user) {
        Bank bank = new Bank();
        User storeUser = new UserDAOImpl().getUserById(storeUserId);
        if (bank.transferMoney(user, storeUser, product.getPrice())) {
            List<Transaction> transactions = user.getBankAccount().getTransactions();
            transactions.get(transactions.size() - 1).setDescription("buy id" + product.getId());
            return true;
        }
        return false;
    }

    public boolean returnProduct(Transaction transaction, User user) {
        User storeUser = new UserDAOImpl().getUserById(storeUserId);
        return new Bank().cancelTransferMoney(storeUser, transaction);
    }

    public boolean commentProduct(Product product, User user, String content) {
        Comment comment1 = new Comment();
        comment1.setContent(content);
        comment1.setProduct(product);
        comment1.setUserId(user.getId());
        product.getComments().add(comment1);
        return true;
    }
}
