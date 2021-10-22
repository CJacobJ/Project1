package com.expense.testing.model.employees;

import com.expense.model.Name;
import com.expense.model.employees.Employee;
import com.expense.model.requests.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class EmployeeTesting {
    Employee testEmployee;
    Name testName = mock(Name.class);

    @Before
    public void init() {
        testEmployee = new Employee(testName, "testUser", "testEmail");
    }

    @Test
    public void testRequestList() {
        Request mockRequest = mock(Request.class);

        testEmployee.addRequest(mockRequest);

        Request actualRequest = testEmployee.getRequest(0);

        Assert.assertSame("Did not recieve same object.", mockRequest, actualRequest);
    }

    @Test
    public void testIsManager() {
        Assert.assertFalse("Employee claims to be Manager!", testEmployee.isManager());
    }
}
