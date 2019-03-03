package org.dgby.gatorpos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dgby.gatorpos.SceneManager;
import org.dgby.gatorpos.models.Product;
import org.dgby.gatorpos.models.Product;

import java.io.IOException;

public class ProductManagerController
{

    // Declare Tablieview
    @FXML
    private TableView<Product> product_Table;

    // Declare Table Columns
    @FXML
    private TableColumn<Product, Integer> prodId_Col, prodPrice_Col;
    @FXML
    private TableColumn<Product, String> prodName_Col, prodCat_Col, prodDescription_Col ;

    // Declare Textfields
    @FXML
    private TextField prodName_TF, prodPrice_TF, prodCat_TF, prodDescription_TF;

    // Declare Buttons
    @FXML
    private Button addNewProduct_Button, deleteProduct_Button, home_Button, back_Button;

    // Initialize upon scene start
    @FXML public void initialize()
    {
        prodId_Col.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
        prodName_Col.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        prodPrice_Col.setCellValueFactory(new PropertyValueFactory<Product,Integer>("price"));
        prodCat_Col.setCellValueFactory(new PropertyValueFactory<Product,String>("category"));
        prodDescription_Col.setCellValueFactory(new PropertyValueFactory<Product,String>("description"));

        Product.updateProducts();
        product_Table.setItems(Product.getProducts());
    }

    // Add the product
    public void addProduct()
    {
        String prod_name = this.prodName_TF.getText();
        String prod_price = this.prodPrice_TF.getText();
        String prod_cat = this.prodCat_TF.getText();
        String prod_description = this.prodDescription_TF.getText();

        //call the product
        if ((prod_name.length() > 0) && (prod_price.length() > 0) && (prod_cat.length() > 0) && (prod_description.length() > 0))
        {
            Product.addProduct(prod_name, Integer.valueOf(prod_price), prod_description, prod_cat);
        }


    }

    // Delete the product
    public void deleteProduct()
    {
        Product product = product_Table.getSelectionModel().getSelectedItem();
        if (product != null)
            Product.deleteProduct(product);
        else
            System.out.println("You need to select a product to delete!"); // TODO: Add a proper form alert for this.
    }

    // Back to Manager scene
    public void back_to_mngr_functions_scrn(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().changeParent("MainManager");
    }

    // Back to Home Screen
    public void back_to_home(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().changeParent("Home");
    }

}
