package com.task.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection getDatabaseConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","root");
        }catch (Exception e){
            e.printStackTrace();
        }
        return  connection;
    }
}
