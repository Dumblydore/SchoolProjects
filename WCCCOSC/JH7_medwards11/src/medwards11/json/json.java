package medwards11.json;

import org.json.simple.JSONObject;import java.lang.String;

/**
 * Created by Maurice on 12/8/2014.
 */
public class json {

    public static String SQLerror(Exception e){
        JSONObject object = new JSONObject();
        object.put("success",false);
        object.put("error",500);
        object.put("message",e.toString());
        return object.toString();
    }

    public static String missedParam(){
        JSONObject object = new JSONObject();
        object.put("success",false);
        object.put("error",500);
        object.put("message","Missing a Parameter!");
        return object.toString();
    }
}
