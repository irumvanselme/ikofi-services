package com.anselme.ikofi.utils.dto.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SendMoneyDTO {

    @NotBlank
    private String accountNumber;

    @NotNull
    @Min(1)
    private double amount;

    @NotNull
    @Pattern(regexp = "^[0-9]{5}$")
    private String  pin;

    public SendMoneyDTO() {
    }

    public SendMoneyDTO(String accountNumber, double amount, String pin) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.pin = pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
