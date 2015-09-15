package Medwards11_JH7.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Maurice on 11/29/2014.
 */
public class DBConnection {
    public static Connection getConnection(String args[]) {

        Connection con=null;
        String urlStr=null;

        if (args.length < 2 )
        {
            System.out.println("You need to enter:");
            System.out.println("         arg[0] = Userid, arg[1] = password");
            return con;
        }

        try {
            //Load the Driver Class Now
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            urlStr =   "jdbc:mysql://russet.wccnet.edu/" + args[0] +
                    "?user="+args[0]+ "&password="+args[1];
            System.out.println("Connecting to : " + urlStr);
            con = DriverManager.getConnection(urlStr);

        }
        catch(SQLException ex) {
            System.err.println("SQLException("+urlStr+"): " + ex);
        }
        catch (Exception ex)
        {
            System.err.println("Exception("+urlStr+"): " + ex);
        }

        return con;
    }
}