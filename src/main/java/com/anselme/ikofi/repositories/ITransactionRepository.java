package com.anselme.ikofi.repositories;

import com.anselme.ikofi.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM transactions WHERE sender_id = :account_id OR receiver_id = :account_id")
    List<Transaction> getAllTransactionsByAccount(@Param("account_id") Long id);
}
