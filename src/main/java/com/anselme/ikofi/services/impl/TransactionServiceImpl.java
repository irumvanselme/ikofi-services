package com.anselme.ikofi.services.impl;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Transaction;
import com.anselme.ikofi.repositories.IAccountRepository;
import com.anselme.ikofi.repositories.ITransactionRepository;
import com.anselme.ikofi.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(IAccountRepository repository, ITransactionRepository transactionRepository) {
        this.accountRepository = repository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction send(Account sender, Account receiver, double amount) {
        accountRepository.removeMoney(sender.getId(), amount);
        accountRepository.addMoney(receiver.getId(), amount);

        return transactionRepository.save(new Transaction(sender, receiver, amount));
    }
}