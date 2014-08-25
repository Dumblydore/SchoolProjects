import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryo.Kryo;
import javafx.scene.Group;

import java.io.IOException;

public class ClientClass extends Client{
    private Object ob;
    public ClientClass(String ip){
        super();
        Kryo kryo = super.getKryo();
        registerClasses(kryo);
        super.start();
        try {
            super.connect(5000, ip, 54555, 54777);
            super.addListener(new Listener() {
                public void received(Connection connection, Object object) {

                    if (object instanceof Double) {
                        Global.paddleInfo.add((Double) object);
                    }
                    if (object instanceof String) {
                        Global.ballInfo.add(object.toString());
                    } if(object instanceof Object){
                        set(object);
                    }
                }
            });
        } catch(IOException e){
            System.out.println("Error: "+e);
        }
    }
    public void registerClasses(Kryo kryo){
        kryo.register(double[].class);
        kryo.register(Integer[].class);
    }
    public boolean tryToConnect(){
        super.sendTCP(Global.player2.getPlayerID());
        return waitOnReply();
    }
    public boolean waitOnReply(){
        if(ob instanceof String){
            if(ob.toString().equalsIgnoreCase("n")){
                System.out.println("Connection refused!");
                return false;
            } else {
                Global.player1.setPlayerID(ob.toString());
                System.out.println(Global.player1.getPlayerID()+" Accepted your request!");
                return true;
            }
        }
        return false;
    }
    private void set(Object ob){
        this.ob = ob;
    }
    public void StartGame(){
        Global.player1.syncPlayer();
        Global.ball.createTimeline();
        Global.ball.syncBall();
    }
}
