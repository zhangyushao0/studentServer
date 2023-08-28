package com.studentDemo.bank;

import com.studentDemo.hibernateutil.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class BankAccountDAOImpl {
    public BankAccount getBankAccountById(Long id) {
        return HibernateUtil.getSessionFactory().openSession().get(BankAccount.class, id);
    }

    public void saveBankAccount(BankAccount bankAccount) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(bankAccount);
        session.getTransaction().commit();
        session.close();
    }

    public Transaction getTransactionById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Transaction transaction = session.get(Transaction.class, id);
        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Transaction> transactions = session.createQuery("FROM Transaction", Transaction.class).list();
        return transactions;
    }
}
