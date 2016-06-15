/**
 * Created by Siclait on 14/6/16.
 */
import spark.Session;
import static spark.Spark.init;
import static spark.Spark.webSocket;

import java.util.HashMap;
import java.util.Map;

public class Chat {

    static Map<Session, String> userUsernameMap = new HashMap<>();
    static int nextUserNumber = 1; // Used for creating the next username

    public Chat(){
        webSocket("/chat", ChatWebSocketHandler.class);
        init();
    }

}
