package com.anselme.ikofi.controllers;

import com.anselme.ikofi.models.User;
import com.anselme.ikofi.services.IAccountService;
import com.anselme.ikofi.services.IUserService;
import com.anselme.ikofi.utils.dto.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/transactions")
public class AccountController {

    private final IAccountService accountService;
    private final IUserService userService;

    @Autowired
    public AccountController(IAccountService service, IUserService userService) {
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
