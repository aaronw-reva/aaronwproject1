package com.revature.woodgateP1.models.DTOs;

public class IncomingReimbDTO {
    private double amount;
    private String description;
    private int userId;

    //boilerplate

    public IncomingReimbDTO() {}

    public IncomingReimbDTO(double amount, String description, int userId) {
        this.amount = amount;
        this.description = description;
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "IncomingReimbDTO{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}
