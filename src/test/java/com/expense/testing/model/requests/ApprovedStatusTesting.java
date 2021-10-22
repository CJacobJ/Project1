package com.expense.testing.model.requests;

import com.expense.model.requests.ApprovalStatus;
import com.expense.model.requests.ApprovedStatus;
import com.expense.model.requests.Request;
import com.testing.TestingRequestListSetImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ApprovedStatusTesting {
    ApprovedStatus testingApprovedStatus;

    @Before
    public void init() {
        testingApprovedStatus = new ApprovedStatus();
    }

    @Test
    public void testToString() {
        Assert.assertEquals("testingStatus toString incorrect!", "approved", testingApprovedStatus.toString());
    }

    @Test
    public void TestToInt() {
        Assert.assertEquals("ApprovedStatus toInt incorrect!", 1, testingApprovedStatus.toInt());
    }

    @Test
    public void testIsPending() {
        Assert.assertFalse("isPending is true!", testingApprovedStatus.isPending());
    }

    @Test
    public void testIsApproved() {
        Assert.assertTrue("isApproved is false!", testingApprovedStatus.isApproved());
    }

    @Test
    public void testIsDenied() {
        Assert.assertFalse("isDenied is true!", testingApprovedStatus.isDenied());
    }

    @Test
    public void testSortByStatusPending() {
        TestingRequestListSetImp mockRequestListSet = mock(TestingRequestListSetImp.class);
        Request mockRequest = mock(Request.class);

        doThrow(new RuntimeException("addPending called!")).when(mockRequestListSet).addPending(mockRequest);
        testingApprovedStatus.sortByStatus(mockRequest, mockRequestListSet);
    }

    @Test(expected = RuntimeException.class)
    public void testSortByStatusApproved() {
        TestingRequestListSetImp mockRequestListSet = mock(TestingRequestListSetImp.class);
        Request mockRequest = mock(Request.class);

        doThrow(new RuntimeException("addApproved called!")).when(mockRequestListSet).addApproved(mockRequest);
        testingApprovedStatus.sortByStatus(mockRequest, mockRequestListSet);
    }

    @Test
    public void testSortByStatusDenied() {
        TestingRequestListSetImp mockRequestListSet = mock(TestingRequestListSetImp.class);
        Request mockRequest = mock(Request.class);

        doThrow(new RuntimeException("addDenied called!")).when(mockRequestListSet).addDenied(mockRequest);
        testingApprovedStatus.sortByStatus(mockRequest, mockRequestListSet);
    }
}

