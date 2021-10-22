package com.expense.persistance.hibernate.replys;

import com.expense.model.Name;

public class EmployeeReply {

    public int getListLength() {
        return listLength;
    }

    public void setListLength(int listLength) {
        this.listLength = listLength;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private String firstName;
    private String lastName;
    private int listLength;

    public EmployeeReply(Name newName, int newListLength) {
        firstName = newName.getFirstName();
        lastName = newName.getLastName();
        listLength = newListLength;
    }
}
