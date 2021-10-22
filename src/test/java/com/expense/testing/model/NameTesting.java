package com.expense.testing.model;

import com.expense.model.Name;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class NameTesting {

    Name testName;

    @Before
    public void init() {
        testName = new Name(1, "John", "Doe");
    }

    @Test
    public void testGetFullName() {
        Assert.assertEquals("fullName return incorrect!", "John Doe", testName.getFullName());
    }

    @Test
    public void testSetFullNameFirst() throws NoSuchFieldException, IllegalAccessException {
        Class testClass = testName.getClass();
        String testFirstName;
        Field testField = testClass.getDeclaredField("firstName");
        testField.setAccessible(true);

        testName.setFullName("Test", "Name");

        testFirstName = (String)testField.get(testName);

        Assert.assertEquals("firstName incorrect!", "Test", testFirstName);
    }

    @Test
    public void testSetFullNameLast() throws NoSuchFieldException, IllegalAccessException {
        Class testClass = testName.getClass();
        String testLastName;
        Field testField = testClass.getDeclaredField("lastName");
        testField.setAccessible(true);

        testName.setFullName("Test", "Name");

        testLastName = (String)testField.get(testName);

        Assert.assertEquals("lastName incorrect!", "Name", testLastName);
    }
}
