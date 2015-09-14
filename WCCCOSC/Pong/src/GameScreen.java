import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Created by Maurice on 4/30/2014.
 */
public class GameScreen extends Pane {
    public GameScreen() {
        super();
        ArrayList<Paddle> players = new ArrayList<>();
        players.add(Global.player1);
        players.add(Global.player2);
        super.setPrefSize(Global.width, Global.height);
        Global.ball.setPaddles(players);
        super.getChildren().addAll(players.get(0),players.get(1));
    }
    public void setPlayer1MouseControls(ServerClass host) {
        super.setOnMouseDragged((MouseEvent e) -> {
            Global.player1.movePaddle(e.getY(), e.getX());
            host.sendData(Global.player1.getPaddleLocation());
        });
    }

    public void setPlayer2Controls(ClientClass client) {
        super.setOnMouseDragged((MouseEvent e) -> {
            Global.player2.movePaddle(e.getY(), e.getX());
            client.sendUDP(Global.player2.getPaddleLocation());
        });
    }
    public void setStartButton(){
        Button startBtn = new Button("start");
        super.getChildren().addAll(startBtn);
        startBtn.setOnAction(ActionEvent -> {
            Global.player1Score = 0;
            Global.player2Score = 0;
            super.getChildren().addAll(Global.ball);
            Global.ball.StartBall();
        startBtn.setVisible(true);});
    }
}
