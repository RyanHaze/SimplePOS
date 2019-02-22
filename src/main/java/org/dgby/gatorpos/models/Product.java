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
    private StringProperty category;

    private static ObservableList<Product> productList = FXCollections.observableArrayList();
    private static Map<String, ObservableList<Product>> categoryList = new HashMap<>();

    public Product(Integer id, String name, Integer price, String description, String category) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
        this.description = new SimpleStringProperty(description);
        this.category = new SimpleStringProperty(category);
    }

    private static void _addProduct(Product product) {
        productList.add(product);
        if (!categoryList.containsKey(product.getCategory()))
            categoryList.put(product.getCategory(), FXCollections.observableArrayList());
        categoryList.get(product.getCategory()).add(product);
    }

    public static void updateProducts() {
        productList.clear();
        for (ObservableList<Product> list : categoryList.values())
            list.clear();

        try {
            ConnectionManager.createTable("Products",
                    new String[] { "name TEXT", "price INTEGER", "description TEXT", "category TEXT" });

            ConnectionManager.executeQuery("SELECT rowid AS id,* FROM Products", resultSet -> {
                while (resultSet.next()) {
                    _addProduct(
                            new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("price"),
                                    resultSet.getString("description"), resultSet.getString("category")));
                }
            });
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    public static Set<String> getCategories() {
        return categoryList.keySet();
    }

    public static ObservableList<Product> getProducts() {
        return productList;
    }

    public static ObservableList<Product> getProductsBycategory(String category) {
        if (categoryList.containsKey(category))
            return categoryList.get(category);
        return FXCollections.observableArrayList();
    }

    public static void addProduct(String name, Integer price, String description, String category) {
        try {
            Integer id = ConnectionManager.insertRow("Products",
                    new String[] { "name", "price", "description", "category" },
                    new Object[] { name, price, description, category });

            ConnectionManager.executeQuery("SELECT rowid AS id,* FROM Products WHERE rowid = " + id, resultSet -> {
                while (resultSet.next()) {
                    _addProduct(
                            new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("price"),
                                    resultSet.getString("description"), resultSet.getString("category")));
                }
            });
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    public static void deleteProduct(Product product) {
        try {
            productList.remove(product);
            categoryList.get(product.getCategory()).remove(product);
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
     * @return the category
     */
    public String getCategory() {
        return category.get();
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category.set(category);
    }

}