package sample;


import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

//Class to create employees
public class Employee {
    //initialize private fields
    private SimpleStringProperty firstName, lastName,id, level;
    private LocalDate hireDate;


    public Employee(String firstName, String lastName, LocalDate hireDate, int id, int level) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.hireDate = hireDate;
        this.id = new SimpleStringProperty(Integer.toString(id));
        this.level = new SimpleStringProperty(Integer.toString(level));
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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public void setId(int id) {
        this.id = new SimpleStringProperty(Integer.toString(id));
    }

    public String getId()
    {
        return id.get();
    }
    public void setLevel(int level)
    {
        this.level = new SimpleStringProperty(Integer.toString(level));
    }
    public String getLevel()
    {
        return level.get();
    }


}
