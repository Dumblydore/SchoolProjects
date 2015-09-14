import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Maurice on 4/30/2014.
 */
public class Ball extends Rectangle {
    private static final Duration PROBE = Duration.millis(1);
    private static final Duration REFRESH = Duration.millis(24);
    private Timeline timeline;
    private ArrayList<Paddle> players;
    private int hitNum;
    private Text score;
   private double dx = 0, dy = 0;
    public Ball() {
        super(25, 25, Color.BLACK);
        super.setX(Global.width / 2);
        super.setY(Global.height / 2);
    }

    private char checkBoundsCollision() {
        if (0 >= super.getX()) {
            return 'l';
        } else if ((Global.width) <= super.getX()) {
            return 'r';
        } else if (0 >= super.getY()) {
            return 'u';
        } else if ((Global.height) <= super.getY()) {
            return 'd';
        } return 's';
    }

    private void checkPaddleCollision() {
        for (Paddle pad : this.players) {
            if (pad.getBoundsInParent().intersects(super.getBoundsInParent())) {
                System.out.println("collision detected");
                hitNum++;
                dx *=-1;
            }
        }
    }

    public void controlBall(ServerClass host) { //for testing
        super.setOnMouseDragged((MouseEvent e) -> {
            super.setX(e.getX());
            super.setY(e.getY());
            checkBoundsCollision();
            checkPaddleCollision();

        });
    }

    public String getBallLocation() {
        String loc = super.getX() + " " + super.getY();
        return loc;
    }

    public void setBallLocation(String loc) {
        String[] x = loc.split(" ");
        super.setX(Double.valueOf(x[0]));
        super.setY(Double.valueOf(x[1]));
    }

    public void setBallLocation(double[] loc) {
        super.setX(loc[0]);
        super.setY(loc[1]);
    }

    public Timeline createTimeline() {
        return new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                try {
                                    setBallLocation(Global.ballInfo.poll());
                                } catch (NullPointerException e) {
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

    public void ballPhysics(ServerClass host) {
        System.out.println("test");


        this.timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                char coll = checkBoundsCollision();
                                switch(coll){
                                    case 'l':
                                        p1Scored();
                                        reset();
                                        break;
                                    case'r':
                                        p2Scored();
                                        reset();
                                        break;
                                    case'u':
                                        dy*=-1;
                                        break;
                                    case'd':
                                        dy*=-1;
                                        break;
                                }
                                updateBall(dx,dy);
                                checkPaddleCollision();
                                host.sendData(getBallLocation());
                            }
                        }
                ),
                new KeyFrame(REFRESH)
        );
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    public void updateBall(double x, double y) {
        setBallLocation(new double[]{super.getX() + x, super.getY() + y});
    }

    public void syncBall() {
        System.out.println("test");
        this.timeline = createTimeline();
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    public void setPaddles(ArrayList<Paddle> p) {
        this.players = p;
    }
    private void reset(){
        super.setX(Global.width / 2);
        super.setY(Global.height / 2);
        dx = 5; dy = 5;
    }
    public int ranGen(int max , int min) {
        Random rand = new Random();
        int ii = (rand.nextInt(max*2)-max);
        return ii;
    }
    public void StartBall(){
        dx = 5;
        dy = -5;
    }
    public void endBall(){
        dx = 0;
        dy = 0;
    }
    public void p1Scored(){
        Global.player1Score++;
        System.out.println("Player 1 Scored!");
        System.out.println("Player1 - "+Global.player1Score+"|Player2 - "+Global.player2Score);
        if(Global.player1Score == 10){
            System.out.println("Player 1 Wins!");
            endBall();
        }
    }
    public void p2Scored(){
        Global.player2Score++;
        System.out.println("Player 2 Scored!");
        System.out.println("Player1 - "+Global.player1Score+"|Player2 - "+Global.player2Score);
        if(Global.player2Score == 10){
            System.out.println("Player 1 Wins!");
            endBall();
        }
    }
}
