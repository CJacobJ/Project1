package com.expense.model.requests;

import com.expense.exceptions.ApprovalStatusIndexOutOfBounds;
import com.expense.model.employees.Employee;
import com.expense.persistance.hibernate.converters.ApprovalStatusConverter;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestID;

    /*@ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Integer parentID;*/

    /*@ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")*/
    @Column(name = "manager_id")
    private Integer approvalID;

    @Convert(converter = ApprovalStatusConverter.class)
    @Column(name = "status")
    private ApprovalStatus approvalStatus;

    @Column(columnDefinition = "decimal", name = "amount")
    private double amount;

    @Column(columnDefinition = "text", name = "info")
    private String info;

    @Column(columnDefinition = "text", name = "reason")
    private String reason;

    public Request() {
    }

    public Request(double newAmount, String newInfo) {
        amount = newAmount;
        info = newInfo;
        reason = "pending approval";
        approvalStatus = new PendingStatus();
    }

    public Request(int newRequestID, int newApprovalID, double newAmount, String newInfo, String newReason) {
        requestID = newRequestID;
        approvalID = newApprovalID;
        amount = newAmount;
        info = newInfo;
        reason = newReason;
    }

    /**
     * Adds an ApprovalStatus to the Request.
     * @param newApprovalID 0 for pending, 1 for approved, 2 for denied.
     * @param newReason The reason for the change.
     * @throws ApprovalStatusIndexOutOfBounds Thrown if newApprovalID is invalid
     */
    public void setStatus(int newApprovalID, String newReason) throws ApprovalStatusIndexOutOfBounds{
        reason = newReason;
        switch (newApprovalID) {
            case 0:
                approvalStatus = new PendingStatus();
                break;
            case 1:
                approvalStatus = new ApprovedStatus();
                break;
            case 2:
                approvalStatus = new DeniedStatus();
                break;
            default:
                throw new ApprovalStatusIndexOutOfBounds("newApprovalID is not a legal value! newApprovalID = " + newApprovalID);
        }
    }

    /**
     * Places the Request into the appropriate list based on the ApprovalStatus.
     * @param requestListSet The RequestListSet for this Request to be placed in.
     */
    public void sortByStatus(RequestListSet requestListSet) {
        approvalStatus.sortByStatus(this, requestListSet);
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    @Override
    public String toString() {
        return requestID + ", " + approvalID + ", " + approvalStatus + ", " + amount + ", " + info + ", " + reason;
    }

    public Integer getApprovalID() {
        return approvalID;
    }

    public void setApprovalID(Integer approvalID) {
        this.approvalID = approvalID;
    }

    /*public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }*/

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
}
