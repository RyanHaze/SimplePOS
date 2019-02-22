package org.dgby.gatorpos.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.*;

import org.dgby.gatorpos.ConnectionManager;

public class Product {
    private IntegerProperty id;
    private StringProperty name;
    private IntegerProperty price;
    private StringProperty description;
    private StringProperty catagory;

    private static Map<String, ObservableList<Product>> productList = new HashMap<>();

    public Product(Integer id, String name, Integer price, String description, String catagory) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
        this.description = new SimpleStringProperty(description);
        this.catagory = new SimpleStringProperty(catagory);
    }

    public static void updateProducts() {
        for (ObservableList<Product> list : productList.values())
            list.clear();

        try {
            ConnectionManager.createTable("Products",
                    new String[] { "name TEXT", "price INTEGER", "description TEXT", "catagory TEXT" });

            ConnectionManager.executeQuery("SELECT rowid AS id,* FROM Products", resultSet -> {
                while (resultSet.next()) {
                    String catagory = resultSet.getString("catagory");

                    if (!productList.containsKey(catagory))
                        productList.put(catagory, FXCollections.observableArrayList());

                    productList.get(catagory).add(new Product(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getInt("price"), resultSet.getString("description"), catagory));
                }
            });
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    public static Set<String> getCatagories() {
        return productList.keySet();
    }

    public static ObservableList<Product> getProductsByCatagory(String catagory) {
        if (productList.containsKey(catagory))
            return productList.get(catagory);
        return FXCollections.observableArrayList();
    }

    public static void addProduct(String name, Integer price, String description, String catagory) {
        try {
            Integer id = ConnectionManager.insertRow("Products",
                    new String[] { "name", "price", "description", "catagory" },
                    new Object[] { name, price, description, price });

            ConnectionManager.executeQuery("SELECT rowid AS id,* FROM Products WHERE rowid = " + id, resultSet -> {
                while (resultSet.next()) {
                    String _catagory = resultSet.getString("catagory");

                    if (!productList.containsKey(_catagory))
                        productList.put(_catagory, FXCollections.observableArrayList());

                    productList.get(_catagory).add(new Product(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getInt("price"), resultSet.getString("description"), _catagory));
                }
            });
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    public static void deleteProduct(Product product) {
        try {
            productList.get(product.getCatagory()).remove(product);
            ConnectionManager.executeUpdate("DELETE FROM Products WHERE rowid = " + product.getId());
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
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the price
     */
    public Integer getPrice() {
        return price.get();
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Integer price) {
        this.price.set(price);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description.set(description);
    }

    /**
     * @return the catagory
     */
    public String getCatagory() {
        return catagory.get();
    }

    /**
     * @param catagory the catagory to set
     */
    public void setCatagory(String catagory) {
        this.catagory.set(catagory);
    }

}