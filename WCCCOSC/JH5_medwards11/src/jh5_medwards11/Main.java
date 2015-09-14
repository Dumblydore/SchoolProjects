/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//http://screencast.com/t/meKUpWIQszM
package jh5_medwards11;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maurice
 */
public class Main { 
    public static void main(String[] args) {
        
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = MyConnection.getConnection(args);
            System.out.println("Type start to continue");
            scanner.next();
            CreateStates.create(connection);
            System.out.println("Type anything to continue");
            scanner.next();
            CreateCities.create(connection);
            System.out.println("Type anything to join the tables");
            scanner.next();
            SelectJoin.join(connection);
            System.out.println("Type anything to delete and update the joined table");
            scanner.next();
            DeleteUpdate.update(connection);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
