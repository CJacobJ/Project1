package com.expense.model;


import javax.persistence.*;

@Entity
@Table(name="names")
public class Name {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name_id")
    private long nameID;

    @Column(columnDefinition = "varchar(40)", name = "first_name")
    private String firstName;

    @Column(columnDefinition = "varchar(40)", name = "last_name")
    private String lastName;

    public Name(int newNameID, String newFirstName, String newLastName) {
        nameID = newNameID;
        firstName = newFirstName;
        lastName = newLastName;
    }

    public Name(String newFirstName, String newLastName) {
        firstName = newFirstName;
        lastName = newLastName;
    }

    private Name() {

    }

    /**
     * Gets the full name.
     * @return Returns the full name, first and last divided by a space.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Sets the full name.
     * @param newFirstName New first name.
     * @param newLastName New last name.
     */
    public void setFullName(String newFirstName, String newLastName) {
        firstName = newFirstName;
        lastName = newLastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
