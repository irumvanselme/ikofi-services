package com.anselme.ikofi.utils.dto.requests.settings;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ChangePINDTO {

    @NotBlank
    private String currentPIN;

    @NotBlank
    @Pattern(regexp = "^[0-9]{5}$")
    private String newPIN;

    public ChangePINDTO() {
    }

    public ChangePINDTO(String currentPIN, String newPIN) {
        this.currentPIN = currentPIN;
        this.newPIN = newPIN;
    }

    public String getCurrentPIN() {
        return currentPIN;
    }

    public void setCurrentPIN(String currentPIN) {
        this.currentPIN = currentPIN;
    }

    public String getNewPIN() {
        return newPIN;
    }

    public void setNewPIN(String newPIN) {
        this.newPIN = newPIN;
    }
}
