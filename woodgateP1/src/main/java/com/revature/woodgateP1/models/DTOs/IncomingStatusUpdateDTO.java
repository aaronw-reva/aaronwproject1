package com.revature.woodgateP1.models.DTOs;

public class IncomingStatusUpdateDTO {
    private int reimbId;
    private String status;

    //boilerplate

    public IncomingStatusUpdateDTO() {}

    public IncomingStatusUpdateDTO(int reimbId, String status) {
        this.reimbId = reimbId;
        this.status = status;
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IncomingStatusUpdateDTO{" +
                "reimbId=" + reimbId +
                ", status='" + status + '\'' +
                '}';
    }
}
