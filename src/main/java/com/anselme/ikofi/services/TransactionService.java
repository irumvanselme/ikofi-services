package com.anselme.ikofi.services;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Transaction;
import com.anselme.ikofi.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionService {

    private final IAccountRepository accountRepository;

    @Autowired
    public TransactionService(IAccountRepository repository){
        this.accountRepository = repository;
    }

    @Transactional
    public Transaction send(Account sender, Account receiver, double amount){

        accountRepository.removeMoney(sender.getId(), amount);

        accountRepository.addMoney(receiver.getId(), amount);

        return new Transaction(sender, receiver, amount);
    }
}
