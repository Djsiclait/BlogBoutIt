/**
 * Created by Siclait on 30/05/2016.
 */
import Entity.*;
import BlogService.*;
import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws Exception {
        getHerokuAssignedPort();
        staticFileLocation("/public");

        PageCreator pages = new PageCreator();
    }

    static void getHerokuAssignedPort() {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        setPort(port);
    }
}
