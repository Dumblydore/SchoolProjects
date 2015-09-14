package medwards11.search;

import medwards11.auth.Auth;
import medwards11.database.DBConnection;
import medwards11.json.json;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Maurice on 12/5/2014.
 */
public class SearchPolitician extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        String topic = request.getParameter("topic");
        String query = request.getParameter("query");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            out.write(processGet(state,topic,query));
        } catch (SQLException e) {
            out.write(json.SQLerror());
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

        return obj.toString();
    }

}
