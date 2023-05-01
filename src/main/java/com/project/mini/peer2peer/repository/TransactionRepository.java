package com.project.mini.peer2peer.repository;

import com.project.mini.peer2peer.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findTransactionByTranId(Integer integer);
    List<Transaction> findAllByOrderByBorrowerId();
    List<Transaction> findAllByOrderByProductId();
//    @Modifying(clearAutomatically = true)
//    @Query("update Transaction t set t.deleteStatus=1 where t.tranId=:tranId")
//    void deleteByTranId(Integer tranId);
}
