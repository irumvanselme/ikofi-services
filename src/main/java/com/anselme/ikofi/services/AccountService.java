package com.anselme.ikofi.services;

import com.anselme.ikofi.models.Transaction;
import com.anselme.ikofi.repositories.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final ITransactionRepository transactionRepository;

    @Autowired
    public AccountService(ITransactionRepository repository){
        this.transactionRepository = repository;
    }

    public List<Transaction> transactions(Long id){
        return transactionRepository.getAllTransactionsByAccount(id);
    }
}
