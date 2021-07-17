package com.anselme.ikofi.controllers;

import com.anselme.ikofi.models.User;
import com.anselme.ikofi.services.IUserService;
import com.anselme.ikofi.utils.dto.requests.settings.ChangePINDTO;
import com.anselme.ikofi.utils.dto.requests.settings.ChangePasswordDTO;
import com.anselme.ikofi.utils.dto.requests.settings.UpdateUserDetailsDTO;
import com.anselme.ikofi.utils.dto.responses.ApiResponse;
import com.anselme.ikofi.utils.functions.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/settings")
public class SettingController {

    private final IUserService userService;

    @Autowired
    public SettingController(IUserService iUserService){
        this.userService = iUserService;
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse> changeUserDetails(@Valid @RequestBody UpdateUserDetailsDTO dto) {
        User user = userService.getLoggedInUser();

        user.setFullNames(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setMobile(dto.getMobile());

        user.getProfile().setAddress(dto.getAddress());
        user.getProfile().setIdCard(dto.getIdCard());

        userService.create(user);

        return ResponseEntity.ok(ApiResponse.success("Successfully updated the account", user));
    }

    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse> changePassword(@Valid @RequestBody ChangePasswordDTO dto) {
        User user = userService.getLoggedInUser();

        if(!PasswordManager.compare(dto.getCurrentPassword(), user.getPassword()))
            return ResponseEntity.badRequest().body(ApiResponse.fail("Invalid current password for the user", null));

        user.setPassword(PasswordManager.encode(dto.getNewPassword()));

        userService.create(user);

        return ResponseEntity.ok(ApiResponse.success("Successfully changed password", user));
    }

    @PutMapping("/change-pin")
    public ResponseEntity<ApiResponse> changePin(@Valid @RequestBody ChangePINDTO dto) {
        User user = userService.getLoggedInUser();

        if(!PasswordManager.compare(dto.getCurrentPIN(), user.getAccount().getPin()))
            return ResponseEntity.badRequest().body(ApiResponse.fail("Invalid current PIN for the current user", null));

        user.getAccount().setPin(PasswordManager.encode(dto.getNewPIN()));

        userService.create(user);

        return ResponseEntity.ok(ApiResponse.success("Successfully changed PIN"));
    }
}
