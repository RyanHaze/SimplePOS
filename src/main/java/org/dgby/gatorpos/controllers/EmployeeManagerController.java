package org.dgby.gatorpos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import java.io.IOException;

import org.dgby.gatorpos.SceneManager;
import org.dgby.gatorpos.models.Employee;

public class EmployeeManagerController
{
    @FXML private TableView<Employee> employee_table;
    @FXML private TableColumn<Employee, String> firstNameCol;
    @FXML private TableColumn<Employee, String> lastNameCol;
    @FXML private TableColumn<Employee, Integer> employeeIdCol;
    @FXML private TableColumn<Employee, Integer> loginCol;

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField login;

    @FXML private Button backButton;
    @FXML private Button homeButton;

    @FXML public void initialize()
    {
        employeeIdCol.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("lastName"));
        loginCol.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("login"));
        
        employee_table.setItems(Employee.getEmployees());
    }

    public void addEmployee()
    {
        // TODO: Add check to see if anything is empty, or if login is in use.
        Employee.addEmployee(firstName.getText(), lastName.getText(), Integer.valueOf(login.getText()));
    }
    
    public void deleteEmployee()
    {
        Employee employee = employee_table.getSelectionModel().getSelectedItem();
        if (employee != null)
            Employee.deleteEmployee(employee);
        else
            System.out.println("You need to select an employee to delete!"); // TODO: Add a proper form alert for this.
    }

    public void back_to_mngr_functions_scrn(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().activate("MainManager");
    }

    public void back_to_home(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().activate("Home");
    }
}
