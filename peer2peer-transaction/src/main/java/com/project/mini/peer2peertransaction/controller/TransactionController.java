package com.project.mini.peer2peertransaction.controller;

import com.project.mini.peer2peertransaction.model.Transaction;
import com.project.mini.peer2peertransaction.request.TransactionRequest;
import com.project.mini.peer2peertransaction.response.MessageResponse;
import com.project.mini.peer2peertransaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse<List<Transaction>>> selectAllTransaction() {
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success get all transaction data");
        res.put("data", transactionService.allTransactions());
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @GetMapping("/{tran_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse<Transaction>> selectUniqueTransaction(@PathVariable("tran_id") int tranId) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success find transaction data");
        res.put("data", transactionService.findTheTransaction(tranId));
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse> addTransaction (@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction(transactionRequest);
        transactionService.insert(transaction);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success add transaction data");
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse<Transaction>> updateTransaction (
            @RequestBody Transaction transaction
    ) {
        transactionService.update(transaction);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success update transaction data");
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @PostMapping("/payloan")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse<Transaction>> payLoan (
            @RequestParam("tran_id") int transId,
            @RequestParam("user_id_borrower") int borrowerId) {
        transactionService.payLoanWithInterest(borrowerId, transId);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success update transaction data");
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
