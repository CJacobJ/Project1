package com.expense.model.requests;

public abstract class ApprovalStatus {

    protected ApprovalStatus() {
    }

    @Override
    public abstract String toString();

    /**
     * Returns the ApprovalStatus's int.
     * @return The approvalStatus's int.
     */
    public abstract int toInt();

    /**
     * Check whether a request is pending.
     * @return true if request is pending.
     */
    public abstract boolean isPending();

    /**
     * Check whether a request is approved.
     * @return true if request is approved.
     */
    public abstract boolean isApproved();

    /**
     * Check whether a request is denied.
     * @return true if request is denied.
     */
    public abstract boolean isDenied();

    /**
     * Sorts a request into the correct list in a requestListSet.
     * @param sortRequest The request that owns this ApprovalStatus.
     * @param requestListSet The RequestListSet for the request to be added to.
     */
    public abstract void sortByStatus(Request sortRequest, RequestListSet requestListSet);
}
