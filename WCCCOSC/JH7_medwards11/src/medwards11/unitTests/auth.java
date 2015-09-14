package medwards11.unitTests;

import org.junit.Test;
import static org.junit.Assert.*;
import medwards11.auth.Auth;

import java.io.IOException;

public class auth {

    @Test
    public void testAuth() {
        try {
            String[] expected = {"medwards11","ER6bdK22Uaqg"};
            String[] actual = Auth.getAuth();
            assertEquals(expected[0], actual[0]);
            assertEquals(expected[1], actual[1]);
        } catch(IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}
