package com.studentDemo.campusStore;

import com.studentDemo.bank.Bank;
import com.studentDemo.bank.Transaction;
import com.studentDemo.user.User;

public class CampusStore {
    private User storeUser;

    public boolean sellProduct(Product product, User user) {
        Bank bank = new Bank();
        if (bank.transferMoney(user, storeUser, product.getPrice())) {
            return true;
        }
        return false;
    }

    public boolean returnProduct(Transaction transaction, User user) {
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
