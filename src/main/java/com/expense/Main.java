package com.expense;

import com.expense.model.employees.Employee;
import com.expense.model.requests.Request;
import com.expense.persistance.hibernate.posters.EditEmployeeInfo;
import com.expense.persistance.hibernate.posters.LoginAttempt;
import com.expense.persistance.hibernate.connectors.HibernateConnector;
import com.expense.persistance.hibernate.posters.NewRequest;
import com.expense.persistance.hibernate.replys.EmployeeReply;
import com.expense.persistance.hibernate.replys.InfoReply;
import com.expense.persistance.hibernate.replys.RequestReply;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import io.javalin.Javalin;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.staticfiles.Location;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        HibernateConnector hibernateConnector = new HibernateConnector();

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/site", Location.CLASSPATH);
                }
        ).start(7000);
        app.get("/test/", ctx -> ctx.result("Hello World"));
        app.post("/loginattempt", ctx -> {
            LoginAttempt incoming = ctx.bodyAsClass(LoginAttempt.class);
            boolean passTest = hibernateConnector.checkPassword(incoming.getUsername(), incoming.getPassword());
            if (passTest) {
                ctx.status(200);
            } else {
                throw new UnauthorizedResponse("Authentication failed!");
            }
        });
        app.post("/employeehomecheck", ctx -> {
            LoginAttempt incoming = ctx.bodyAsClass(LoginAttempt.class);
            boolean passTest = hibernateConnector.checkPassword(incoming.getUsername(), incoming.getPassword());
            if (passTest) {
                Employee newEmployee = hibernateConnector.getSingleEmployeeByUsername(incoming.getUsername());
                EmployeeReply newReply = new EmployeeReply(newEmployee.getEmployeeName(), newEmployee.requestListLength());
                ctx.status(200);
                ctx.json(newReply);

            } else {
                throw new UnauthorizedResponse("Authentication failed!");
            }
        });
        app.post("/addRequest", ctx -> {
            NewRequest incoming = ctx.bodyAsClass(NewRequest.class);
            boolean passTest = hibernateConnector.checkPassword(incoming.getUsername(), incoming.getPassword());
            if (passTest) {
                Employee newEmployee = hibernateConnector.getSingleEmployeeByUsername(incoming.getUsername());
                Request newRequest = new Request(incoming.getAmount(), incoming.getInfo());
                newEmployee.addRequest(newRequest);
                hibernateConnector.saveRequest(newRequest);
                ctx.status(201);
            } else {
                throw new UnauthorizedResponse("Authentication failed");
            }
        });
        app.post("/myRequests", ctx -> {
            List<Request> requestList;
            List<RequestReply> replyList = new ArrayList<>();
            LoginAttempt incoming = ctx.bodyAsClass(LoginAttempt.class);
            boolean passTest = hibernateConnector.checkPassword(incoming.getUsername(), incoming.getPassword());
            if (passTest) {
                Employee newEmployee = hibernateConnector.getSingleEmployeeByUsername(incoming.getUsername());
                requestList = newEmployee.getRequestList();
                for (Request req : requestList) {
                    replyList.add(RequestReply.generateReply(req, hibernateConnector));
                }
                ctx.status(200);
                ctx.json(replyList);
            } else {
                throw new UnauthorizedResponse("Authentication failed");
            }
        });
        app.post("/employeeInfo", ctx -> {
            LoginAttempt incoming = ctx.bodyAsClass(LoginAttempt.class);
            boolean passTest = hibernateConnector.checkPassword(incoming.getUsername(), incoming.getPassword());
            if (passTest) {
                Employee newEmployee = hibernateConnector.getSingleEmployeeByUsername(incoming.getUsername());
                InfoReply newReply = new InfoReply(newEmployee.getEmployeeName(), newEmployee.getEmailAddress());
                ctx.status(200);
                ctx.json(newReply);
            } else {
                throw new UnauthorizedResponse("Authentication failed");
            }
        });
        app.post("/editEmployeeInfo", ctx -> {
            EditEmployeeInfo incoming = ctx.bodyAsClass(EditEmployeeInfo.class);
            boolean passTest = hibernateConnector.checkPassword(incoming.getUsername(), incoming.getPassword());
            if (passTest) {
                Employee editEmployee = hibernateConnector.getSingleEmployeeByUsername(incoming.getUsername());
                editEmployee.getEmployeeName().setFullName(incoming.getFirstName(), incoming.getLastName());
                editEmployee.setEmailAddress(incoming.getEmail());
                hibernateConnector.saveEmployee(editEmployee);
                ctx.status(201);
            } else {
                throw new UnauthorizedResponse("Authentication failed");
            }
        });

        //System.out.println(hibernateConnector.checkPassword("TestName", "123abc"));

        /*

        Employee newEmployee = hibernateConnector.getSingleEmployeeByID(11);

        System.out.println(newEmployee);

        //Name newName = hibernateConnector.getSingleNameByID(2);
        //Employee newEmployee = new Employee(newName, "TestName", "TestEmail");
        Request newRequest = new Request(200.0, "Need money");

        //hibernateConnector.saveNewEmployee(newEmployee, "123abc");

        newEmployee.addRequest(newRequest);

        System.out.println(newEmployee.requestListLength());

        hibernateConnector.saveRequest(newRequest);*/
    }
}
