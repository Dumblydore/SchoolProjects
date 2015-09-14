import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {
    @Override
    public void start(Stage mainStage) throws Exception {
        Group root = new Group();
        root.getChildren().addAll(new LoginScreen(root));
        mainStage.setTitle("Pong");
        mainStage.setScene(new Scene(root, Global.width, Global.height+100));
        mainStage.show();


    }

    public static void main(String[] args) {

        launch(args);
    }
}
