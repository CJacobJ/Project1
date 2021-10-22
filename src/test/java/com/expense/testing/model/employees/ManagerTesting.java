package com.expense.testing.model.employees;

import com.expense.model.Name;
import com.expense.model.employees.Manager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ManagerTesting {
    Manager testManager;
    Name testName = mock(Name.class);

    @Before
    public void init() {
        testManager = new Manager(testName, "testUser", "testEmail");
    }

    @Test
    public void testIsManager() {
        Assert.assertTrue("Manager claims to be regular employee!", testManager.isManager());
    }
}
