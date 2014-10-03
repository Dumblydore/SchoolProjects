<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.net.URISyntaxException" %>
<%@ page import="java.util.Random" %>
<%--
  Created by IntelliJ IDEA.
  User: Maurice
  Date: 10/2/2014
  Time: 7:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList lines = new ArrayList();
    String fileLocation = application.getRealPath("/") + "WEB-INF/OneLiners.txt";
    try {
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileLocation)));
        String line;
        while((line = reader.readLine()) != null)
            lines.add(line);
        reader.close();
    } catch (IOException e) {
        System.err.println("OneLiners File not Found.");
    }
        %>



<html>
  <head>
    <title></title>
  </head>
  <body>
  <h1 class="Line"><%out.print(lines.get(new Random().nextInt(lines.size())).toString());%></h1>
  <div id="container">
      <form id='form' method="get">
          <label>Principal: $</label> <input type="text" name="principal" id="principal" value="10000"><br>
          <label>Rate: %</label> <input type="text" name="rate" id="rate" value="6"><br>
          <label>Months left:</label> <input type="text" name="months" id="months" value="60"><br>
          <label>Payment: $</label> <input type="text" name="payment" id="payment" value="1000"><br>
          <input type="submit" id="submit"></button>
      </form>
      <table border="1" id="mortgageTable">
          <%


              if(request.getParameter("rate") != null || request.getParameter("payment") != null || request.getParameter("principal") != null) {
                  %>
                  <tr>
                  <td>Months</td>
                  <td>Principal</td>
                  <td>Interest</td>
                  </tr>
          <%
                  double interest = Double.valueOf(request.getParameter("rate"));
                  double monthlyPayment = Double.valueOf(request.getParameter("payment"));
                  double principal = Double.valueOf(request.getParameter("principal"));
                  int months = Integer.valueOf(request.getParameter("months"));
                  double newPrincipal = principal;

                  for(int i=0; i <= months; i++){
                      double interestPaid = (newPrincipal * interest) / (12 * 100);
                      newPrincipal = newPrincipal + interestPaid - monthlyPayment;
                      if(newPrincipal < 0)
                          break;
                      %>
                      <tr>
                          <td><%=i+1%></td>
                          <td><%=String.format("%.2f", newPrincipal)%> </td>
                          <td><%=String.format("%.2f", interestPaid)%></td>
                      </tr>
                      <%
                  }
              }
          %>

      </table>
  </div>

  </body>
</html>
