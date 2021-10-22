package com.expense.testing.model.requests;

import com.expense.exceptions.ApprovalStatusIndexOutOfBounds;
import com.expense.model.requests.*;
import com.testing.TestingRequestListSetImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class RequestTesting {
    Request testRequest;

    @Before
    public void init() {
        testRequest = new Request(30, "test");
    }

    @Test
    public void testSetStatusPending() throws NoSuchFieldException, IllegalAccessException {
        ApprovalStatus tester;

        testRequest.setStatus(0, "testing");

        tester = testRequest.getApprovalStatus();

        Assert.assertTrue("Incorrect ApprovalStatus assigned!", tester instanceof PendingStatus);
    }

    @Test
    public void testSetStatusApproved() throws NoSuchFieldException, IllegalAccessException {
        ApprovalStatus tester;

        testRequest.setStatus(1, "testing");

        tester = testRequest.getApprovalStatus();

        Assert.assertTrue("Incorrect ApprovalStatus assigned!", tester instanceof ApprovedStatus);
    }

    @Test
    public void testSetStatusDenied() throws NoSuchFieldException, IllegalAccessException {
        ApprovalStatus tester;

        testRequest.setStatus(2, "testing");

        tester = testRequest.getApprovalStatus();

        Assert.assertTrue("Incorrect ApprovalStatus assigned!", tester instanceof DeniedStatus);
    }

    @Test(expected = ApprovalStatusIndexOutOfBounds.class)
    public void testSetStatusFailed() {
        testRequest.setStatus(3, "testing");
    }

    @Test(expected = RuntimeException.class)
    public void testSortByStatusPending() throws NoSuchFieldException, IllegalAccessException {
        TestingRequestListSetImp mockRequestListSet = mock(TestingRequestListSetImp.class);
        PendingStatus mockPendingStatus = mock(PendingStatus.class);
        doThrow(new RuntimeException("sortByStatus called!")).when(mockPendingStatus).sortByStatus(testRequest, mockRequestListSet);

        Class testClass = testRequest.getClass();
        Field testField = testClass.getDeclaredField("approvalStatus");
        testField.setAccessible(true);
        testField.set(testRequest, mockPendingStatus);

        testRequest.sortByStatus(mockRequestListSet);
    }
}
