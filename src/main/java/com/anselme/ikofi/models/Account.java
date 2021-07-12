package com.anselme.ikofi.models;

import com.anselme.ikofi.models.enums.EAccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "accounts")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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

    @JsonIgnore
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
}
