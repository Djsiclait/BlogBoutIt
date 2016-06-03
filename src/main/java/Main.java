/**
 * Created by Siclait on 30/05/2016.
 */
import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws Exception {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        PageCreator pages = new PageCreator();
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
