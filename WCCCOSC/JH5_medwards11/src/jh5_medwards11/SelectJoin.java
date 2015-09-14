/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jh5_medwards11;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Maurice
 */
public class SelectJoin {
    public static void join(Connection connection) throws SQLException {
        String sql = "SELECT STATES.State, STATES.Region, STATES.City\n" 
                   + "FROM STATES\n"
                   + "INNER JOIN CITIES";
        String printTemp = "Name: $state | Region: $region | City: $city | Capital: $cap | Population: $pop";
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
            System.out.println("Joining tables\n");
            ResultSet response = statement.executeQuery("select * from STATES");
            while(response.next()){
                String state = response.getString("State");
                String region = response.getString("Region");
                String city = response.getString("City");
                String capital = response.getString("Capital");
                int pop = response.getInt("Population");
                System.out.println(printTemp.replace("$state", state)
                .replace("$region", region)
                .replace("$cap", capital)
                .replace("$city", city)
                .replace("$pop", Integer.toString(pop)));
            }
    }
 }
