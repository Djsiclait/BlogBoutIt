/**
 * Created by Siclait on 14/6/16.
 */

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ChatWebSocketHandler {

    private String sender;
    private String message;

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception{
        String username = Chat.getUsername(); //"User" + Chat.nextUserNumber++;
        Chat.userUsernameMap.put(user, username);
        Chat.colorUsernameMap.put(username, Chat.getColor());
        Chat.broadcastAdminMessage(sender = "Server",  message = (username + " joined the chat"));
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason){

        String username = Chat.userUsernameMap.get(user);
        Chat.userUsernameMap.remove(user);
        Chat.broadcastAdminMessage(sender = "Server", message = (username + " has left the chat"));
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message){

        Chat.broadcastAdminMessage(sender = Chat.userUsernameMap.get(user), this.message = message);
        //Chat.unicastMessage(sender = Chat.userUsernameMap.get(user), this.message = message, "admin");

    }

}
