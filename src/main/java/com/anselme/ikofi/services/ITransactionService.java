package com.anselme.ikofi.services;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Transaction;

public interface ITransactionService {

    public Transaction send(Account sender, Account receiver, double amount);
}
