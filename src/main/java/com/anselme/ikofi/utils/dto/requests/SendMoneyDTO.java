package com.anselme.ikofi.utils.dto.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SendMoneyDTO {

    @NotNull
    @Min(1)
    private long receiver_id;

    @NotNull
    @Min(1)
    private double amount;

    public SendMoneyDTO() {
    }

    public SendMoneyDTO(long receiver_id, double amount) {
        this.receiver_id = receiver_id;
        this.amount = amount;
    }

    public long getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(long receiver_id) {
        this.receiver_id = receiver_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
