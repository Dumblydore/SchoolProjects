package medwards11.search;

import medwards11.auth.Auth;
import medwards11.database.DBConnection;
import medwards11.json.json;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Maurice on 12/8/2014.
 */
public class Country extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            if (state != null)
                out.write(processGet(state));
            else
                out.write(processGet());
        } catch (SQLException e) {
            out.write(json.SQLerror(e));
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
        conn.close();
        return obj.toString();
    }

    public String processGet() throws IOException, SQLException {
        String template = "SELECT * FROM States;";
        Connection conn = DBConnection.getConnection(Auth.getAuth());
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(template);
        JSONArray array = new JSONArray();
        while(resultSet.next()) {
            JSONObject obj = new JSONObject();
            obj.put("State", resultSet.getString("State"));
            obj.put("PolPop", resultSet.getInt("PolPop"));
            obj.put("regions", resultSet.getInt("regions"));
            array.add(obj);
        }
        conn.close();
        return array.toString();
    }

}
