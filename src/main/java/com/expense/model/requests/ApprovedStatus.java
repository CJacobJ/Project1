package com.expense.model.requests;

public class ApprovedStatus extends ApprovalStatus{

    @Override
    public String toString() {
        return "approved";
    }

    @Override
    public int toInt() {
        return 1;
    }

    @Override
    public boolean isPending() {
        return false;
    }

    @Override
    public boolean isApproved() {
        return true;
    }

    @Override
    public boolean isDenied() {
        return false;
    }

    @Override
    public void sortByStatus(Request sortRequest, RequestListSet requestListSet) {
        requestListSet.addApproved(sortRequest);
    }
}
