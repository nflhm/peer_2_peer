package com.project.mini.peer2peertransaction.model;

import com.project.mini.peer2peertransaction.request.TransactionRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(name = "tran_id")
    private int tranId;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "user_id_borrower")
    private int borrowerId;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "tran_date")
    private LocalDate tranDate;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "payment_status")
    private Boolean paymentStatus;
    @Column(name = "delete_status")
    private Boolean deleteStatus;

    public Transaction() {
    }

    public Transaction(int tranId, int productId, int borrowerId, Double amount, LocalDate tranDate, LocalDate dueDate, Boolean paymentStatus, Boolean deleteStatus) {
        this.tranId = tranId;
        this.productId = productId;
        this.borrowerId = borrowerId;
        this.amount = amount;
        this.tranDate = tranDate;
        this.dueDate = dueDate;
        this.paymentStatus = paymentStatus;
        this.deleteStatus = deleteStatus;
    }

    public Transaction(TransactionRequest request) {
        this.tranId = request.getTranId();
        this.productId = request.getProductId();
        this.borrowerId = request.getBorrowerId();
        this.amount = request.getAmount();
        this.tranDate = request.getTranDate();
        this.dueDate = request.getDueDate();
        this.paymentStatus = request.getPaymentStatus();
        this.deleteStatus = request.getDeleteStatus();
    }

    public int getTranId() {
        return tranId;
    }

    public void setTranId(int tranId) {
        this.tranId = tranId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getTranDate() {
        return tranDate;
    }

    public void setTranDate(LocalDate tranDate) {
        this.tranDate = tranDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
