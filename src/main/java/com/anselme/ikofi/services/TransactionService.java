package com.anselme.ikofi.services;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    public List<Transaction> sent(Account account){
        return account.getSent();
    }

    public List<Transaction> received(Account account){
        return account.getReceived();
    }
}
