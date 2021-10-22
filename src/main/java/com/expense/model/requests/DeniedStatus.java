package com.expense.model.requests;

public class DeniedStatus extends ApprovalStatus{

    @Override
    public String toString() {
        return "denied";
    }

    @Override
    public int toInt() {
        return 2;
    }

    @Override
    public boolean isPending() {
        return false;
    }

    @Override
    public boolean isApproved() {
        return false;
    }

    @Override
    public boolean isDenied() {
        return true;
    }

    @Override
    public void sortByStatus(Request sortRequest, RequestListSet requestListSet) {
        requestListSet.addDenied(sortRequest);
    }
}
