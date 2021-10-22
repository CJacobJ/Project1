package com.expense.testing.model.requests;

import com.expense.model.requests.PendingStatus;
import com.expense.model.requests.Request;
import com.testing.FunctionCalled;
import com.testing.TestingRequestListSetImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class PendingStatusTesting {
    PendingStatus testingPendingStatus;

    @Before
    public void init() {
        testingPendingStatus = new PendingStatus();
    }

    @Test
    public void testToString() {
        Assert.assertEquals("testingStatus toString incorrect!", "pending", testingPendingStatus.toString());
    }

    @Test
    public void TestToInt() {
        Assert.assertEquals("Pending toInt incorrect!", 0, testingPendingStatus.toInt());
    }

    @Test
    public void testIsPending() {
        Assert.assertTrue("isPending is false!", testingPendingStatus.isPending());
    }

    @Test
    public void testIsApproved() {
        Assert.assertFalse("isApproved is true!", testingPendingStatus.isApproved());
    }

    @Test
    public void testIsDenied() {
        Assert.assertFalse("isDenied is true!", testingPendingStatus.isDenied());
    }

    @Test(expected = RuntimeException.class)
    public void testSortByStatusPending() {
        TestingRequestListSetImp mockRequestListSet = mock(TestingRequestListSetImp.class);
        Request mockRequest = mock(Request.class);

        doThrow(new RuntimeException("addPending called!")).when(mockRequestListSet).addPending(mockRequest);
        testingPendingStatus.sortByStatus(mockRequest, mockRequestListSet);
    }

    @Test
    public void testSortByStatusApproved() {
        TestingRequestListSetImp mockRequestListSet = mock(TestingRequestListSetImp.class);
        Request mockRequest = mock(Request.class);

        doThrow(new RuntimeException("addApproved called!")).when(mockRequestListSet).addApproved(mockRequest);
        testingPendingStatus.sortByStatus(mockRequest, mockRequestListSet);
    }

    @Test
    public void testSortByStatusDenied() {
        TestingRequestListSetImp mockRequestListSet = mock(TestingRequestListSetImp.class);
        Request mockRequest = mock(Request.class);

        doThrow(new RuntimeException("addDenied called!")).when(mockRequestListSet).addDenied(mockRequest);
        testingPendingStatus.sortByStatus(mockRequest, mockRequestListSet);
    }
}
