package medwards11.unitTests;

import medwards11.search.*;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import medwards11.auth.Auth;

import java.io.IOException;
import java.sql.SQLException;

public class Search {
    @Test
    public void test() {
        int i = 1;
        System.out.println("num= " + i);
    }
    @Ignore
    public void searchCountryTest() {
       Country state = new Country();
        String actual = null;
        try {
            actual = state.processGet("Michi");
            assertTrue(actual.contains("Michigan"));
//            System.out.println(actual);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Ignore
    public void searchStateTest(){
        State state = new State();
        String actual = null;
        try {
            actual = state.processGet("Michigan","Brian");
            assertTrue(actual.contains("Banks"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Ignore
    public void searchPoliticianTest(){
        Politician state = new Politician();
        String actual = null;
        try {
            actual = state.processGet("Michigan","Abortion", "Pro");
            assertTrue(actual.contains("Banks"));
            assertTrue(actual.contains("Pro"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
}
