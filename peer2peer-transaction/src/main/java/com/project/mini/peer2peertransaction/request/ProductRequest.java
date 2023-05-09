package com.project.mini.peer2peertransaction.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequest {
    private int productId;
    private int lenderId;
    private Float amount;
    private Float interest;
    private int dueTime;
    private Boolean deleteStatus;
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
