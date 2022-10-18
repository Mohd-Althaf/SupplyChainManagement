package com.example.supplychainmanagement;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {

    public SimpleIntegerProperty id;
    public SimpleDoubleProperty price;
    public SimpleStringProperty name;
    public SimpleIntegerProperty quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id =new SimpleIntegerProperty(id);
        this.price =new SimpleDoubleProperty(price);
        this.name =new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public int getId() {
        return id.get();
    }

    public double getPrice() {
        return price.get();
    }

    public String getName() {
        return name.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public static ObservableList<Product> getAllProducts(){
        String selectProduct = "SELECT * FROM product";
        return getProductList(selectProduct);
    }

    public static ObservableList<Product> getProductsByname(String productName){

        String selectProduct = String.format("SELECT * FROM product WHERE name like '%%%s%%'", productName.toLowerCase());
        if(productName.length()!=0)
        return getProductList(selectProduct);
        ObservableList<Product> list = FXCollections.observableArrayList();
        list.add(new Product(0,"Please Enter in searchBox",0,0));
        return list;
    }
    private static ObservableList<Product> getProductList(String query){
        DatabaseConnection dbCon = new DatabaseConnection();
        ObservableList<Product> data = FXCollections.observableArrayList();

        try{
            ResultSet rs = dbCon.getQueryTable(query);
            while(rs.next()){
                data.add(new Product(rs.getInt(1),rs.getString(2),
                        rs.getDouble(3),rs.getInt(4)));
            }
            rs.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return data;
    }

}
