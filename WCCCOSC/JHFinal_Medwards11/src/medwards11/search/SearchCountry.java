package medwards11.search;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medwards11.auth.Auth;
import medwards11.database.DBConnection;
import medwards11.json.json;
import org.json.simple.JSONObject;

public class SearchCountry extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String state = request.getParameter("state");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            out.write(processGet(state));
        } catch (SQLException e) {
            out.write(json.SQLerror());
        }
    }

    public String processGet(String state) throws IOException, SQLException {
        String template = "SELECT * FROM States WHERE State LIKE '%$string%';";
        Connection conn = DBConnection.getConnection(Auth.getAuth());
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(template.replace("$string",state));
        resultSet.first();
        JSONObject obj = new JSONObject();
        obj.put("State", resultSet.getString("State"));
        obj.put("PolPop", resultSet.getInt("PolPop"));
        obj.put("regions",resultSet.getInt("regions"));

        return obj.toString();
    }



}
