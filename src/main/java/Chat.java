/**
 * Created by Siclait on 14/6/16.
 */
import org.json.JSONObject;
import spark.Session;

import static j2html.TagCreator.*;
import static spark.Spark.init;
import static spark.Spark.webSocket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Chat {

    static Map<Session, String> userUsernameMap = new HashMap<>();
    static int nextUserNumber = 1; // Used for creating the next username

    public Chat(){
        webSocket("/chat", ChatWebSocketHandler.class);
        init();
    }

    // Broadcast function: Admin Only
    public static void broadcastAdminMessage(String sender, String message){

        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {

            try{
                session.getRemote().sendString(String.valueOf(new JSONObject()
                        .put("userMessage", createHtmlMessageFromSender(sender, message))
                        .put("userlist", userUsernameMap.values())));
            } catch (Exception exp){
                exp.printStackTrace();
            }
        });
    }

    // Builds a HTML element with a sender-name, message, timmestamp
    private static String createHtmlMessageFromSender(String sender, String message){

        return article().with(
                b(sender + ":"),
                p(message),
                span().withClass("timestamp").withText(new SimpleDateFormat("HH:mm:ss").format(new Date()))
        ).render();
    }
}
