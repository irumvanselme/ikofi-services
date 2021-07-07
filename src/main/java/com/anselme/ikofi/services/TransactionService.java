package com.anselme.ikofi.services;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    public Transaction send(Account sender, Account receiver, double amount){

        return new Transaction(sender, receiver, amount);
    }
}
