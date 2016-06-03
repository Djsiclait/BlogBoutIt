/**
 * Created by Siclait on 30/05/2016.
 */
import java.util.ArrayList;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws Exception {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        PageCreator pages = new PageCreator();
        // Initiate server connection
        //DatabaseManager.StartServer();
        //java.util.Date utilDate = new java.util.Date();
        //java.sql.Date time = new java.sql.Date(utilDate.getTime());
        // Terminate server connection
        //DatabaseManager.CloseServer();

        pages.DBmanager.DeleteComment(2);

        ArrayList<Comment> comments = pages.DBmanager.GetArticleComments(0);

        for (Comment comment:
             comments) {
            System.out.println(comment.getId() + " " + comment.getComment() + " " + comment.getAuthor());
        }

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
