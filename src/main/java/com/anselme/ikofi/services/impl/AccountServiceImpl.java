package com.anselme.ikofi.services.impl;

import com.anselme.ikofi.models.Transaction;
import com.anselme.ikofi.repositories.ITransactionRepository;
import com.anselme.ikofi.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    private final ITransactionRepository transactionRepository;

    @Autowired
    public AccountServiceImpl(ITransactionRepository repository){
        this.transactionRepository = repository;
    }

    public List<Transaction> transactions(Long id){
        return transactionRepository.getAllTransactionsByAccount(id);
    }
}
