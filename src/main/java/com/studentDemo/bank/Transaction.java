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

import lombok.Data;

@Data
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
    private LocalDateTime time;
    @Column(name = "description")
    private String description;
    @ManyToOne // 添加多对一的关系
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    public Transaction() {
        this.time = LocalDateTime.now();
    }
}
