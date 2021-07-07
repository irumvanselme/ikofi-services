package com.anselme.ikofi.controllers;

import com.anselme.ikofi.models.User;
import com.anselme.ikofi.services.AccountService;
import com.anselme.ikofi.services.UserService;
import com.anselme.ikofi.utils.dto.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/transactions")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public AccountController(AccountService service, UserService userService) {
        this.accountService = service;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTransactions() {
        User user = userService.getLoggedInUser();
        return ResponseEntity.ok(new ApiResponse(accountService.transactions(user.getAccount().getId())));
    }

    @GetMapping("/sent")
    public ResponseEntity<ApiResponse> getAllTransactionsThatWhereSentByMe() {
        User user = userService.getLoggedInUser();
        return ResponseEntity.ok(new ApiResponse(user.getAccount().getSent()));
    }

    @GetMapping("/received")
    public ResponseEntity<ApiResponse> getAllTransactionsThatWhereReceivedByMe() {
        User user = userService.getLoggedInUser();
        return ResponseEntity.ok(new ApiResponse(user.getAccount().getReceived()));
    }
}
