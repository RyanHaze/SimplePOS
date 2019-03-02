package org.dgby.gatorpos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    @FXML private TableView<Product> product_table;
    @FXML private TableColumn<Product, Integer> prod_IdCol;
    @FXML private TableColumn<Product, String> prod_NameCol;
    @FXML private TableColumn<Product, Integer> prod_PriceCol;
    @FXML private TableColumn<Product, String> prod_CatCol;
    @FXML private TableColumn<Product, String> prod_DescriptionCol;

    @FXML private TextField prod_Name;
    @FXML private TextField prod_Price;
    @FXML private TextField prod_Cat;
    @FXML private TextField prod_Description;

    @FXML public void initialize()
    {
        prod_IdCol.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
        prod_NameCol.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        prod_PriceCol.setCellValueFactory(new PropertyValueFactory<Product,Integer>("price"));
        prod_CatCol.setCellValueFactory(new PropertyValueFactory<Product,String>("category"));
        prod_DescriptionCol.setCellValueFactory(new PropertyValueFactory<Product,String>("description"));

        Product.updateProducts();
        product_table.setItems(Product.getProducts());
    }

    public void addProduct()
    {
        String prod_name = this.prod_Name.getText();
        String prod_price = this.prod_Price.getText();
        String prod_cat = this.prod_Cat.getText();
        String prod_description = this.prod_Description.getText();

        //call the product
        if ((prod_name.length() > 0) && (prod_price.length() > 0) && (prod_cat.length() > 0) && (prod_description.length() > 0))
        {
            Product.addProduct(prod_name, Integer.valueOf(prod_price), prod_description, prod_cat);
        }



    }

    public void deleteProduct()
    {
        Product product = product_table.getSelectionModel().getSelectedItem();
        if (product != null)
            Product.deleteProduct(product);
        else
            System.out.println("You need to select an employee to delete!"); // TODO: Add a proper form alert for this.
    }


    public void back_to_mngr_functions_scrn(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().changeParent("MainManager");
    }

    public void back_to_home(ActionEvent event) throws IOException
    {
        SceneManager.getInstance().changeParent("Home");
    }

}
