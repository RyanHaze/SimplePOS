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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import org.dgby.gatorpos.SceneManager;
import org.dgby.gatorpos.models.Employee;

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
    @FXML private TableColumn<Employee, String> emplyeeIdCol;
    @FXML private TableColumn<Employee, LocalDate> hireDateCol;
    @FXML private TableColumn<Employee, String> levelCol;

    @FXML private Button backButton;
    @FXML private Button homeButton;

    @FXML public void initialize()
    {
        //set up columns in the table
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("lastName"));
        emplyeeIdCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("id"));
        levelCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("level"));
        hireDateCol.setCellValueFactory(new PropertyValueFactory<Employee,LocalDate>("hireDate"));

        //load some dummy date
        employee_table.setItems(getEmployee());
    }

    //return an observable list of employee objects
    public ObservableList<Employee> getEmployee()
    {
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        employees.add(new Employee("Ryan", "Hazewski", LocalDate.of(2017,Month.FEBRUARY,12), 4839, 1));
        employees.add(new Employee("John", "Peel", LocalDate.of(2019,Month.JANUARY,25), 8741, 1));
        employees.add(new Employee("Beverly", "Sanders", LocalDate.of(2018,Month.SEPTEMBER,4), 1234, 2));

        return employees;

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
