package com.task.read;


import com.task.database.service.DataInsert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {
    public void readFileData(){
        try{
            DataInsert dit = new DataInsert();
             //String fileName = "demo_task.txt";
            String fileName = "1M-customers.txt";
             Set<String> customerSet = new HashSet<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null && !line .equals("")){
                String temp [] = line.split(",");
                String concatValue = "";
                concatValue = getFileLineData(temp);
                //check duplicate data
                if(!(customerSet.contains(concatValue))){
                    customerSet.add(concatValue);
                    //validate mobile & email
                    if(isMobileNumberValid(temp[5]) && isEmailValid(temp[6])){
                        // insert valid data
                        dit.insertValidData(temp);
                    }else {
                        // insert invalid
                        dit.insertInvalidData(temp);
                    }

                }
                line = reader.readLine();
            }
            System.out.println("Set size....."+customerSet.size());

            reader.close();
        }catch(FileNotFoundException ef){
            ef.printStackTrace();
        }catch (IOException ei){
            ei.printStackTrace();
        }

    }


    public  String getFileLineData(String temp []){
        String concatValue = "";
        String firstName = temp[0];
        String lastName = temp[1];
        String city = temp[2];
        String state = temp[3];
        String postCode = temp[4];
        String mobileNumber = temp[5];
        String email = temp[6];
        String ip = "";
        if(temp.length > 7) {
             ip = temp[7];
        }
        concatValue = firstName+lastName+city+state+postCode+mobileNumber+email+ip;
        return  concatValue;
    }

    public boolean isMobileNumberValid(String mobileNumber){
        boolean numberValid = false;
        String patfirst = "^(\\d{3}[- ]?){2}\\d{4}$";
        if(Pattern.compile(patfirst).matcher(mobileNumber).matches()){
            numberValid = true;
            return  numberValid;
        }

         String patsecond =  "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[ ]?\\d{3}[- .]?\\d{4}$";
        if(Pattern.compile(patsecond).matcher(mobileNumber).matches()){
            numberValid = true;
            return  numberValid;
        }

        String patthird =  "\\d{1} \\d{3} \\d{3} \\d{4}";
        if(Pattern.compile(patthird).matcher(mobileNumber).matches()){
            numberValid = true;
            return  numberValid;
        }
        return numberValid;
    }

 public boolean isEmailValid(String email){
        boolean emailValid = false;
        String emailReg = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailReg);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            emailValid = true;
        }
        return emailValid;
 }
}
