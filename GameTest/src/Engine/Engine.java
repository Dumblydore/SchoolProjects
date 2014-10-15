package Engine;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Engine {
    final int FRAMERATE = 60;
    private double currentMillis;
    private static List<GameObj> instances;
    private static List<Method> updateMethods;
    public Engine() {
        System.out.println("Initializing Engine...");
        instances = new ArrayList<GameObj>();
        updateMethods = new ArrayList<Method>();
    }

    public void update() {
       for(GameObj object : instances){
            object.update();
       }

    }

    public void loadObjects(ArrayList<GameObj> objects) {
        instances =  objects;
        System.out.println("Loading Objects");
        for(GameObj object : instances){
            object.init();
        }
    }
}
