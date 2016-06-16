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
import java.util.*;

public class Chat {

    // Used for non registered guest of the website
    static Map<Session, String> userUsernameMap = new HashMap<>();
    static Map<String, String> colorUsernameMap = new HashMap<>();
    static int nextUserNumber = 1; // Used for creating the next username

    static ArrayList<String> adjectives = new ArrayList<>();
    static ArrayList<String> nouns = new ArrayList<>();
    static ArrayList<String> colors = new ArrayList<>();

    // User for registered clients of the website
    static Map<String, User> authorUsernameMap = new HashMap<>();

    public Chat(){
        webSocket("/chat", ChatWebSocketHandler.class);
        init();
        System.out.println("\n\nWebSoket Initialized\n\n");
        FillAdjectives();
        FillNouns();
        FillColors();
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

    public static void unicastMessage(String sender, String message, String reciever){

        System.out.println("\n\nPING!");
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {

            System.out.println("DING!\n\n");
            if(session.equals(userUsernameMap.get(sender)))
                try{
                    //session.getRemote().sendString(String.valueOf(new JSONObject()
                            //.put("userMessage", createHtmlMessageFromSender(sender, message))
                            //.put("user", "PLACEHOLER")));
                    System.out.println("\n\nPONG!\n\n");
                } catch (Exception exp){
                    exp.printStackTrace();
                }
        });

    }

    // Builds a HTML element with a sender-name, message, timmestamp
    private static String createHtmlMessageFromSender(String sender, String message){

        return article().with(
                b(sender + ":"),
                p(message),//colorUsernameMap.get(sender)),
                span().withClass("timestamp").withText(new SimpleDateFormat("HH:mm:ss").format(new Date()))
        ).render();
    }

    // Auxilary Functions
    private static void  FillAdjectives(){
        adjectives.add("Well-Read ");
        adjectives.add("Quirky ");
        adjectives.add("Wise ");
        adjectives.add("Literate ");
        adjectives.add("Quizzical ");
        adjectives.add("Informed ");
        adjectives.add("Gracious ");
        adjectives.add("Infamous ");
    }

    private static void FillNouns(){
        nouns.add("Nomad");
        nouns.add("Bookworm");
        nouns.add("Reader");
        nouns.add("Owl");
        nouns.add("Student");
        nouns.add("Scholar");
        nouns.add("Teacher");
        nouns.add("Friend");
        nouns.add("Philosopher");
        nouns.add("Orator");
        nouns.add("Scribe");
    }

    private static void FillColors(){
        colors.add("red");
        colors.add("blue");
        colors.add("green");
        colors.add("cyan");
        colors.add("yellow");
        colors.add("purple");
        colors.add("orange");
        colors.add("beige");
        colors.add("violet");
        colors.add("pink");
    }

    static Random rand = new Random();
    public static String getUsername(){
        return "The " + adjectives.get(rand.nextInt(adjectives.size())) + nouns.get(rand.nextInt(nouns.size()));
    }

    public static String getColor(){
        return colors.get(rand.nextInt(colors.size()));
    }
}
