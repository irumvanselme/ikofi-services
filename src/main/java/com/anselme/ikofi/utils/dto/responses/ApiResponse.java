package com.anselme.ikofi.utils.dto.responses;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.util.Date;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApiResponse {

    private String status = "Success";
    private String message = "Success";
    private Object data;
    private String timestamp = new Date().toString();
    private String developer = "irumvanselme";

    public ApiResponse() {
    }

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponse(Object data) {
        this.data = data;
    }

    public ApiResponse(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp;
        this.developer = developer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public static ApiResponse success(Object data){
        return new ApiResponse("Success", "Success", data);
    }

    public static ApiResponse success(String message, Object data){
        return new ApiResponse("Success", message, data);
    }

    public static ApiResponse fail(Object data){
        return new ApiResponse("Error", "An error occurred", data);
    }

    public static ApiResponse fail(String message, Object data){
        return new ApiResponse("Error", message, data);
    }
}
