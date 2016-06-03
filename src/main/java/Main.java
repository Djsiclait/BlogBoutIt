/**
 * Created by Siclait on 30/05/2016.
 */
import java.sql.ResultSet;
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

        Tag tag = new Tag(4, "#Swag");
        Article article = new Article(0);

        pages.DBmanager.ProcessTagsOnArticlea(tag, article);

        ResultSet rs = pages.DBmanager.getConn().createStatement().executeQuery("SELECT * FROM HASHTAG WHERE ARTICULO=" +
                article.getId());

        while(rs.next())
            System.out.println(rs.getInt("etiqueta") + " " + rs.getInt("articulo"));

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
