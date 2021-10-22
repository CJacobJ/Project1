package com.expense.model.employees;

import com.expense.model.Name;
import com.expense.model.requests.Request;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "employees")
@DiscriminatorColumn(name = "manager_status", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name_id", referencedColumnName = "name_id")
    private Name employeeName;

    @Column(columnDefinition = "varchar(40)", name = "username")
    private String userName;

    @Column(columnDefinition = "varchar(60)", name = "email_Address")
    private String emailAddress;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id"/*, referencedColumnName = "employee_id", nullable = false*/)
    private List<Request> requestList;

    public Employee() {
        requestList = new ArrayList<>();
    }

    public Employee(Name newName, String newUserName, String newEmailAddress) {
        employeeName = newName;
        userName = newUserName;
        emailAddress = newEmailAddress;
        requestList = new ArrayList<>();
    }

    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Adds a Request to requestList.
     * @param newRequest The Request to be added.
     */
    public void addRequest(Request newRequest) {
        requestList.add(newRequest);
        //newRequest.setParentID(employeeID);
    }

    /**
     * Gets a request from requestList.
     * @param requestNum The number of the Request to return.
     * @return The requested Request.
     */
    public Request getRequest(int requestNum) {
        return requestList.get(requestNum);
    }

    /**
     * Gets the length of the requestList.
     * @return The size of the list.
     */
    public int requestListLength() {
        int size = requestList.size();
        return size;
    }

    /**
     * Shows whether the employee is a manager.
     * @return true if manager, false if regular employee.
     */
    public boolean isManager() {
        return false;
    }

    @Override
    public String toString() {
        String employeeString = employeeID + ", " + employeeName.getFullName() + ", " + userName + ", " + emailAddress + ", " + requestListLength() + ", " + isManager();

        return employeeString;
    }

    public Name getEmployeeName() {
        return employeeName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
