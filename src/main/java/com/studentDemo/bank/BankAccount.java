package com.studentDemo.bank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "password")
    private String password;
    @Column(name = "account_type")
    private String accountType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bankAccount")
    private List<Transaction> transactions = new ArrayList<>();

}