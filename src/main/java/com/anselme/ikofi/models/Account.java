package com.anselme.ikofi.models;

import org.hibernate.annotations.Proxy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import com.anselme.ikofi.models.enums.EAccountStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "accounts")
@Proxy(lazy = false)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pin;

    private String accountNumber;

    private double amount;

    @Enumerated(EnumType.STRING)
    private EAccountStatus status;

    @CreationTimestamp
    private Date createdAt;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private User user;

    public Account() {
    }

    public Account(double amount, String pin, EAccountStatus status) {
        this.amount = amount;
        this.pin = pin;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public double getAmount() {
        return amount;
    }

    @JsonIgnore
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String  getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String  accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public EAccountStatus getStatus() {
        return status;
    }

    @JsonIgnore
    public void setStatus(EAccountStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
