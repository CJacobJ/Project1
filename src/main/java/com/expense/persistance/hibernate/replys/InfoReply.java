package com.expense.persistance.hibernate.replys;

import com.expense.model.Name;

public class InfoReply {

    private String firstName;
    private String lastName;
    private String emailAddress;

    public InfoReply(Name newName, String newEmail) {
        firstName = newName.getFirstName();
        lastName = newName.getLastName();
        emailAddress = newEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
