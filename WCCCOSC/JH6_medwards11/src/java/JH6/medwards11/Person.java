package JH6.medwards11;
////import java.sql.Connection;
////import java.sql.SQLException;
////import java.sql.Statement;
////import java.util.logging.Level;
////import java.util.logging.Logger;
////import javax.servlet.http.HttpServletRequest;
////
////public class Person {
////    private static String[] auth;
////   
////    static void addPerson(HttpServletRequest request) {
////       DBConnection.getConnection(auth);
////    }
////    
////    static void removePerson(HttpServletRequest request) {
//////                        int mode = Integer.valueOf(request.getAttribute("type").toString());
//////        switch(mode){
//////            case(1): //add
//////                break;
//////            case(0): //edit
//////                break;
//////            case(-1): //remove
//////                break;
//////        }
////    }
////    
////    static void editPerson(HttpServletRequest request) {
////        
////    }
////    
////    static void loadAuth(String usr, String pass) {
////        String[] auth1 = {"rest","tsd"};
////        auth = auth1;
////        
////    }
////    
////    private void createTable() {
////        try {
////            System.out.println("Creating table");
////            Connection conn = DBConnection.getConnection(auth);
////            String sql = "CREATE TABLE IF NOT EXISTS People\n" +
////                    "(\n" +
////                    "Id INT(11) NOT NULL AUTO_INCREMENT,\n" +
////                    "Name VARCHAR(255) NOT NULL,\n" +
////                    "EyeColor VARCHAR(255) NOT NULL,\n" +
////                    "HairColor VARCHAR(255) NOT NULL,\n" +
////                    "Height VARCHAR(255) NOT NULL,\n" +
////                    "Weight INT(11) NOT NULL,\n" +
////                    "PRIMARY KEY (ID)\n" +
////                    ");";
////            Statement statement = conn.createStatement();
////            statement.executeUpdate(sql);
////        } catch (SQLException ex) {
////            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
////        }
////    }
////}
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.medwards11.person;
//
///**
// *
// * @author Maurice
// */
public class Person {
    private String name;
    private String eyeColor;
    private String height;
    private String weight;
    
    public Person(String personName, String personEyeColor, String personHeight, String personWeight) {
        name = personName;
        eyeColor = personEyeColor;
        height = personHeight;
        weight = personWeight;
    }
    
    public String getName(){
        return name;
    }
    public String getEyeColor(){
        return eyeColor;
    }
    public String getHeight(){
        return height;
    }
    public String getWeight(){
        return weight;
    }
    
    public void setName(String personName){
        name = personName;
    }
    public void setEyeColor(String personEyeColor){
        eyeColor = personEyeColor;
    }
    public void setHeight(String personHeight){
        height = personHeight;
    }
    public void setWeight(String personWeight){
        weight = personWeight;
    }

}
