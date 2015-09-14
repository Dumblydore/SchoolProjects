package org.medwards;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONArray;
import org.json.JSONObject;

public class MortgageCalculator extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double interest = Double.valueOf(request.getParameter("rate"));
        double monthlyPayment = Double.valueOf(request.getParameter("payment"));
        double principal = Double.valueOf(request.getParameter("principal"));
        int months = Integer.valueOf(request.getParameter("months"));
        double newPrincipal = principal;
            JSONArray monthArray = new JSONArray();
            JSONArray principalArray = new JSONArray();
            JSONArray interestArray = new JSONArray();

            JSONObject jsonObject = new JSONObject();
            for(int i=0; i <= months; i++){
                double interestPaid = (newPrincipal * interest) / (12 * 100);
                newPrincipal = newPrincipal + interestPaid - monthlyPayment;
                if(newPrincipal < 0)
                    break;
                monthArray.put(i+1);
                principalArray.put(String.format("%.2f", newPrincipal));
                interestArray.put(String.format("%.2f", interestPaid));
            }
        jsonObject.put("months", monthArray);
        jsonObject.put("principal", principalArray);
        jsonObject.put("interest", interestArray);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
    }

}
