package com.anselme.ikofi.repositories;

import com.anselme.ikofi.models.Account;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Query("UPDATE Account SET amount = amount - :transactionAmount WHERE id = :id")
    void removeMoney(@Param("id") long id, @Param("transactionAmount") final double transactionAmount);

    @Modifying
    @Query("UPDATE Account SET amount = amount + :transactionAmount WHERE id = :id")
    void addMoney(@Param("id") long id, @Param("transactionAmount") final double transactionAmount);
}
