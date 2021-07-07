package com.anselme.ikofi.models;

import com.anselme.ikofi.models.enums.EAccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @Enumerated(EnumType.STRING)
    private EAccountStatus status;

    @CreationTimestamp
    private Date createdAt;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transaction> sent;

    @JsonIgnore
    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Transaction> received;

    public Account() {
    }

    public Account(double amount, EAccountStatus status) {
        this.amount = amount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public EAccountStatus getStatus() {
        return status;
    }

    public void setStatus(EAccountStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getSent() {
        return sent;
    }

    public void setSent(List<Transaction> sent) {
        this.sent = sent;
    }

    public List<Transaction> getReceived() {
        return received;
    }

    public void setReceived(List<Transaction> received) {
        this.received = received;
    }
}
