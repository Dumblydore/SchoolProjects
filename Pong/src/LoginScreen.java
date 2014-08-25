import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * Created by Maurice on 4/30/2014.
 */
public class LoginScreen extends BorderPane {
    private Group root;

    public LoginScreen(Group root) {
        super();
        this.root = root;
        super.setLeft(new Rectangle(300,0));
        super.setTop(new Rectangle(0,250));
        super.setCenter(loginInfo());
    }

    private VBox loginInfo() {
        VBox box = new VBox();
        TextField name = new TextField();
        Button hostbtn = new Button("Host");
        hostbtn.setOnAction(ActionEvent -> search(name.getText().trim()));
        hostbtn.setMaxWidth(Double.MAX_VALUE);
        HBox joinBox = new HBox();
        Button joinBtn = new Button("Join");
        TextField ipAddr = new TextField();
        joinBtn.setOnAction(ActionEvent -> connect(ipAddr.getText().trim(), name.getText().trim()));
        joinBox.getChildren().addAll(joinBtn, ipAddr);
        box.getChildren().addAll(name, hostbtn, joinBox);
        return box;
    }

    private void search(String playerID) {
        Global.check = false;
        Global.player1.setPlayerID(playerID);
        System.out.println("Searching, Please wait.");
        ServerClass host = new ServerClass();
        host.StartGame();
        GameScreen game = new GameScreen();
        game.setPlayer1MouseControls(host);
        root.getChildren().remove(0);
        root.getChildren().add(game);
        game.setStartButton();
    }

    private void connect(String ip, String playerID) {
        Global.player2.setPlayerID(playerID);
        System.out.println("Searching, Please wait.");
        ClientClass client = new ClientClass(ip);
        client.tryToConnect();
        client.StartGame();
        GameScreen game = new GameScreen();
        game.setPlayer2Controls(client);
        root.getChildren().remove(0);
        root.getChildren().add(game);
    }

    private void startGame() {

    }
}
