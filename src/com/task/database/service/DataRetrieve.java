package com.task.database.service;

import com.task.connection.DatabaseConnection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataRetrieve {
    DatabaseConnection dbConnection = new DatabaseConnection();
    Connection con = null;
    Statement stmt= null;

    public void getDataFromValidCustomer(){
        try{
            List<String> dataList = new ArrayList<>();
            con = dbConnection.getDatabaseConnection();
            stmt = con.createStatement();
            String query = "select * from customer_valid";
           ResultSet rs = stmt.executeQuery(query);

           while (rs.next()){
               String first_name = rs.getString("first_name");
               String last_name = rs.getString("last_name");
               String city = rs.getString("city");
               String state = rs.getString("state");
               String post_code = rs.getString("post_code");
               String mobile_number = rs.getString("mobile_number");
               String email = rs.getString("email");
               String ip = rs.getString("ip");
               dataList.add(first_name+","+last_name+","+city+","+state+","+post_code+","+mobile_number+","+email+","+ip);
           }
           rs.close();
           stmt.close();

                   writeValidCustomerFile(dataList, "valid-customer.txt");

        }catch (Exception e){
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

    private static void writeValidCustomerFile(List<String> list, String path) {
        BufferedWriter out = null;
        try {
            File file = new File(path);
            out = new BufferedWriter(new FileWriter(file, true));
            for (String s : list) {
                out.write(s);
                out.newLine();

            }
            out.close();
        } catch (IOException e) {
        }
    }


    public void getDataFromInvalidCustomer(){
        try{
            List<String> data = new ArrayList<>();
            con = dbConnection.getDatabaseConnection();
            stmt = con.createStatement();
            String query = "select * from customer_invalid";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String post_code = rs.getString("post_code");
                String mobile_number = rs.getString("mobile_number");
                String email = rs.getString("email");
                String ip = rs.getString("ip");
                data.add(first_name+","+last_name+","+city+","+state+","+post_code+","+mobile_number+","+email+","+ip);
            }
            rs.close();
            stmt.close();
            writeInvalidCustomerFile(data,"invalid-customer.txt");
        }catch (Exception e){
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

    private static void writeInvalidCustomerFile(List<String> list, String path) {
        BufferedWriter out = null;
        try {
            File file = new File(path);
            out = new BufferedWriter(new FileWriter(file, true));
            for (String s : list) {
                out.write(s);
                out.newLine();

            }
            out.close();
        } catch (IOException e) {
        }
    }
}
