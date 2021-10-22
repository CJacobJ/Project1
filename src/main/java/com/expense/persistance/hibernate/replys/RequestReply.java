package com.expense.persistance.hibernate.replys;

import com.expense.model.employees.Employee;
import com.expense.model.requests.ApprovalStatus;
import com.expense.model.requests.Request;
import com.expense.persistance.hibernate.connectors.HibernateConnector;

public class RequestReply {

    private String approvalName;

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    private String approvalStatus;

    private double amount;

    private String info;

    private String reason;

    public RequestReply() {

    }

    public RequestReply(String newApprovalName, ApprovalStatus newApprovalStatus, double newAmount, String newInfo, String newReason) {
        approvalName = newApprovalName;
        approvalStatus = newApprovalStatus.toString();
        amount = newAmount;
        info = newInfo;
        reason = newReason;
    }

    public static RequestReply generateReply(Request request, HibernateConnector connector) {
        String newApprovalName;
        if (request.getApprovalID() != null) {
            Employee manager = connector.getSingleEmployeeByID(request.getApprovalID());
            newApprovalName = manager.getEmployeeName().getFullName();
        } else {
            newApprovalName = "";
        }

        ApprovalStatus newApprovalStatus = request.getApprovalStatus();
        double newAmount = request.getAmount();
        String newInfo = request.getInfo();
        String newReason = request.getReason();

        return new RequestReply(newApprovalName, newApprovalStatus, newAmount, newInfo, newReason);
    }
}
