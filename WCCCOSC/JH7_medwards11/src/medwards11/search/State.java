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
public class State extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        String query = request.getParameter("query");
        String column = request.getParameter("column");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            if(column != null)
                out.write(processGet(column,state,query));
            else if(query != null && column == null)
                out.write(processGet(state,query));
            else
                out.write(processGet(state));
        } catch (SQLException e) {
            out.write(json.SQLerror(e));
        }
    }
    public String processGet(String stateName, String query) throws IOException, SQLException {
        String template = "SELECT * FROM $stateName WHERE PoliticianName LIKE '%$query%';";
        Connection conn = DBConnection.getConnection(Auth.getAuth());
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(template.replace("$query",query).replace("$stateName",stateName));
        resultSet.first();
        JSONObject obj = new JSONObject();
        obj.put("politicianName", resultSet.getString("PoliticianName"));
        obj.put("Email", resultSet.getString("Email"));
        obj.put("region",resultSet.getInt("Region"));
        conn.close();
        return obj.toString();
    }

    public String processGet(String column, String stateName, String query) throws IOException, SQLException {
        String template = "SELECT * FROM $stateName WHERE PoliticianName LIKE '%$query%';";
        Connection conn = DBConnection.getConnection(Auth.getAuth());
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(template.replace("$query",query).replace("$stateName",stateName));
        resultSet.first();
        JSONObject obj = new JSONObject();
        obj.put("politicianName", resultSet.getString("PoliticianName"));
        obj.put("contactInfo", resultSet.getString("ContactInfo"));
        obj.put("region",resultSet.getString("Region"));

        return obj.toString();
    }
    public String processGet(String state) throws IOException, SQLException {
        String template = "SELECT * FROM $stateName;";
        Connection conn = DBConnection.getConnection(Auth.getAuth());
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(template.replace("$stateName",state));
        JSONArray array = new JSONArray();
        while(resultSet.next()) {
            JSONObject obj = new JSONObject();
            obj.put("politicianName", resultSet.getString("PoliticianName"));
            obj.put("party", resultSet.getString("party"));
            obj.put("region", resultSet.getInt("Region"));
            array.add(obj);
        }
        conn.close();
        return array.toString();
    }
}
