package com.anselme.ikofi.services;

import com.anselme.ikofi.models.Transaction;
import org.springframework.stereotype.Service;
import com.anselme.ikofi.repositories.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public interface IAccountService {

    public List<Transaction> transactions(Long id);
}
