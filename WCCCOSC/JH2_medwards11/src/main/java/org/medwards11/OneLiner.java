package org.medwards11;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import org.json.JSONObject;

public class OneLiner extends HttpServlet {
    ArrayList lines;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Recieved request!");
        JSONObject rrd = new JSONObject();
        rrd.put("Line",randLine());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(rrd.toString());
        out.flush();
    }

    @Override
    public void init() {
        lines = new ArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(getClass().getResource("/OneLiners.txt").toURI())));
            String line;
            while((line = reader.readLine()) != null)
                lines.add(line);
            reader.close();
        } catch (IOException e) {
            System.err.println("OneLiners File not Found.");
        } catch (URISyntaxException e) {

        }


    }

    public String randLine(){
        return lines.get(new Random().nextInt(lines.size())).toString();
    }

}
