package com.expense.persistance.hibernate.connectors;

import com.expense.model.Name;
import com.expense.model.employees.Employee;
import com.expense.model.requests.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class HibernateConnector {

    private SessionFactory sessionFactory;
    private Logger log = LogManager.getLogger(HibernateConnector.class.getName());

    public HibernateConnector() {
        configure();
    }

    /**
     * Loads the config from file
     */
    private void configure() {
        Configuration configuration = new Configuration().configure();

        if(configuration != null) {
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
    }

    /**
     * Returns a single Name by its id.
     * @param id The Name's id.
     * @return The Name asked for.
     */
    public Name getSingleNameByID(long id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Name newName = (Name) session.get(Name.class, id);

        tx.commit();
        return newName;
    }

    public Employee getSingleEmployeeByUsername(String username) {
        Employee newEmployee = getSingleEmployeeByID(getEmployeeIDByUsername(username));
        return newEmployee;
    }

    public int getEmployeeIDByUsername(String username) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Integer id = 0;
        Boolean gotID = false;

        String sql = "SELECT employee_id FROM employees WHERE username = :user";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter("user", username);

        List<Integer> results = query.list();

        for(Integer integer : results) {
            id = integer;
            gotID = true;
        }

        tx.commit();

        if (gotID) {
            return id.intValue();
        } else {
            return 0;
        }
    }

    /**
     * Gets an Employee based on its id.
     * @param id The Employee's id.
     * @return The Employee object.
     */
    public Employee getSingleEmployeeByID(int id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Employee newEmployee = (Employee) session.get(Employee.class, id);

        tx.commit();
        return newEmployee;
    }

    /**
     * Gets a single Request based on id.
     * @param id The Request's id.
     * @return The Request object.
     */
    public Request getSingleRequestByID(int id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Request newRequest = (Request) session.get(Request.class, id);

        tx.commit();

        return newRequest;
    }

    /**
     * Saves a new Request to the database.
     * @param request The new Request to be saved.
     */
    public void saveRequest(Request request) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(request);
        tx.commit();
    }

    /**
     * Updates an existing Request on the database.
     * @param request The current Request.
     */
    public void updateRequest(Request request) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(request);
        tx.commit();
    }

    /**
     * Saves a new Employee to the database.
     * @param employee The new Employee to be saved.
     */
    public void saveNewEmployee(Employee employee, String newPass) {
        saveEmployee(employee);
        updatePassword(employee, newPass);
    }

    public void saveEmployee(Employee employee) {
        System.out.println("Start save");
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println("Sess");
        session.save(employee);
        System.out.println("commit");

        tx.commit();
        System.out.println("End save");
    }

    public void updatePassword(Employee employee, String newPass) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hash = BCrypt.hashpw(newPass, BCrypt.gensalt(11));
        String sql = "UPDATE employees SET password = :empass WHERE employee_id = :emid";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter("empass", hash);
        query.setParameter("emid", employee.getEmployeeID());

        query.executeUpdate();
        tx.commit();
    }


    /**
     * Updates an existing Employee on the database.
     * @param employee The current Employee.
     */
    public void updateEmployee(Employee employee) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(employee);
        tx.commit();
    }

    /**
     * Saves a new Name to the database.
     * @param name The new Name.
     */
    public void saveName(Name name) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(name);
        tx.commit();
    }

    /**
     * Updates an existing Name on the database.
     * @param name The current Name.
     */
    public void updateName(Name name) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(name);
        tx.commit();
    }

    public boolean checkPassword(String username, String entry) {
        log.warn("Password check for: " + username);

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hash = "";
        Boolean gotHash = false;

        String sql = "SELECT password FROM employees WHERE username = :user";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter("user", username);

        List<String> results = query.list();

        for(String strings : results) {
            hash = strings;
            gotHash = true;
        }

        tx.commit();

        if (gotHash) {
            return BCrypt.checkpw(entry, hash);
        } else {
            log.warn("Incorrect password for: " + username);
            return false;
        }
    }
}
