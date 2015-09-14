<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
     // Routine to carefully convert a string to an Double

    double getDoubleCarefully(String s) {
        double d = -1;
        try {
            d = Double.parseDouble(s.trim());
        } catch (RuntimeException e) {
        }
        return d;
    }

    // Routine to carefully convert a string to an Integer
    int getIntegerCarefully(String s) {
        int ivalue = -1;
        try {
            ivalue = Integer.parseInt(s.trim());
        } catch (RuntimeException e) {
        }
        return ivalue;
    }
 %>

<%
    String strInterest = request.getParameter("interest");
    String strPrincipal = request.getParameter("principal");
    String strMonths = request.getParameter("months");
    String strPayment = request.getParameter("payment");

    double interest = getDoubleCarefully(strInterest);
    double principal = getDoubleCarefully(strPrincipal);
    double payment = getDoubleCarefully(strPayment);
    int months = getIntegerCarefully(strMonths);
    
    boolean goodParameters = true;
    String errorMsg="";
    if (principal < 0) {
        errorMsg += "<h3>Principal value is bad</h3>\n";
        goodParameters = false;
    }
    if (interest < 0){ 
        errorMsg += "<h3>Interest value is bad</h3>\n";
        goodParameters = false;
    }
    if (payment < 0){
        goodParameters = false;
        errorMsg += "<h3>Payment value is bad</h3>\n";
    }
    if (months < 0){ 
        goodParameters = false;
        errorMsg += "<h3>Months value is bad</h3>\n";
    }
    
    String tableGuts = null;
    if (goodParameters)
    {
        tableGuts="";
    }
    String lastPayment = "";
    
    double interest_this_month = 0;
    for (int i = 1; i <= months; i++) {
        interest_this_month = principal * interest / (12 * 100);
        principal = principal + interest_this_month - payment;
        if (principal > 0) {
            String sPrinciple = String.format("%.2f", principal);
            String sInterest = String.format("%.2f", interest_this_month);
            tableGuts += "<tr><td>" + i + "</td>"
                    + "<td>" + sPrinciple + "</td>"
                    + "<td>" + sInterest + "</td>"
                    + "</tr>\n";
        } else {
            break;
        }
    }
    if (principal < 0) {
        String sLastPayment = String.format("%.2f", payment + principal);
        String sLastInterest = String.format("%.2f", interest_this_month);
        lastPayment ="Last Payment = " + sLastPayment
                + "<br>This includes interest:" + sLastInterest;
    }


    
%>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interest Calculations: JSP</title>
    </head>
    <body>
       
        
        <h1>Interest Calculations</h1>   
        <h3>Calculating for:</h3>
        <h3>interest rate/year=  <%=strInterest%>   </h3>
        <h3>starting principal=  <%= strPrincipal%> </h3>
        <h3>payment/month  =     <%= strPayment%>   </h3>
        <h3>months   =           <%= strMonths%>    </h3>
        
        
        <%= errorMsg %>
        
        <%
            if (tableGuts != null)
            {
         %>
            <table border=4><tr>
                    <th>Month Number</th>
                    <th>Principal</th>
                    <th>Interest</th>
               </tr>
               <%=tableGuts %>
               
            </table>
            <%=lastPayment%>
        
        <%
            } // ending the if (tableGuts != null)
         %>
        
    </body>
</html>