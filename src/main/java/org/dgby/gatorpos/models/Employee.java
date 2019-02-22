package org.dgby.gatorpos.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;

import org.dgby.gatorpos.ConnectionManager;

public class Employee {
    private IntegerProperty id;
    private StringProperty firstName;
    private StringProperty lastName;
    private IntegerProperty login;

    private static ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    public Employee(Integer id, String firstName, String lastName, Integer login) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.login = new SimpleIntegerProperty(login);
    }

    public static void updateEmployees() {
        ConnectionManager.createTable("Employees",
                new String[] { "fname TEXT", "lname TEXT", "login INTEGER NOT NULL UNIQUE" });

        employeeList.clear();
        try {
            ConnectionManager.executeQuery("SELECT rowid AS id,* FROM Employees", resultSet -> {
                while (resultSet.next())
                    employeeList.add(new Employee(resultSet.getInt("id"), resultSet.getString("fname"),
                        resultSet.getString("lname"), resultSet.getInt("login")));
            });
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    public static ObservableList<Employee> getEmployees() {
        return employeeList;
    }

    public static void addEmployee(String firstName, String lastName, Integer login) {
        try {
            Integer id = ConnectionManager.insertRow("Employees",
                    new String[] { "fname", "lname", "login" },
                    new Object[] { firstName, lastName, login });
            
            ConnectionManager.executeQuery("SELECT rowid AS id,* FROM Employees WHERE rowid = " + id, resultSet -> {
                while (resultSet.next())
                    employeeList.add(new Employee(resultSet.getInt("id"), resultSet.getString("fname"),
                        resultSet.getString("lname"), resultSet.getInt("login")));
            });
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    public static void deleteEmployee(Employee employee) {
        try {
            employeeList.remove(employee);
            ConnectionManager.executeUpdate("DELETE FROM Employees WHERE rowid = " + employee.getId());
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id.set(id);
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * @return the login
     */
    public Integer getLogin() {
        return login.get();
    }

    /**
     * @param login the login to set
     */
    public void setLogin(Integer login) {
        this.login.set(login);
    }
}
