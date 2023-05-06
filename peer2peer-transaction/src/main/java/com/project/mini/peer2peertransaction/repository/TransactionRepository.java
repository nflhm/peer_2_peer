package com.project.mini.peer2peertransaction.repository;

import com.project.mini.peer2peertransaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findTransactionByTranId(Integer integer);
    List<Transaction> findAllByOrderByBorrowerId();
    List<Transaction> findAllByOrderByProductId();
}
