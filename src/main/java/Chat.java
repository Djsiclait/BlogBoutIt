/**
 * Created by Siclait on 14/6/16.
 */
import Entity.User;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import static j2html.TagCreator.*;
import static spark.Spark.init;
import static spark.Spark.webSocket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Chat {

    // Used for non registered guest of the website
    static Map<Session, String> userUsernameMap = new HashMap<>();
    static int nextUserNumber = 1; // Used for creating the next username

    // User for registered clients of the website
    static Map<String, User> authorUsernameMap = new HashMap<>();

    public Chat(){
        webSocket("/chat", ChatWebSocketHandler.class);
        init();
        System.out.println("\n\nWebSoket Initialized\n\n");
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

    public static void unicastMessage(Session ses, String sender, String message, String reciever){

        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {

            if(session.equals(ses))
                try{
                    session.getRemote().sendString(String.valueOf(new JSONObject()
                            .put("userMessage", createHtmlMessageFromSender(sender, message))
                            .put("user", "PLACEHOLER")));

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
