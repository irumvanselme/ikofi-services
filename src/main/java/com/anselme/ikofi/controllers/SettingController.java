package com.anselme.ikofi.controllers;

import com.anselme.ikofi.utils.dto.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/settings")
public class SettingController {

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse> changeUserDetails() {

        return ResponseEntity.ok(ApiResponse.success("Successfully updated the account"));
    }

    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse> changePassword() {

        return ResponseEntity.ok(ApiResponse.success("Successfully changed password"));
    }

    @PutMapping("/change-pin")
    public ResponseEntity<ApiResponse> changePin() {

        return ResponseEntity.ok(ApiResponse.success("Successfully changed PIN"));
    }
}
