package com.anselme.ikofi.services;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Transaction;
import org.springframework.stereotype.Service;
import com.anselme.ikofi.repositories.IAccountRepository;
import com.anselme.ikofi.repositories.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@Service
public interface ITransactionService {

    public Transaction send(Account sender, Account receiver, double amount);
}
