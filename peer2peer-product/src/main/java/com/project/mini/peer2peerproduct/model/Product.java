package com.project.mini.peer2peerproduct.model;

import com.project.mini.peer2peerproduct.request.ProductRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    private int productId;
    @Column(name = "user_id_lender")
    private int lenderId;

    @Column(name = "amount")
    private Float amount;
    @Column(name = "interest")
    private Float interest;
    @Column(name = "due_time")
    private int dueTime;
    @Column(name = "delete_status")
    private Boolean deleteStatus;

    public Product() {
    }

    public Product(ProductRequest request) {
        this.productId = request.getProductId();
        this.lenderId = request.getLenderId();
        this.amount = request.getAmount();
        this.interest = request.getInterest();
        this.dueTime = request.getDueTime();
        this.deleteStatus = request.getDeleteStatus();
    }

    public Product(int productId, int lenderId, Float amount, Float interest, int dueTime, Boolean deleteStatus) {
        this.productId = productId;
        this.lenderId = lenderId;
        this.amount = amount;
        this.interest = interest;
        this.dueTime = dueTime;
        this.deleteStatus = deleteStatus;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getLenderId() {
        return lenderId;
    }

    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
    }

    public Float getAmount() { return amount; }

    public void setAmount(Float amount) { this.amount = amount; }

    public Float getInterest() {
        return interest;
    }

    public void setInterest(Float interest) {
        this.interest = interest;
    }

    public int getDueTime() {
        return dueTime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", lenderId=" + lenderId +
                ", amount=" + amount +
                ", interest=" + interest +
                ", dueTime=" + dueTime +
                ", deleteStatus=" + deleteStatus +
                '}';
    }

    public void setDueTime(int dueTime) {
        this.dueTime = dueTime;
    }

    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
