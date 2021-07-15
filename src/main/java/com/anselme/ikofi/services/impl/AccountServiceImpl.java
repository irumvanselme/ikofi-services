package com.anselme.ikofi.services.impl;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Transaction;
import com.anselme.ikofi.repositories.IAccountRepository;
import com.anselme.ikofi.repositories.ITransactionRepository;
import com.anselme.ikofi.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements IAccountService {

    private final ITransactionRepository transactionRepository;
    private final IAccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(ITransactionRepository repository, IAccountRepository iAccountRepository){
        this.transactionRepository = repository;
        this.accountRepository = iAccountRepository;
    }

    public List<Transaction> transactions(Long id){
        return transactionRepository.getAllTransactionsByAccount(id);
    }

    public Optional<Account> findById(long id){
        return accountRepository.findById(id);
    }

    public String getNewAccountNumber(){
        var number = "0000";
        do {
            Random random = new Random();
            number = String.format("%04d", random.nextInt(10000));
        } while (accountRepository.findByAccountNumber(number).isPresent());

        return number;
    }
}
