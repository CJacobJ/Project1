package com.expense.model.requests;

public class PendingStatus extends ApprovalStatus{

    @Override
    public String toString() {
        return "pending";
    }

    @Override
    public int toInt() {
        return 0;
    }

    @Override
    public boolean isPending() {
        return true;
    }

    @Override
    public boolean isApproved() {
        return false;
    }

    @Override
    public boolean isDenied() {
        return false;
    }

    @Override
    public void sortByStatus(Request sortRequest, RequestListSet requestListSet) {
        requestListSet.addPending(sortRequest);
    }
}
