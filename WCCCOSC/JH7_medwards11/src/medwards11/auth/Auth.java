package medwards11.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Maurice on 12/5/2014.
 */
public class Auth  {
    public static String[] getAuth() throws IOException {
        Properties properties = new Properties();
        String configFile = "medwards11/conf/credentials.properties";
        InputStream in = Auth.class.getClassLoader().getResourceAsStream(configFile);
        properties.load(in);
        String[] credentials = {properties.getProperty("usr"),properties.getProperty("pw")};
        return credentials;
    }
}
