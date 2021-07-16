package com.anselme.ikofi.controllers;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Transaction;
import com.anselme.ikofi.models.User;
import com.anselme.ikofi.services.IAccountService;
import com.anselme.ikofi.services.ITransactionService;
import com.anselme.ikofi.services.IUserService;
import com.anselme.ikofi.utils.dto.requests.SendMoneyDTO;
import com.anselme.ikofi.utils.dto.responses.ApiResponse;
import com.anselme.ikofi.utils.functions.PasswordManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AccountController {
    private final IAccountService accountService;
    private final ITransactionService transactionService;
    private final IUserService userService;

    @Autowired
    public AccountController(IAccountService iAccountService, ITransactionService transactionService, IUserService userService) {
        this.accountService  = iAccountService;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @PostMapping("/api/money/send")
    public ResponseEntity<ApiResponse> sendMoney(@Valid @RequestBody SendMoneyDTO dto) {
        Optional<Account> _account = accountService.findByAccountNumber(dto.getAccountNumber());

        if (_account.isEmpty())
            return ResponseEntity.badRequest().body(ApiResponse.fail("Account to receive does not exit"));

        if(!PasswordManager.compare(dto.getPin(), _account.get().getPin()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.fail("Pin does not Mathch "));

        User user = userService.getLoggedInUser();

        if (user.getAccount().getAmount() < dto.getAmount())
            return ResponseEntity.badRequest().body(ApiResponse.fail("Money to send is less than your balance"));

        Transaction transaction = transactionService.send(user.getAccount(), _account.get(), dto.getAmount());

        return ResponseEntity.ok(ApiResponse.success(transaction));
    }

    @GetMapping("/api/account/balance")
    public ResponseEntity<ApiResponse> getBalance(){
        User user = userService.getLoggedInUser();

        return ResponseEntity.ok(ApiResponse.success(user.getAccount().getAmount()));
    }
}