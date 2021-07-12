package com.anselme.ikofi.services;

import java.util.List;
import com.anselme.ikofi.models.Transaction;

public interface IAccountService {

    public List<Transaction> transactions(Long id);
}
