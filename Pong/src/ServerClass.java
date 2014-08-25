import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import javafx.scene.Group;
import javafx.scene.shape.HLineTo;

import java.io.IOException;
import java.util.Scanner;

public class ServerClass extends Server {
    private Object ob;
    public ServerClass() {
        super();
        Kryo kryo = super.getKryo();
        registerClasses(kryo);
        super.start();
        try {
            super.bind(54555, 54777);
            super.addListener(new Listener() {
                public void received(Connection connection, Object ob) {
                    if (ob instanceof Double) {
                        System.out.println("test");
                        Global.paddleInfo.add((Double) ob);
                    }
                    if(ob instanceof String){
                    }
                }
            });
        } catch (IOException e) {
            System.out.println(e);
        }

    }
    public void registerClasses(Kryo kryo) {
        kryo.register(Double.class);
        kryo.register(Integer[].class);
    }

    public void sendData(Object ob) {
        getConnections()[0].sendTCP(ob);
    }
    public void respond(String s){
        getConnections()[0].sendTCP(s);
    }

    public void waitForOpponent() {
            System.out.println("Allow " + ob.toString() + " to connect?(y/n)");
            Scanner in = new Scanner(System.in);
            String choice = in.next();
            if (choice.equalsIgnoreCase("y")) {
                Global.player2.setPlayerID(ob.toString());
                respond(Global.player1.getPlayerID());
            } else {
                respond("n");
            }
        }

    public void StartGame(){
        Global.player2.syncPlayer();
        Global.ball.ballPhysics(this);
        Global.ball.syncBall();
    }
}

