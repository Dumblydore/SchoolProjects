
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Maurice on 12/8/2014.
 */
public class Main {
    public static void main(String[] args) {
        String template = "UPDATE Michigan SET photo='$link' WHERE Region=$num;";
        try {
            Connection connection = DB.getConnection(args);
            Statement statement = connection.createStatement();
            for(int i = 1; i <= 110; i++)
                statement.executeUpdate(template.replace("$link",PhotoLinks.genLink(i)).replace("$num",Integer.toString(i)));
            } catch (SQLException e) {
            System.err.println("sql fucked up");
            e.printStackTrace();
        }
    }
}
