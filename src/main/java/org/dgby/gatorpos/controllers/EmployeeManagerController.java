package org.dgby.gatorpos.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import org.dgby.gatorpos.ConnectionManager;
import org.dgby.gatorpos.SceneManager;
import org.dgby.gatorpos.models.Employee;
import org.w3c.dom.Text;

public class EmployeeManagerController
{
    /* This class controls the employee/manager scene
       It presents a table view of all employees and
       access to either add/change/remove data and privileges.
    */

    //configure the table
    @FXML private TableView<Employee> employee_table;
    @FXML private TableColumn<Employee, String> firstNameCol;
    @FXML private TableColumn<Employee, String> lastNameCol;
    @FXML private TableColumn<Employee, String> employeeIdCol;
    @FXML private TableColumn<Employee, String> loginCol;

    // create new employee objects
    @FXML private TextField firstNameText;
    @FXML private TextField lastNameText;
    @FXML private TextField idText;
    @FXML private TextField loginText;

    @FXML private Button backButton;
    @FXML private Button homeButton;

    @FXML public void initialize()
    {
        //set up columns in the table
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("lastName"));
        employeeIdCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("id"));
        loginCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("login"));
        //load some dummy data
        employee_table.setItems(getEmployee());
    }

    //return an observable list of employee objects
    public ObservableList<Employee> getEmployee()
    {
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        //call method to return array of strings from database
        String[] strings_for_Employees = ConnectionManager.populateTable();

        //now we have our string so for loop to create employees with some bullshit code
        for(int i = 0; i < strings_for_Employees.length; i++)
        {
            if(strings_for_Employees[i] == null) break;
            employees.add(new Employee(strings_for_Employees[i+1], strings_for_Employees[i+2], Integer.parseInt(strings_for_Employees[i]), Integer.parseInt(strings_for_Employees[i+3])));
            i = i+3;
        }

        return employees;
    }

    //store the created employee objects in the database.
    public void addEmployeePushed()
    {
        String fname = firstNameText.getText();
        String lname = lastNameText.getText();
        String idString = idText.getText();
        String loginString = loginText.getText();
        //can convert better but for lack of brain power and love of increasing instructions
        int id = Integer.parseInt(idString);
        int login = Integer.parseInt(loginString);
        //now we can call insert employee method in connection manager
        ConnectionManager.insertEmployee(id, login, fname, lname);
    }
    //delete an employee FIRED!
    public void deleteEmployeePushed()
    {
        // read the id
        String idString = idText.getText();
        int id = Integer.parseInt(idString);
        // call delete employee in connection manager
        ConnectionManager.deleteEmployee(id);
    }



    // The functions below manage changing to the previous screens

    public void back_to_mngr_functions_scrn(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().activate("MainManager");
    }

    public void back_to_home(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().activate("Home");
    }
}
