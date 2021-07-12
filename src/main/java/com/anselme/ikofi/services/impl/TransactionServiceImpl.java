package com.anselme.ikofi.services.impl;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Activity;
import com.anselme.ikofi.models.Transaction;
import com.anselme.ikofi.models.enums.EUserActions;
import com.anselme.ikofi.repositories.IAccountRepository;
import com.anselme.ikofi.repositories.ITransactionRepository;
import com.anselme.ikofi.services.IActivityService;
import com.anselme.ikofi.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;
    private final IActivityService activityService;

    @Autowired
    public TransactionServiceImpl(IAccountRepository repository, ITransactionRepository transactionRepository, IActivityService iActivityService) {
        this.accountRepository = repository;
        this.transactionRepository = transactionRepository;
        this.activityService = iActivityService;
    }

    @Transactional
    public Transaction send(Account sender, Account receiver, double amount) {
        accountRepository.removeMoney(sender.getId(), amount);
        accountRepository.addMoney(receiver.getId(), amount);

        Transaction transaction = transactionRepository.save(new Transaction(sender, receiver, amount));

        activityService.record(new Activity(sender.getUser(), EUserActions.SEND_MONEY, transaction));
        activityService.record(new Activity(receiver.getUser(), EUserActions.RECEIVE_MONEY, transaction));

        return transaction;
    }
}
