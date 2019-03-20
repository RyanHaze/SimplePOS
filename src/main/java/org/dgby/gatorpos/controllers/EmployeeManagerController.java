package org.dgby.gatorpos.controllers;

import java.io.IOException;

import javafx.scene.control.*;
import org.dgby.gatorpos.SceneManager;
import org.dgby.gatorpos.models.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeeManagerController {

    // Declare all Textfields
    @FXML
    private TextField firstName_TF, lastName_TF, login_TF;

    // Declare the Tableview and tableColumns
    @FXML
    private TableView<Employee> employee_Table;
    @FXML
    private TableColumn<Employee, String> firstName_Col, lastName_Col;
    @FXML
    private TableColumn<Employee, Integer> employeeId_Col, login_Col;

    // Declare all buttons
    @FXML
    private Button back_Button, home_Button, addNewEmployee_Button, deleteEmployee_Button;

    // Declare title label
    @FXML
    private Label title_Label;

    // Initialize is first called when the scene is loaded
    @FXML
    public void initialize() {

        employeeId_Col.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        firstName_Col.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastName_Col.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        login_Col.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("login"));

        Employee.updateEmployees();
        employee_Table.setItems(Employee.getEmployees());
    }

    // Add employee to the tableview
    @FXML
    public void addEmployee() {
        // Get the textfields input
        String firstName = this.firstName_TF.getText();
        String lastName = this.lastName_TF.getText();
        String login = this.login_TF.getText();

        // Call Class Employee's addEmployee
        if ((firstName.length() > 0) && (lastName.length() > 0) && (login.length() > 0))
            Employee.addEmployee(firstName, lastName, Integer.valueOf(login));

    }

    // Delete and employee from the tableview
    @FXML
    public void deleteEmployee() {
        Employee employee = employee_Table.getSelectionModel().getSelectedItem();

        if (employee != null)
            Employee.deleteEmployee(employee);
        else
            System.out.println("You need to select an employee to delete!"); // TODO: Add a proper form alert for this.
    }

    // Back to the manager functions screen
    @FXML
    public void back_to_mngr_functions_scrn(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("MainManager");
    }

    // Back to the home screen
    @FXML
    public void back_to_home(ActionEvent event) throws IOException {
        SceneManager.getInstance().changeParent("Home");
    }
}
