
import java.io.IOException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hoangvancong
 * 
 * http://localhost:8080/Websocket_Server/server
 */
@ServerEndpoint("/server")
public class MyServerEnpoint {
    private Session session;
    
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
    }
    
    @OnMessage
    public void onMessage(String message) throws IOException{
        System.out.println("message: " + message);
        if(this.session != null && this.session.isOpen()){
            this.session.getBasicRemote().sendText("From server: " + message);
        }
    }
}
