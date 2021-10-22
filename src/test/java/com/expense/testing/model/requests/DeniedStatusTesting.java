package com.expense.testing.model.requests;

import com.expense.model.requests.ApprovalStatus;
import com.expense.model.requests.DeniedStatus;
import com.expense.model.requests.Request;
import com.testing.TestingRequestListSetImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class DeniedStatusTesting {
    DeniedStatus testingDeniedStatus;

    @Before
    public void init() {
        testingDeniedStatus = new DeniedStatus();
    }

    @Test
    public void testToString() {
        Assert.assertEquals("testingStatus toString incorrect!", "denied", testingDeniedStatus.toString());
    }

    @Test
    public void TestToInt() {
        Assert.assertEquals("DeniedStatus toInt incorrect!", 2, testingDeniedStatus.toInt());
    }

    @Test
    public void testIsPending() {
        Assert.assertFalse("isPending is true!", testingDeniedStatus.isPending());
    }

    @Test
    public void testIsApproved() {
        Assert.assertFalse("isApproved is true!", testingDeniedStatus.isApproved());
    }

    @Test
    public void testIsDenied() {
        Assert.assertTrue("isDenied is false!", testingDeniedStatus.isDenied());
    }

    @Test
    public void testSortByStatusPending() {
        TestingRequestListSetImp mockRequestListSet = mock(TestingRequestListSetImp.class);
        Request mockRequest = mock(Request.class);

        doThrow(new RuntimeException("addPending called!")).when(mockRequestListSet).addPending(mockRequest);
        testingDeniedStatus.sortByStatus(mockRequest, mockRequestListSet);
    }

    @Test
    public void testSortByStatusApproved() {
        TestingRequestListSetImp mockRequestListSet = mock(TestingRequestListSetImp.class);
        Request mockRequest = mock(Request.class);

        doThrow(new RuntimeException("addApproved called!")).when(mockRequestListSet).addApproved(mockRequest);
        testingDeniedStatus.sortByStatus(mockRequest, mockRequestListSet);
    }

    @Test(expected = RuntimeException.class)
    public void testSortByStatusDenied() {
        TestingRequestListSetImp mockRequestListSet = mock(TestingRequestListSetImp.class);
        Request mockRequest = mock(Request.class);

        doThrow(new RuntimeException("addDenied called!")).when(mockRequestListSet).addDenied(mockRequest);
        testingDeniedStatus.sortByStatus(mockRequest, mockRequestListSet);
    }
}

