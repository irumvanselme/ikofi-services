package com.anselme.ikofi.utils.dto.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SendMoneyDTO {

    @NotNull
    @Min(1)
    private long receiverId;

    @NotNull
    @Min(1)
    private double amount;

    @NotNull
    @Pattern(regexp = "^[0-9]{5}$")
    private String  pin;

    public SendMoneyDTO() {
    }

    public SendMoneyDTO(long receiver_id, double amount, String pin) {
        this.receiverId = receiver_id;
        this.amount = amount;
        this.pin = pin;
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
