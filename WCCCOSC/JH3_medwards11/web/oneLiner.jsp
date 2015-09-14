<%-- 
    Document   : oneLiner
    Created on : Aug 27, 2013, 2:59:32 PM
    Author     : chasselb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@page import= "java.util.*,java.io.*" %>
<%!
    ArrayList<String> oneLiners = new ArrayList<String>();
    int nextOneLiner=0;
    
    // Overriding the jspInit routine in the JSP Servlet class
// Note that this code is mostly just a copy of the init() code in the Servlet version
  public void jspInit()
  {  
      
        ServletConfig sc = getServletConfig();
        ServletContext sctx = getServletContext();
        
       String oneLinerFilename = sc.getInitParameter("oneLinerFilename");
        // The following is a simpler way to go
       //String oneLinerFilename = sctx.getInitParameter("oneLinerFilename");
        
        String fullPath = sctx.getRealPath("/WEB-INF/"+oneLinerFilename);
        System.out.println("fullPath="+ fullPath);
        try
        {
            File f = new File(fullPath);
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine())
            {
                String s= scan.nextLine().trim();
                if (s.length() > 0)
                    oneLiners.add(s);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error in init(): "+e);
        }
  }
 %>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>One Liner</title>
    </head>
    <body>
        
         <h3>
             <%=oneLiners.get(   (nextOneLiner++  )    % oneLiners.size()) %>
         </h3>
            
        <form action="oneLiner.jsp">
            <input type="submit" value="Next" >
        </form>
        
        
        
    </body>
</html>
