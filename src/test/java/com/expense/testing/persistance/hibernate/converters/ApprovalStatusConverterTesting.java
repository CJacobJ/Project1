package com.expense.testing.persistance.hibernate.converters;

import com.expense.exceptions.ApprovalStatusIndexOutOfBounds;
import com.expense.model.requests.ApprovalStatus;
import com.expense.model.requests.ApprovedStatus;
import com.expense.model.requests.DeniedStatus;
import com.expense.model.requests.PendingStatus;
import com.expense.persistance.hibernate.converters.ApprovalStatusConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApprovalStatusConverterTesting {

    ApprovalStatusConverter testingApprovalStatusConvertor;

    @Before
    public void init() {
        testingApprovalStatusConvertor = new ApprovalStatusConverter();
    }

    @Test
    public void testConvertToDatabaseColumn0() {
        ApprovalStatus mockApprovalStatus = mock(ApprovalStatus.class);
        when(mockApprovalStatus.toInt()).thenReturn(0);

        Integer testInteger = testingApprovalStatusConvertor.convertToDatabaseColumn(mockApprovalStatus);

        Assert.assertEquals("convertToDatabaseColumn did not return 0!", new Integer(0), testInteger);
    }

    @Test
    public void testConvertToDatabaseColumn1() {
        ApprovalStatus mockApprovalStatus = mock(ApprovalStatus.class);
        when(mockApprovalStatus.toInt()).thenReturn(1);

        Integer testInteger = testingApprovalStatusConvertor.convertToDatabaseColumn(mockApprovalStatus);

        Assert.assertEquals("convertToDatabaseColumn did not return 1!", new Integer(1), testInteger);
    }

    @Test
    public void testConvertToDatabaseColumn2() {
        ApprovalStatus mockApprovalStatus = mock(ApprovalStatus.class);
        when(mockApprovalStatus.toInt()).thenReturn(2);

        Integer testInteger = testingApprovalStatusConvertor.convertToDatabaseColumn(mockApprovalStatus);

        Assert.assertEquals("convertToDatabaseColumn did not return 2!", new Integer(2), testInteger);
    }

    @Test
    public void testConvertToEntityAttributePending() {
        Integer testInteger = new Integer(0);

        ApprovalStatus testApprovalStatus = testingApprovalStatusConvertor.convertToEntityAttribute(testInteger);

        Assert.assertTrue("convertToEntityAttribute did not return PendingStatus! testApprovalStatus = " + testApprovalStatus, testApprovalStatus instanceof PendingStatus);
    }

    @Test
    public void testConvertToEntityAttributeApproved() {
        Integer testInteger = new Integer(1);

        ApprovalStatus testApprovalStatus = testingApprovalStatusConvertor.convertToEntityAttribute(testInteger);

        Assert.assertTrue("convertToEntityAttribute did not return ApprovedStatus! testApprovalStatus = " + testApprovalStatus, testApprovalStatus instanceof ApprovedStatus);
    }

    @Test
    public void testConvertToEntityAttributeDenied() {
        Integer testInteger = new Integer(2);

        ApprovalStatus testApprovalStatus = testingApprovalStatusConvertor.convertToEntityAttribute(testInteger);

        Assert.assertTrue("convertToEntityAttribute did not return DeniedStatus! testApprovalStatus = " + testApprovalStatus, testApprovalStatus instanceof DeniedStatus);
    }

    @Test(expected = ApprovalStatusIndexOutOfBounds.class)
    public void testConvertToEntityAttributeException() {
        Integer testInteger = new Integer(3);

        testingApprovalStatusConvertor.convertToEntityAttribute(testInteger);
    }
}
