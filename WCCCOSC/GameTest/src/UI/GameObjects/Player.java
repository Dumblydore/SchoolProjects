package UI.GameObjects;

/**
 * Created by Maurice on 10/11/2014.
 */
import Engine.GameObj;
import javafx.scene.shape.Circle;

public class Player extends Circle implements GameObj {
    public Player() {
        super();
    }
    @Override
    public void init() {
        super.setCenterX(400);
        super.setCenterY(300);
    }

    @Override
    public void update() {

    }

}
