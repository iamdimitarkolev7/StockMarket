package com.kolev.stock.app.myapp.repository;

import com.kolev.stock.app.myapp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findById(Long transactionId);

    <S extends Transaction> S save(S transaction);
}
