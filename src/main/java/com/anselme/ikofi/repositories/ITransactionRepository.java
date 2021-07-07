package com.anselme.ikofi.repositories;

import com.anselme.ikofi.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
}
