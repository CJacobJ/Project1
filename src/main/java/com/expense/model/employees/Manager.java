package com.expense.model.employees;

import com.expense.model.Name;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Manager extends Employee{

    public Manager() {
        super();
    }

    public Manager(Name newName, String newUserName, String newEmailAddress) {
        super(newName, newUserName, newEmailAddress);
    }

   @Override
    public boolean isManager() {
        return true;
    }
}
