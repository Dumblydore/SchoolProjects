import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Created by Maurice on 4/30/2014.
 */
public class Paddle extends Rectangle {
    private static final Duration PROBE = Duration.millis(1);
    private Timeline timeline;
    private int player;
    private String playerID;

    public Paddle(Color c, int player) {
        super(Global.width / 30, Global.height / 4, c);
        super.setY(150);
        Global.paddleInfo.add(super.getY());
        switch (player) {
            case 1:
                super.setX(60 - (super.getWidth()));
                break;
            case 2:
                super.setX(Global.width - 60);
                break;
            case 3:
                super.setWidth(Global.width / 4);
                super.setHeight(Global.width / 20);
                super.setY(60 - super.getHeight());
                super.setX(Global.width / 2);
                break;
            case 4:
                super.setWidth(Global.width / 4);
                super.setHeight(Global.width / 20);
                super.setY(Global.height - (60));
                super.setX(Global.width / 2);
                break;
            default:
        }
        this.player = player;
    }

    public void movePaddle(double y, double x) {
        if (this.player > 2) {
            super.setX(x);
        } else {
            super.setY(y);
        }
    }

    public int getPlayerNum() {
        return this.player;
    }

    public Double getPaddleLocation() {
        return super.getY();
    }

    public void setPaddleLocation(Double loc) {
        super.setY(loc);
    }
    public Timeline createTimeline(){
        return new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                try{
                                setPaddleLocation(Global.paddleInfo.poll());}
                                catch(NullPointerException e){
                                    //to not spam the console :p
                                }
                                //System.out.println(Global.paddleInfo.poll());
                            }
                        }
                ),
                new KeyFrame(
                        PROBE
                )
        );
    }
    public void syncPlayer(){
        timeline = createTimeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void setPlayerID(String id){
        this.playerID = id;
    }
    public void setPlayerColor(Color c){
        super.setFill(c);
    }
    public String getPlayerID(){
        return this.playerID;
    }

}
