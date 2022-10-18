package com.example.supplychainmanagement;

import java.sql.ResultSet;

public class Order {

    public static boolean orderProduct(int productId, String customermail){
        String query = String.format("INSERT INTO orders(quantity,customer_id,product_id) VALUES(1,(SELECT cid FROM customers WHERE email = '%s'),'%s')",customermail,productId);
        DatabaseConnection dbCon = new DatabaseConnection();
        if(dbCon.executeQuery(query)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Order.orderProduct(5,"mohd.althaf337@gmail.com");
    }
}
