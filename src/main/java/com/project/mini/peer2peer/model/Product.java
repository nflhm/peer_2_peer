package com.project.mini.peer2peer.model;

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
    @Column(name = "interest")
    private Float interest;
    @Column(name = "due_time")
    private int due_time;
    @Column(name = "delete_status")
    private Boolean deleteStatus;

    public Product() {
    }

    public Product(int productId, int lenderId, Float interest, int due_time, Boolean deleteStatus) {
        this.productId = productId;
        this.lenderId = lenderId;
        this.interest = interest;
        this.due_time = due_time;
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

    public Float getInterest() {
        return interest;
    }

    public void setInterest(Float interest) {
        this.interest = interest;
    }

    public int getDue_time() {
        return due_time;
    }

    public void setDue_time(int due_time) {
        this.due_time = due_time;
    }

    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
