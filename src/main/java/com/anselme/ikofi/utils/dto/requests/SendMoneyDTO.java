package com.anselme.ikofi.utils.dto.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SendMoneyDTO {

    @NotNull
    @Min(1)
    private long receiverId;

    @NotNull
    @Min(1)
    private double amount;

    public SendMoneyDTO() {
    }

    public SendMoneyDTO(long receiver_id, double amount) {
        this.receiverId = receiver_id;
        this.amount = amount;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
