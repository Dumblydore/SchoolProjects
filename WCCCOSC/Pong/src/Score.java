import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Created by Maurice on 4/30/2014.
 */
public class Score extends Text {
    public Score(int x, int y){
        super();
        super.setX(x);
        super.setX(y);
    }
    public void updateScores(String s){
       super.setText(s);
    }
}
