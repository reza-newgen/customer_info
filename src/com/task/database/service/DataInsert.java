package com.task.database.service;

import com.task.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataInsert {
    DatabaseConnection dbConnection = new DatabaseConnection();
    Connection con = null;
    Statement stmt= null;
    public synchronized void  insertValidData(String temp []){
        try{
            con = dbConnection.getDatabaseConnection();
            stmt = con.createStatement();
            String ip = "";
            if(temp.length > 7){
                ip = temp[7];
            }
            String query = "INSERT INTO customer_valid(first_name,last_name,city,state,post_code,mobile_number,email,ip) values('"+temp[0]+"','"+temp[1]+"','"+temp[2]+"','"+temp[3]+"','"+temp[4]+"','"+temp[5]+"','"+temp[6]+"','"+ip+"')";
            stmt.executeUpdate(query);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null)
                    con.close();
            } catch (SQLException se) {}
            try {
                if (con != null)
                    con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public synchronized void insertInvalidData(String temp []){
        try{
            con = dbConnection.getDatabaseConnection();
            stmt = con.createStatement();
            String ip = "";
            if(temp.length > 7){
                ip = temp[7];
            }
            String query = "INSERT INTO customer_invalid(first_name,last_name,city,state,post_code,mobile_number,email,ip) values('"+temp[0]+"','"+temp[1]+"','"+temp[2]+"','"+temp[3]+"','"+temp[4]+"','"+temp[5]+"','"+temp[6]+"','"+ip+"')";
            stmt.executeUpdate(query);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null)
                    con.close();
            } catch (SQLException se) {}
            try {
                if (con != null)
                    con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
