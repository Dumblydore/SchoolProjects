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
public class CreateStates {
   public static void create(Connection connection) throws SQLException {
        String[] states = {"California", "Michigan", "Texas", "Ohio"};
        String[] regions = {"West", "Midwest", "Southwest", "Midwest"};
        String[] largestCity = {"Los Angeles", "Detroit", "Dallas", "Cleveland"};
        String[] capitals = {"Sacramento", "Lansing", "Austin", "Columbus"};
        int[] population = {20000000,8000000,10000000,8500000};
        String sqlTemplate = "INSERT INTO STATES VALUES('$state','$region','$city','$capital',$pop)";
        String printTemp = "Name: $state | Region: $region | City: $city | Capital: $cap | Population: $pop";
        
        System.out.println("Creating table states.");
        String sql = "CREATE TABLE STATES"
                   + "(State varchar(20),"
                   + "Region varchar(20),"
                   + "City varchar(20),"
                   + "Capital varchar(20),"
                   + "Population int);";
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE STATES");
            statement.executeUpdate(sql);
            System.out.println("Table Created.");
            for(int i = 0; i < states.length; i++) {
                statement.executeUpdate(sqlTemplate.replace("$state", states[i])
                .replace("$region", regions[i])
                .replace("$city", largestCity[i])
                .replace("$capital", capitals[i])
                .replace("$pop", Integer.toString(population[i])));
                System.out.println(states[i]+" -added to table");
            }
            System.out.println();
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
