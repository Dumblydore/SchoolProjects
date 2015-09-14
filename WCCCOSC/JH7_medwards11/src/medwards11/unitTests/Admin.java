package medwards11.unitTests;

import medwards11.admin.*;
import medwards11.user.*;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;
public class Admin {

    @Test
    public void login(){
        Login login = new Login();
        boolean expected = true;
        try {
            boolean actual = login.processGet("admin","FavePokeJolt3");
            assertEquals(actual,expected);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginFail(){
        Login login = new Login();
        boolean expected = false;
        try {
            boolean actual = login.processGet("admin","FavePokeJolt");
            assertEquals(actual,expected);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /*@Ignore
    public void logout(){
        Add add = new Add();
        int expected = 1;
        int actual = 0;
        assertEquals(actual,expected);
    }

    @Ignore
    public void addTest(){
        Add add = new Add();
        int expected = 1;
        int actual = 0;
        assertEquals(actual,expected);
    }

    @Ignore
    public void deleteTest() {
        Delete add = new Delete();
        int expected = 1;
        int actual = 0;
        assertEquals(actual,expected);
    }

    @Ignore
    public void EditTest() {
        Edit edit = new Edit();
        int expected = 1;
        int actual = 0;
        assertEquals(actual,expected);
    }*/

}
