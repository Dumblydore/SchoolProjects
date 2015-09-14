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
public class CreateCities {
     public static void create(Connection connection) throws SQLException {
        String[] city = {"San Francisco", "San Pedro State", "Fresno", "Ann Arbor"};
        String[] states = {"California", "California", "California", "Michigan"};
        int[] population = {980000,120000,500000,140000};
        String sqlTemplate = "INSERT INTO CITIES VALUES('$city','$state',$pop)";
        String printTemp = "Name: $state | City: $city | Population: $pop";
        
        System.out.println("Creating table cities.");
        String sql = "CREATE TABLE CITIES"
                   + "(City varchar(20),"
                   + "State varchar(20),"
                   + "Population int);";
        
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE CITIES");
            statement.executeUpdate(sql);
            System.out.println("Table Created.");
            for(int i = 0; i < states.length; i++) {
                statement.executeUpdate(sqlTemplate.replace("$state", states[i])
                .replace("$city", city[i])
                .replace("$pop", Integer.toString(population[i])));
                System.out.println(states[i]+" -added to table");
            }
            System.out.println();
            ResultSet response = statement.executeQuery("select * from CITIES");
            while(response.next()){
                String state = response.getString("State");
                String cityName = response.getString("City");
                int pop = response.getInt("Population");
                System.out.println(printTemp.replace("$state", state)
                .replace("$city", cityName)
                .replace("$pop", Integer.toString(pop)));
            }

    }
}
