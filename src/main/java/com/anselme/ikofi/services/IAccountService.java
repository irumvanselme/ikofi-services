package com.anselme.ikofi.services;

import java.util.List;
import java.util.Optional;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Transaction;


public interface IAccountService {

    public List<Transaction> transactions(Long id);

    public Optional<Account> findById(long id);
}
