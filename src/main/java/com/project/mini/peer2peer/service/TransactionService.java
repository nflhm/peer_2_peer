package com.project.mini.peer2peer.service;

import com.project.mini.peer2peer.model.Transaction;
import com.project.mini.peer2peer.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public void insert(Transaction transaction) { transactionRepository.save(transaction); }

    public void update(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public Transaction findTheTransaction(Integer integer) {
        return transactionRepository.findTransactionByTranId(integer);
    }

    public List<Transaction> allTransactions() {
        return transactionRepository.findAllByOrderByBorrowerId();
    }
}
