package com.expense.model.requests;

public interface RequestListSet {

    /**
     * Adds a pending request.
     * @param newRequest The request to be added.
     */
    public void addPending(Request newRequest);

    /**
     * Adds a approved request.
     * @param newRequest The request to be added.
     */
    public void addApproved(Request newRequest);

    /**
     * Adds a denied request.
     * @param newRequest The request to be added.
     */
    public void addDenied(Request newRequest);
}
