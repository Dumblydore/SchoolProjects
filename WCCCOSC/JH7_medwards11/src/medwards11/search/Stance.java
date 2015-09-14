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
 * Created by Maurice on 12/10/2014.
 */
public class Stance extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topic = request.getParameter("topic");
        String stance = request.getParameter("stance");
        String state = request.getParameter("state");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            out.write(processGet(topic,stance,state));
        } catch (SQLException e) {
            out.write(json.SQLerror(e));
        }
        out.close();
    }

    public String processGet(String topic, String stance, String state) throws SQLException, IOException {
        String sql = "SELECT PoliticianName FROM $state WHERE $topic='$stance'";
        Connection conn = DBConnection.getConnection(Auth.getAuth());
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql.replace("$stance", stance)
                .replace("$topic", topic)
                .replace("$state",state));
        JSONArray array = new JSONArray();
        while(resultSet.next()) {
            JSONObject obj = new JSONObject();
            obj.put("politicianName", resultSet.getString("PoliticianName"));
            array.add(obj);
        }
        conn.close();
        return array.toString();
    }
}
