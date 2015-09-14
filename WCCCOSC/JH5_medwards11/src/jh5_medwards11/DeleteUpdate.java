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
public class DeleteUpdate {
     public static void update(Connection connection) throws SQLException {
        SelectJoin.join(connection);
        String sql1 = "SELECT * FROM STATES";
        String sql2 = "DELETE FROM STATES WHERE City='$city'";
        String printTemp = "Name: $state | City: $city | Population: $pop";
        Statement statement = connection.createStatement();
        ResultSet first = statement.executeQuery(sql1);
        first.first();
        String firstCity = first.getString("City");
        statement.executeUpdate(sql2.replace("$city", firstCity));
          ResultSet response = statement.executeQuery("select * from STATES");
          System.out.println("*********************************");
          System.out.println("Deleted " + firstCity);
          System.out.println("*********************************");
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
