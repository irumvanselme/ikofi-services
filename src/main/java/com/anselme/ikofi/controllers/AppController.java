package com.anselme.ikofi.controllers;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Transaction;
import com.anselme.ikofi.models.User;
import com.anselme.ikofi.repositories.IAccountRepository;
import com.anselme.ikofi.services.ITransactionService;
import com.anselme.ikofi.services.IUserService;
import com.anselme.ikofi.utils.dto.requests.SendMoneyDTO;
import com.anselme.ikofi.utils.dto.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AppController {
    private final IAccountRepository accountRepository;
    private final ITransactionService transactionService;
    private final IUserService userService;

    @Autowired
    public AppController(IAccountRepository iAccountRepository, ITransactionService transactionService, IUserService userService) {
        this.accountRepository = iAccountRepository;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @PostMapping("/api/money/send")
    public ResponseEntity<ApiResponse> sendMoney(@Valid @RequestBody SendMoneyDTO dto) {
        Optional<Account> _account = accountRepository.findById(dto.getReceiverId());

        if (_account.isEmpty())
            return ResponseEntity.badRequest().body(new ApiResponse("Failed", "Failed", "Account to receive does not exit"));

        User user = userService.getLoggedInUser();

        if (user.getAccount().getAmount() < dto.getAmount())
            return ResponseEntity.badRequest().body(new ApiResponse("Failed", "Failed", "Money to send is less than your balance"));

        Transaction transaction = transactionService.send(user.getAccount(), _account.get(), dto.getAmount());

        return ResponseEntity.ok(new ApiResponse(transaction));
    }
}
