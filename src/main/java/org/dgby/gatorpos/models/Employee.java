package org.dgby.gatorpos.models;


import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

//Class to create employees
public class Employee {
    //initialize private fields
    private SimpleStringProperty firstName, lastName,id, login;



    public Employee(String firstName, String lastName, int id, int login) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.id = new SimpleStringProperty(Integer.toString(id));
        this.login = new SimpleStringProperty(Integer.toString(login));
    }

    //getters and setters
    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setFirstName(SimpleStringProperty firstName) {
        this.firstName = firstName;
    }

    public void setLastName(SimpleStringProperty lastName) {
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = new SimpleStringProperty(Integer.toString(id));
    }

    public String getId()
    {
        return id.get();
    }
    public void setLogin(int login)
    {
        this.login = new SimpleStringProperty(Integer.toString(login));
    }
    public String getLogin()
    {
        return login.get();
    }


}
