package com.revature.woodgateP1.models.DTOs;

public class IncomingReimbDescDTO {
    private int reimbId;
    private String description;

    public IncomingReimbDescDTO() {}

    public IncomingReimbDescDTO(int reimbId, String description) {
        this.reimbId = reimbId;
        this.description = description;
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "IncomingReimbDescDTO{" +
                "reimbId=" + reimbId +
                ", description='" + description + '\'' +
                '}';
    }
}
