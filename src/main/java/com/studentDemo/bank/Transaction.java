package com.studentDemo.bank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import java.time.LocalDateTime;
import com.studentDemo.user.User;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "counter_user_id")
    private Long counterUserId;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "time")
    private String time;
    @Column(name = "description")
    private String description;
    @ManyToOne // 添加多对一的关系
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    public Transaction() {
        time = LocalDateTime.now().toString();
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getCounterUserId() {
        return counterUserId;
    }

    public void setCounterUserId(Long counterUserId) {
        this.counterUserId = counterUserId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
