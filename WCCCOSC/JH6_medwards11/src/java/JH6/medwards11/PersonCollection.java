/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JH6.medwards11;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Maurice
 */
public class PersonCollection extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }
    
    @Override
    public void init() {
        System.out.println("Building list!");
        buildPeopleList();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        System.out.println("Get Recieved. \n Link: " + request.getParameter("personName"));
        
       try {
        if(request.getParameter("personButton").toString().equals("Add")) {
            addPerson(new Person(request.getParameter("personName"),
                    request.getParameter("personEye"),
            request.getParameter("personHeight"),
            request.getParameter("personWeight").toString()));
        } else if(request.getParameter("personButton").equals("Edit")) {
            editPerson(new Person(request.getParameter("personName"),
                    request.getParameter("personEye"),
            request.getParameter("personHeight"),
            request.getParameter("personWeight").toString()));
        } else if(request.getParameter("personButton").equals("Remove")) {
            removePerson(new Person(request.getParameter("personName"),
                    request.getParameter("personEye"),
            request.getParameter("personHeight"),
            request.getParameter("personWeight").toString()));
        } else if (request.getParameter("personButton").equals("Clear")) {
            clear();
        }
       } catch(NullPointerException e) {
//             Person person = new Person("Jim","Brown","5'11","130");
//             session.setAttribute("person",person);       
                }
        session.setAttribute("people", getPeopleList().toArray());
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/PersonCollection2.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void buildPeopleList(){
        try {
            String sql = "CREATE TABLE IF NOT EXISTS People\n" +
                    "(\n" +
                    "Id INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "Name VARCHAR(255) NOT NULL,\n" +
                    "EyeColor VARCHAR(255) NOT NULL,\n" +
                    "Height VARCHAR(255) NOT NULL,\n" +
                    "Weight INT(11) NOT NULL,\n" +
                    "PRIMARY KEY (ID)\n" +
                    ");";
            Connection connection = DBConnection.getConnection(getAuth());
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PersonCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList<Person> getPeopleList() {
            ArrayList people = new ArrayList();
        try {
            String sql = "SELECT * FROM People";
            Connection connection = DBConnection.getConnection(getAuth());
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
           
          
            while(results.next()) {
                people.add(new Person(results.getString("Name"),results.getString("EyeColor"),results.getString("Height"),Integer.toString(results.getInt("Weight"))));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return people;
    }
    
    private String[] getAuth(){
        try {
            Properties prop = new Properties();
            prop.load(PersonCollection.class.getResourceAsStream("userInfo.properties"));
            String[] auth = {"medwards11","ER6bdK22Uaqg"};
            return auth;
        } catch (IOException ex) {
            Logger.getLogger(PersonCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void addPerson(Person person) {
        System.out.println("Person added!");
        try {
            Connection connection = DBConnection.getConnection(getAuth());
            Statement statement = connection.createStatement();
            String name = person.getName();
            String eye = person.getEyeColor();
            String height = person.getHeight();
            String weight = person.getWeight();;

            String sql = "INSERT INTO People (Name, EyeColor, Height, Weight)\n"
                    + "VALUES ('$name', '$eye', '$height', $weight);";
            statement.executeUpdate(sql.replace("$name", name).
                    replace("$eye", eye).
                    replace("$height", height).
                    replace("$weight", weight));
        } catch (SQLException ex) {
            Logger.getLogger(PersonCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    private void editPerson(Person person) {
        System.out.println("Person edited!");
        try {
            Connection connection = DBConnection.getConnection(getAuth());
            Statement statement = connection.createStatement();
            String name = person.getName();
            String eye = person.getEyeColor();
            String height = person.getHeight();
            String weight = person.getWeight();

            String sql1 = "SELECT Id FROM People\n" +
                          "WHERE Name='$name';";
            
            String sql2 = "UPDATE People\n" +
                         "SET Name='$name', EyeColor='$eye', Height='$height', Weight='$weight'\n" +
                         "WHERE Id=$id;";
            
                    ResultSet rs = (statement.executeQuery(sql1.replace("$name", name)));
                    rs.next();
                    int id = rs.getInt(1);
                    statement.executeUpdate(sql2.replace("$name", name).
                    replace("$eye", eye).
                    replace("$height", height).
                    replace("$weight", weight).
                    replace("$id",Integer.toString(id)));
                    
        } catch (SQLException ex) {
            Logger.getLogger(PersonCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    private void removePerson(Person person) {
        System.out.println("Person removed!");
        try {
            Connection connection = DBConnection.getConnection(getAuth());
            Statement statement = connection.createStatement();
            String name = person.getName();

            String sql1 = "SELECT Id FROM People\n" +
                          "WHERE Name='$name';";
            
            String sql2 = "DELETE FROM People\n" +
                          "WHERE Id=$id;";
            
            ResultSet rs = (statement.executeQuery(sql1.replace("$name", name)));
            rs.next();
            int id = rs.getInt(1);
                    
            statement.executeUpdate(sql2.replace("$name", name).
                    replace("$id",Integer.toString(id)));
                    
        } catch (SQLException ex) {
            Logger.getLogger(PersonCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void clear() {
        System.out.println("All Entries Deleted!");
        try {
            Connection connection = DBConnection.getConnection(getAuth());
            Statement statement = connection.createStatement();

            String sql = "DELETE FROM People";
            statement.executeUpdate(sql);
                    
        } catch (SQLException ex) {
            Logger.getLogger(PersonCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
}
