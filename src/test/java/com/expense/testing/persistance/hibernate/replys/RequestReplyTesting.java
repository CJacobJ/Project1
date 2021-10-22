package com.expense.testing.persistance.hibernate.replys;

import com.expense.model.Name;
import com.expense.model.employees.Employee;
import com.expense.model.requests.ApprovalStatus;
import com.expense.model.requests.Request;
import com.expense.persistance.hibernate.connectors.HibernateConnector;
import com.expense.persistance.hibernate.replys.RequestReply;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestReplyTesting {

    RequestReply testingRequestReply;

    @Before
    public void init() {
        testingRequestReply = new RequestReply();
    }

    @Test
    public void testGenerateReplyNullApprovalID() {
        Request mockRequest = mock(Request.class);
        ApprovalStatus mockApprovalStatus = mock(ApprovalStatus.class);
        HibernateConnector mockHibernateConnector = mock(HibernateConnector.class);

        when(mockRequest.getApprovalID()).thenReturn(null);
        when(mockRequest.getApprovalStatus()).thenReturn(mockApprovalStatus);
        when(mockRequest.getAmount()).thenReturn(200.00);
        when(mockRequest.getInfo()).thenReturn("Testing info");
        when(mockRequest.getReason()).thenReturn("Testing reason");

        when(mockApprovalStatus.toString()).thenReturn("mockstatus");

        testingRequestReply = RequestReply.generateReply(mockRequest, mockHibernateConnector);

        Assert.assertEquals("approvalName incorrect!","", testingRequestReply.getApprovalName());
        Assert.assertEquals("approvalStatus incorrect!", "mockstatus", testingRequestReply.getApprovalStatus());
        Assert.assertEquals("amount incorrect!", 200.00, testingRequestReply.getAmount(), 0.01);
        Assert.assertEquals("info incorrect!", "Testing info", testingRequestReply.getInfo());
        Assert.assertEquals("reason incorrect!", "Testing reason", testingRequestReply.getReason());
    }

    @Test
    public void testGenerateReplyNoNull() {
        Request mockRequest = mock(Request.class);
        ApprovalStatus mockApprovalStatus = mock(ApprovalStatus.class);
        HibernateConnector mockHibernateConnector = mock(HibernateConnector.class);
        Employee mockEmployee = mock(Employee.class);
        Name mockName = mock(Name.class);

        when(mockRequest.getApprovalID()).thenReturn(52);
        when(mockRequest.getApprovalStatus()).thenReturn(mockApprovalStatus);
        when(mockRequest.getAmount()).thenReturn(200.00);
        when(mockRequest.getInfo()).thenReturn("Testing info");
        when(mockRequest.getReason()).thenReturn("Testing reason");

        when(mockApprovalStatus.toString()).thenReturn("mockstatus");

        when(mockHibernateConnector.getSingleEmployeeByID(52)).thenReturn(mockEmployee);

        when(mockEmployee.getEmployeeName()).thenReturn(mockName);

        when(mockName.getFullName()).thenReturn("mockName");

        testingRequestReply = RequestReply.generateReply(mockRequest, mockHibernateConnector);

        Assert.assertEquals("approvalName incorrect!", "mockName", testingRequestReply.getApprovalName());
    }
}
