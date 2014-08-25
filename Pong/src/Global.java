import javafx.scene.paint.Color;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Maurice on 4/30/2014.
 */
public class Global {
    public static int width = 800;
    public static int height = 400;
    public static LinkedBlockingQueue<Double> paddleInfo = new LinkedBlockingQueue<>();
    public static LinkedBlockingQueue<String> ballInfo = new LinkedBlockingQueue<>();
    public static Paddle player1 = new Paddle(Color.BLACK, 1);
    public static Paddle player2 = new Paddle(Color.BLACK, 2);
    public static Ball ball = new Ball();
    public static int player1Score = 0;
    public static int player2Score = 0;
    public static boolean check;

}
