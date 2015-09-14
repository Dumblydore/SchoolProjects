package medwards11.search;

import medwards11.auth.Auth;
import medwards11.database.DBConnection;
import medwards11.json.json;
import medwards11.politician.Stance;
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
public class Politician extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        String topic = request.getParameter("topic");
        String query = request.getParameter("query");
        String politician = request.getParameter("politician");
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        try {
            if(politician == null)
                out.write(processGet(state,topic,query));
            else
                out.write(processGet(state,politician));
        } catch (SQLException e) {
            out.write(json.SQLerror(e));
        }
    }

    public String processGet(String state, String topic, String query) throws IOException, SQLException {
        String template = "SELECT * FROM $state WHERE $topic LIKE '%$stance%';";
        Connection conn = DBConnection.getConnection(Auth.getAuth());
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(template.replace("$stance",query)
                .replace("$state",state)
                .replace("$topic",topic));
        resultSet.first();
        JSONObject obj = new JSONObject();
        obj.put("politicianName", resultSet.getString("PoliticianName"));
        obj.put("stance", resultSet.getString(topic));
        conn.close();
        return obj.toString();
    }

    public String processGet(String state, String politician) throws IOException, SQLException {
        String template = "SELECT * FROM $state WHERE politicianName='$politician'";
        Connection conn = DBConnection.getConnection(Auth.getAuth());
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(template
                .replace("$state",state)
                .replace("$politician",politician));
        JSONArray array = new JSONArray();

        while(resultSet.next()) {
            JSONObject obj = new JSONObject();
            obj.put("politicianName", resultSet.getString("PoliticianName"));
            obj.put("politicianPhoto",resultSet.getString("photo"));
            for(String stance : Stance.getStances())
                obj.put(stance, resultSet.getString(stance));
            array.add(obj);
        }
        conn.close();
        return array.toString();
    }


}
