package medwards11.user;

import medwards11.auth.Auth;
import medwards11.database.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Maurice on 12/8/2014.
 */
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Connection conn = DBConnection.getConnection(Auth.getAuth());
    }

    public boolean processGet(String user, String password) throws IOException, SQLException {
        boolean status = false;
        String template = "SELECT * FROM users WHERE UserName='$user' && Password='$pass';";
        Connection conn = DBConnection.getConnection(Auth.getAuth());
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(template.replace("$user",user)
                .replace("$pass",password));

        status = resultSet.next();
        return status;
    }
}
